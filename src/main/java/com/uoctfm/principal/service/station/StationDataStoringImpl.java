package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.calculated.StationStatistics;
import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;
import com.uoctfm.principal.repository.load.service.FileSystemDatabaseService;
import com.uoctfm.principal.repository.load.service.GisDatabaseService;
import com.uoctfm.principal.repository.load.service.TimeseriesDatabaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationDataStoringImpl implements StationDataStoring {

    Logger logger = LoggerFactory.getLogger(StationDataStoring.class);

    private static String FILE_SYSTEM = "File System";
    private static String TIME_SERIES = "Time Series";
    private static String GIS = "GIS";

    @Autowired
    private FileSystemDatabaseService fileSystemDatabaseService;

    @Autowired
    private TimeseriesDatabaseService timeseriesDatabaseService;

    @Autowired
    private GisDatabaseService gisDatabaseService;

    @Override
    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO, StationDerived stationDerived, StationPercentils stationPercentils, StationRaw stationRaw, StationStatistics stationStatistics) {
        if(systemConfigurationDTO.getSaveInFileSystem()){
            fileSystemDatabaseService.saveRaw(stationRaw);
            fileSystemDatabaseService.saveDerived(stationDerived);
            fileSystemDatabaseService.savePercentils(stationPercentils, stationStatistics);
            logSuccessfulProcess(FILE_SYSTEM, systemConfigurationDTO.getName());
        }else{
            logSkippingProcess(FILE_SYSTEM, systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInTimeSeries()){
            timeseriesDatabaseService.saveRaw(stationRaw);
            timeseriesDatabaseService.saveDerived(stationDerived);
            timeseriesDatabaseService.savePercentils(stationPercentils, stationStatistics);
            logSuccessfulProcess(TIME_SERIES, systemConfigurationDTO.getName());
        }else{
            logSkippingProcess(TIME_SERIES, systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInGIS()){
            gisDatabaseService.saveRaw(stationRaw);
            gisDatabaseService.saveDerived(stationDerived);
            gisDatabaseService.savePercentils(stationPercentils, stationStatistics);
            logSuccessfulProcess(GIS, systemConfigurationDTO.getName());
        }else{
            logSkippingProcess(GIS, systemConfigurationDTO.getName());
        }
    }

    private void logSuccessfulProcess( String database, String name) {
        logger.info("Success on saving on {} database for process {}", database, name);
    }

    private void logSkippingProcess( String database, String name) {
        logger.info("Skipping to save on {} database for process {}", database, name);
    }

}
