package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.GlobalStatistical;
import com.uoctfm.principal.domain.transformation.StationDataWrapper;
import com.uoctfm.principal.repository.load.gis.GisAccessRepository;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.load.AbstractDatabaseStateLessService;
import com.uoctfm.principal.service.transformation.LocationAndStationMergeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GisDatabaseService extends AbstractDatabaseStateLessService {

    @Autowired
    StationLocation stationLocation ;

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
        StationsLocationDTO stationsLocationDTO = stationLocation.getListLocationStatus(systemConfigurationDTO.getSystemLocationEndPoint());
        locationAndStationMergeService.mergeRawData(stationsLocationDTO, stationDataWrapper.getStationRaw());
    }

    @Override
    public void saveStatistics(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        StationsLocationDTO stationsLocationDTO = stationLocation.getListLocationStatus(systemConfigurationDTO.getSystemLocationEndPoint());
        GlobalStatistical globalStatistical = gisAccessRepository.findBySystem(systemConfigurationDTO.getId());

        if(nonNull(globalStatistical)) {
            logger.info("Found register for {} ({}) on Global Statistical table, proceeding to update", systemConfigurationDTO.getName(), globalStatistical.getSystem());
            locationAndStationMergeService.updateStatisticalData(globalStatistical, stationDataWrapper.getStationStatistics());
        } else {
            logger.info("Not found register for {} ({}) on Global Statistical table, proceeding to insert",systemConfigurationDTO.getName(), systemConfigurationDTO.getId());
            globalStatistical = locationAndStationMergeService.mergeStatisticalDate(stationsLocationDTO, stationDataWrapper.getStationStatistics());
            globalStatistical.setSystem(systemConfigurationDTO.getId());
        }
        gisAccessRepository.save(globalStatistical);
    }

    @Override
    public void saveDerived(StationDataWrapper stationDataWrapper, SystemConfigurationDTO systemConfigurationDTO) {
        logger.info("GIS not saves derived data");
    }

}
