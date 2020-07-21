package com.uoctfm.principal.flow;

import com.uoctfm.principal.domain.transformation.*;
import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import com.uoctfm.principal.service.configuration.SystemConfiguration;
import com.uoctfm.principal.service.load.databases.FileSystemDatabaseService;
import com.uoctfm.principal.service.load.databases.GisDatabaseService;
import com.uoctfm.principal.service.load.databases.TimeseriesDatabaseService;
import com.uoctfm.principal.service.transformation.StationCalculation;
import com.uoctfm.principal.service.extraction.stationStatus.StationStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class SystemFlowImpl implements SystemFlow {

    @Autowired
    private SystemConfiguration systemConfiguration;
    @Autowired
    private StationStatus stationStatus;
    @Autowired
    private StationCalculation stationCalculation;
    @Autowired
    private GisDatabaseService gisDatabaseService;
    @Autowired
    private FileSystemDatabaseService fileSystemDatabaseService;
    @Autowired
    private TimeseriesDatabaseService timeseriesDatabaseService;

    private Logger logger = getLogger(SystemFlowImpl.class);

    @Override
    public void executeById(Integer id) {

        logger.info("Starting {} process", id);

        SystemConfigurationDTO systemConfigurationDTO = systemConfiguration.getSystemConfigurationBy(id);
        if (!checkSystemConfiguration(id, systemConfigurationDTO)) {
            logger.error("Ending {} process - because doesn't exists configuration", id);
            return;
        }

        StationsStatusDTO stationsStatusDTO = stationStatus.getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint());
        if (isNull(stationsStatusDTO)) {
            logger.error("Ending {} process - because couldn't reach microservice", id);
            return;
        }

        StationPercentils stationPercentil = stationCalculation.calculatePercentils(stationsStatusDTO);
        StationRaw stationRaw = stationCalculation.calculateRaw(stationsStatusDTO);
        StationStatistics stationStatistics = stationCalculation.calculateStatistics(stationsStatusDTO);

        StationDataWrapper stationDataWrapper = new StationDataWrapper(null, stationPercentil, stationRaw, stationStatistics);

        gisDatabaseService.databaseServiceExecutor(systemConfigurationDTO.getSaveInGIS(), "Gis", stationDataWrapper, systemConfigurationDTO);
        fileSystemDatabaseService.databaseServiceExecutor(systemConfigurationDTO.getSaveInFileSystem(), "File System", stationDataWrapper, systemConfigurationDTO);
        timeseriesDatabaseService.databaseServiceExecutor(systemConfigurationDTO.getSaveInTimeSeries(), "Timeseries", stationDataWrapper, systemConfigurationDTO);

        logger.info("Ending {} process", id);

    }

    private boolean checkSystemConfiguration(Integer id, SystemConfigurationDTO systemConfigurationDTO) {
        if(isNull(systemConfigurationDTO)){
            logger.warn("Configuration {}, not found on database", id);
            return false;
        }

        logger.info("Configuration {} on database belongs to {}",
                systemConfigurationDTO.getId(),
                systemConfigurationDTO.getName());
        return true;

    }

}
