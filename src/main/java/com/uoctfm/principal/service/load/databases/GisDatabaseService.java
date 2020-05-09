package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.gis.GlobalStatistical;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import com.uoctfm.principal.domain.transformation.StationDataWrapper;
import com.uoctfm.principal.repository.load.gis.GisAccessRepository;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import com.uoctfm.principal.service.transformation.LocationAndStationMergeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

import static java.lang.Integer.valueOf;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.toSet;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GisDatabaseService extends AbstractDatabaseService {

    @Autowired
    StationLocation stationLocation;

    @Autowired
    GisAccessRepository gisAccessRepository;

    @Autowired
    LocationAndStationMergeService locationAndStationMergeService;

    Logger logger = getLogger(GisDatabaseService.class);

    @Override
    public void initialize(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
    }

    @Override
    public void saveRaw(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        StationsLocationDTO locationList = stationLocation.getListLocationStatus(systemConfigurationDTO.getSystemLocationEndPoint());
        Set<StationSystemRaw> stationSystemRaw = locationAndStationMergeService.mergeRawData(locationList, stationDataWrapper.getStationRaw());
        TreeSet<StationSystemRaw> databaseStationSystemRaw = gisAccessRepository.findStationSystem(valueOf(systemConfigurationDTO.getId()));

        logger.info("Found {} register on Station System Raw table (GIS) for system {}.", stationSystemRaw.size(), systemConfigurationDTO.getName());

        Set<StationSystemRaw> stationSystemRawToSave =
                stationSystemRaw.stream().map(localStationSystemRaw -> {
                    localStationSystemRaw.setSystem(systemConfigurationDTO.getId());
                    if (databaseStationSystemRaw.contains(localStationSystemRaw)) {
                        localStationSystemRaw.setId(databaseStationSystemRaw.floor(localStationSystemRaw).getId());
                    }
                    return localStationSystemRaw;
                }).collect(toSet());

        logger.info("Inserting/Updating {} register on Station System Raw table (GIS) for system {}.", stationSystemRawToSave.size(), systemConfigurationDTO.getName());

        gisAccessRepository.saveStationSystem(stationSystemRawToSave);
    }

    @Override
    public void saveStatistics(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        StationsLocationDTO stationsLocationDTO = stationLocation.getListLocationStatus(systemConfigurationDTO.getSystemLocationEndPoint());
        GlobalStatistical globalStatistical = gisAccessRepository.findBySystem(systemConfigurationDTO.getId());

        if (nonNull(globalStatistical)) {
            logger.info("Found register for {} ({}) on Global Statistical table, proceeding to update", systemConfigurationDTO.getName(), globalStatistical.getSystem());
            locationAndStationMergeService.updateStatisticalData(globalStatistical, stationDataWrapper.getStationStatistics());
        } else {
            logger.info("Not found register for {} ({}) on Global Statistical table, proceeding to insert", systemConfigurationDTO.getName(), systemConfigurationDTO.getId());
            globalStatistical = locationAndStationMergeService.mergeStatisticalDate(stationsLocationDTO, stationDataWrapper.getStationStatistics());
            globalStatistical.setSystem(systemConfigurationDTO.getId());
            globalStatistical.setName(systemConfigurationDTO.getName());
        }
        gisAccessRepository.saveGlobal(globalStatistical);
    }

    @Override
    public void saveDerived(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        logger.info("GIS not saves derived data");
    }

}
