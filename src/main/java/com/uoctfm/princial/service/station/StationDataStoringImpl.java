package com.uoctfm.princial.service.station;

import com.uoctfm.princial.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.princial.domain.station.StationDerived;
import com.uoctfm.princial.domain.station.StationPercentils;
import com.uoctfm.princial.domain.station.StationRaw;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StationDataStoringImpl implements StationDataStoring {

    Logger logger = LoggerFactory.getLogger(StationDataStoring.class);

    @Override
    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO, StationDerived stationDerived, StationPercentils stationPercentils, StationRaw stationRaw) {
        if(systemConfigurationDTO.getSaveInFileSystem()){
            logger.info("Proceeding Save in File System for process {}", systemConfigurationDTO.getName());
        }else{
            logger.info("Skipping Save in File System for process {}", systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInTimeSeries()){
            logger.info("Proceeding Save in Time Series for process {}", systemConfigurationDTO.getName());
        }else{
            logger.info("Skipping Save in Time Series for process {}", systemConfigurationDTO.getName());
        }

        if(systemConfigurationDTO.getSaveInGIS()){
            logger.info("Proceeding Save in Time Series for process {}", systemConfigurationDTO.getName());
        }else{
            logger.info("Skipping Save in Time Series for process {}", systemConfigurationDTO.getName());
        }
    }

}
