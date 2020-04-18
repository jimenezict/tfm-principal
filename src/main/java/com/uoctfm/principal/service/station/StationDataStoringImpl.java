package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;
import com.uoctfm.principal.repository.load.FileSystemDatabaseRepository;
import com.uoctfm.principal.repository.load.GisDatabaseRepository;
import com.uoctfm.principal.repository.load.TimeseriesDatabaseRepository;
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
    private FileSystemDatabaseRepository fileSystemDatabaseRepository;

    @Autowired
    private TimeseriesDatabaseRepository timeseriesDatabaseRepository;

    @Autowired
    private GisDatabaseRepository gisDatabaseRepository;

    @Override
    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO, StationDerived stationDerived, StationPercentils stationPercentils, StationRaw stationRaw) {
        if(systemConfigurationDTO.getSaveInFileSystem()){
            fileSystemDatabaseRepository.saveRaw(stationRaw);
            fileSystemDatabaseRepository.saveDerived(stationDerived);
            fileSystemDatabaseRepository.savePercentils(stationPercentils, );
            logSuccessfulProcess(FILE_SYSTEM, systemConfigurationDTO.getName());
        }else{
            logSkippingProcess(FILE_SYSTEM, systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInTimeSeries()){
            timeseriesDatabaseRepository.saveRaw(stationRaw);
            timeseriesDatabaseRepository.saveDerived(stationDerived);
            timeseriesDatabaseRepository.savePercentils(stationPercentils, );
            logSuccessfulProcess(TIME_SERIES, systemConfigurationDTO.getName());
        }else{
            logSkippingProcess(TIME_SERIES, systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInGIS()){
            gisDatabaseRepository.saveRaw(stationRaw);
            gisDatabaseRepository.saveDerived(stationDerived);
            gisDatabaseRepository.savePercentils(stationPercentils, );
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
