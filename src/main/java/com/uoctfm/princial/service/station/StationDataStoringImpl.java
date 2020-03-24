package com.uoctfm.princial.service.station;

import com.uoctfm.princial.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.princial.domain.station.StationDerived;
import com.uoctfm.princial.domain.station.StationPercentils;
import com.uoctfm.princial.domain.station.StationRaw;
import com.uoctfm.princial.repository.station.FileSystemDatabaseRepository;
import com.uoctfm.princial.repository.station.GisDatabaseRepository;
import com.uoctfm.princial.repository.station.TimeseriesDatabaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationDataStoringImpl implements StationDataStoring {

    Logger logger = LoggerFactory.getLogger(StationDataStoring.class);

    @Autowired
    private FileSystemDatabaseRepository fileSystemDatabaseRepository = new FileSystemDatabaseRepository();

    @Autowired
    private TimeseriesDatabaseRepository timeseriesDatabaseRepository = new TimeseriesDatabaseRepository();

    @Autowired
    private GisDatabaseRepository gisDatabaseRepository = new GisDatabaseRepository();

    @Override
    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO, StationDerived stationDerived, StationPercentils stationPercentils, StationRaw stationRaw) {
        if(systemConfigurationDTO.getSaveInFileSystem()){
            fileSystemDatabaseRepository.saveRaw(stationRaw);
            fileSystemDatabaseRepository.saveDerived(stationDerived);
            fileSystemDatabaseRepository.savePercentils(stationPercentils);
            logger.info("Proceeding Save in File System for process {}", systemConfigurationDTO.getName());
        }else{
            logger.info("Skipping Save in File System for process {}", systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInTimeSeries()){
            timeseriesDatabaseRepository.saveRaw(stationRaw);
            timeseriesDatabaseRepository.saveDerived(stationDerived);
            timeseriesDatabaseRepository.savePercentils(stationPercentils);
            logger.info("Proceeding Save in Time Series for process {}", systemConfigurationDTO.getName());
        }else{
            logger.info("Skipping Save in Time Series for process {}", systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInGIS()){
            gisDatabaseRepository.saveRaw(stationRaw);
            gisDatabaseRepository.saveDerived(stationDerived);
            gisDatabaseRepository.savePercentils(stationPercentils);
            logger.info("Proceeding Save in Time Series for process {}", systemConfigurationDTO.getName());
        }else{
            logger.info("Skipping Save in Time Series for process {}", systemConfigurationDTO.getName());
        }
    }

}
