package com.uoctfm.principal.flow;

import com.uoctfm.principal.domain.calculated.StationStatistics;
import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;
import com.uoctfm.principal.service.configuration.SystemConfiguration;
import com.uoctfm.principal.service.station.StationCalculation;
import com.uoctfm.principal.service.station.StationDataStoring;
import com.uoctfm.principal.service.station.StationStatus;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class SystemFlow {

    @Autowired
    private SystemConfiguration systemConfiguration;
    @Autowired
    private StationStatus stationStatus;
    @Autowired
    private StationCalculation stationCalculation;
    @Autowired
    private StationDataStoring stationDataStoring;

    private Logger logger = getLogger(SystemFlow.class);

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

        StationsStatusDTO lastStationsStatusDTO = stationStatus.getLastStationStatus(systemConfigurationDTO.getId());
        if(isNull(lastStationsStatusDTO)){
            logger.warn("Not found the latest sample for {}", systemConfigurationDTO.getName().trim());
        }

        StationDerived stationDerived = stationCalculation.calculateDerived(stationsStatusDTO, lastStationsStatusDTO);
        StationPercentils stationPercentil = stationCalculation.calculatePercentils(stationsStatusDTO);
        StationRaw stationRaw = stationCalculation.calculateRaw(stationsStatusDTO);
        StationStatistics stationStatistics = stationCalculation.calculateStatistics(stationsStatusDTO);

        stationDataStoring.stationDataStoring(systemConfigurationDTO, stationDerived, stationPercentil, stationRaw, stationStatistics);
        stationStatus.saveLastStationStatus(stationsStatusDTO, id);

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
