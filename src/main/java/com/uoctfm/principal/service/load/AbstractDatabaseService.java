package com.uoctfm.principal.service.load;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.transformation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractDatabaseService {

    Logger logger = LoggerFactory.getLogger(AbstractDatabaseService.class);

    public void databaseServiceExecutor(boolean executeStep, String databaseType, StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        if (executeStep) {
            initialize(stationDataWrapper, systemConfigurationDTO);
            saveRaw(stationDataWrapper, systemConfigurationDTO);
            saveStatistics(stationDataWrapper, systemConfigurationDTO);
            logSuccessfulProcess(databaseType, systemConfigurationDTO);
            return;
        }
        logSkippingProcess(databaseType, systemConfigurationDTO);
    }

    public abstract void initialize(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO);

    public abstract void saveRaw(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO);

    public abstract void saveDerived(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO);

    public abstract void saveStatistics(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO);

    private void logSuccessfulProcess(String type, SystemConfigurationDTO systemConfigurationDTO) {
        logger.info("Success on saving on {} database for process {}", type, systemConfigurationDTO.getName());
    }

    private void logSkippingProcess(String type, SystemConfigurationDTO systemConfigurationDTO) {
        logger.info("Skipping to save on {} database for process {}", type, systemConfigurationDTO.getName());
    }

}
