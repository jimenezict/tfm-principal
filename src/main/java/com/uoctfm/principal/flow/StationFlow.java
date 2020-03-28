package com.uoctfm.principal.flow;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.station.StationDerived;
import com.uoctfm.principal.domain.station.StationStatusDTO;
import com.uoctfm.principal.service.configuration.SystemConfiguration;
import com.uoctfm.principal.service.station.StationCalculation;
import com.uoctfm.principal.service.station.StationDataStoring;
import com.uoctfm.principal.service.station.StationStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;

public class StationFlow {

    @Autowired
    private SystemConfiguration systemConfiguration;
    @Autowired
    private StationStatus stationStatus;
    @Autowired
    private StationCalculation stationCalculation;
    @Autowired
    private StationDataStoring stationDataStoring;

    Logger logger = getLogger(StationFlow.class);

    public void executeById(Integer id) {

        StationDerived stationDerived = new StationDerived();

        logger.info("Starting {} process", id);

        SystemConfigurationDTO systemConfigurationDTO = systemConfiguration.getSystemConfigurationBy(id);
        if (!checkSystemConfiguration(id, systemConfigurationDTO)) return;

        StationStatusDTO stationStatusDTO = stationStatus.getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint());
        if (isNull(stationStatusDTO)) return;

        StationStatusDTO lastStationStatusDTO = stationStatus.getLastListStationStatus(systemConfigurationDTO.getId());
        if(nonNull(lastStationStatusDTO)){
            logger.warn("{} not found valid latest sample", systemConfigurationDTO.getName());
        }

        stationDataStoring.stationDataStoring(systemConfigurationDTO,
                stationCalculation.calculateDerived(stationStatusDTO, lastStationStatusDTO),
                stationCalculation.calculatePercentils(stationStatusDTO),
                stationCalculation.calculateRaw(stationStatusDTO));

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
