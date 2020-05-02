package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.GlobalStatistical;
import com.uoctfm.principal.repository.load.gis.GisAccessRepository;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import com.uoctfm.principal.service.transformation.LocationAndStationMergeService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GisDatabaseService extends AbstractDatabaseService {

    Logger logger = getLogger(GisDatabaseService.class);

    StationsLocationDTO stationLocationDTO;

    @Autowired
    StationLocation stationLocation;

    @Autowired
    GisAccessRepository gisAccessRepository;

    @Autowired
    LocationAndStationMergeService locationAndStationMergeService;

    @Override
    public void initialize() {
        stationLocationDTO = stationLocation.getListLocationStatus(super.systemConfigurationDTO.getSystemLocationEndPoint());
    }

    @Override
    public void saveRaw(){
        locationAndStationMergeService.mergeRawlData(stationLocationDTO, stationRaw);
    };

    @Override
    public void saveStatistics(){
        GlobalStatistical globalStatistical = locationAndStationMergeService.mergeStatisticalDate(stationLocationDTO, stationStatistics);
        gisAccessRepository.save(globalStatistical);
    }

    @Override
    public void saveDerived(){
        logger.info("GIS not saves derived data");
    };

}
