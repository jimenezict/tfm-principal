package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import org.springframework.stereotype.Service;

@Service
public class GisDatabaseService extends AbstractDatabaseService {

    @Override
    public void initialize() {

    }

    @Override
    public void saveRaw(){};

    @Override
    public void savePercentils(){};

    @Override
    public void saveDerived(){};

}
