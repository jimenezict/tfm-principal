package com.uoctfm.principal.flow;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.domain.station.calculated.StationDerived;
import com.uoctfm.principal.service.configuration.SystemConfiguration;
import com.uoctfm.principal.service.station.StationCalculation;
import com.uoctfm.principal.service.station.StationDataStoring;
import com.uoctfm.principal.service.station.StationStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;


@Component
public class StationMain {

    @Autowired
    private SystemConfiguration systemConfiguration;

    private Logger logger = getLogger(StationMain.class);

    @Scheduled(fixedRate = 500)
    public void execute() {
        List<SystemConfigurationDTO> systemConfigurationList = systemConfiguration.getSystemConfiguration();

        logger.info("Starting Main process");

        systemConfigurationList.forEach(systemConfiguration -> {
            StationFlow stationFlow = new StationFlow();
            stationFlow.executeById(systemConfiguration.getId());
        });

        logger.info("Starting End process");
    }

}
