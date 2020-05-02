package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.service.configuration.SystemConfiguration;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocationImpl;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GisDatabaseService extends AbstractDatabaseService {

    Logger logger = getLogger(GisDatabaseService.class);

    StationsLocationDTO stationLocationDTO;

    @Autowired
    StationLocation stationLocation;

    @Override
    public void initialize() {
        stationLocationDTO = stationLocation.getListLocationStatus(super.systemConfigurationDTO.getSystemLocationEndPoint());
    }

    @Override
    public void saveRaw(){};

    @Override
    public void saveStatistics(){};

    @Override
    public void saveDerived(){
        logger.info("GIS not saves derived");
    };

}
