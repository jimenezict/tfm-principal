package com.uoctfm.principal.repository.load.service;

import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;
import com.uoctfm.principal.domain.calculated.StationStatistics;
import org.springframework.stereotype.Service;

@Service
public class FileSystemDatabaseService extends AbstractDatabaseService {

    @Override
    public void saveRaw(StationRaw stationRaw){};

    @Override
    public void savePercentils(StationPercentils stationPercentils, StationStatistics stationStatistics){};

    @Override
    public void saveDerived(StationDerived stationDerived){};

}
