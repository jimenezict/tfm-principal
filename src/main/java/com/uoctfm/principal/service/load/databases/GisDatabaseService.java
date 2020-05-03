package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.GlobalStatistical;
import com.uoctfm.principal.repository.load.gis.GisAccessRepository;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocationImpl;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import com.uoctfm.principal.service.transformation.LocationAndStationMergeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GisDatabaseService extends AbstractDatabaseService {

    @Autowired
    StationLocation stationLocation ;

    @Autowired
    GisAccessRepository gisAccessRepository;

    @Autowired
    LocationAndStationMergeService locationAndStationMergeService;

    Logger logger = getLogger(GisDatabaseService.class);
    StationsLocationDTO stationLocationDTO;

    @Override
    public void initialize() {
        stationLocation = new StationLocationImpl();
    }

    @Override
    public void saveRaw() {
        locationAndStationMergeService.mergeRawData(stationLocationDTO, stationRaw);
    }

    @Override
    public void saveStatistics() {
        GlobalStatistical globalStatistical = gisAccessRepository.findBySystem(super.getSystemConfigurationDTO().getName());
        if(nonNull(globalStatistical)) {
            logger.info("Found register for {} on Global Statistical table, proceeding to update", globalStatistical.getSystem());
            locationAndStationMergeService.updateStatisticalData(globalStatistical, stationStatistics);
        } else {
            logger.info("Not found register for {} on Global Statistical table, proceeding to insert", super.getSystemConfigurationDTO().getId());
            globalStatistical = locationAndStationMergeService.mergeStatisticalDate(stationLocationDTO, stationStatistics);
            globalStatistical.setSystem(super.getSystemConfigurationDTO().getId());
        }
        gisAccessRepository.save(globalStatistical);
    }

    @Override
    public void saveDerived() {
        logger.info("GIS not saves derived data");
    }

}
