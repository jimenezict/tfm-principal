package com.uoctfm.principal.service.configuration;

import com.uoctfm.principal.domain.configuration.SystemStatisticsDTO;
import com.uoctfm.principal.repository.configuration.SystemStatisticsRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SystemStatisticsImpl implements SystemStatistics {

    @Value("${service.main.systemstatistics:false}")
    private Boolean systemStatistics;
    @Autowired
    private SystemStatisticsRepository systemStatisticsRepository;

    Logger logger = getLogger(SystemStatisticsImpl.class);

    @Override
    public void insert(SystemStatisticsDTO systemStatisticsDTO) {
        if(systemStatistics) {
            try {
                systemStatisticsRepository.save(systemStatisticsDTO);
            } catch (Exception e) {
                logger.error("Process {} could not save execution statistics: ", systemStatisticsDTO.getId(), e.getMessage());
            }
        }
    }
}
