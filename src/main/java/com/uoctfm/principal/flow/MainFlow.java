package com.uoctfm.principal.flow;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.service.configuration.SystemConfiguration;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


@Component
public class MainFlow {

    @Autowired
    private SystemConfiguration systemConfiguration;

    @Autowired
    private SystemFlow systemFlow;

    private final Logger logger = getLogger(MainFlow.class);

    @Scheduled(cron = "0 0/1 * 1/1 * ?")
    public void execute() {
        List<SystemConfigurationDTO> systemConfigurationList = systemConfiguration.getSystemConfiguration();

        logger.info("Starting Main process, will be executed {} processes", systemConfigurationList.size());

        systemConfigurationList
                .stream()
                .filter(systemConfiguration -> systemConfiguration.getMasterEnable())
                .forEach(systemConfiguration -> {
                    new Thread(() -> systemFlow.executeById(systemConfiguration.getId())).start();
                });

        logger.info("Ending Main process");
    }

}
