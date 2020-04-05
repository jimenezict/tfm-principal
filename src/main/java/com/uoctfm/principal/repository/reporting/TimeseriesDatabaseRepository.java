package com.uoctfm.principal.repository.reporting;

import com.uoctfm.principal.domain.station.calculated.StationDerived;
import com.uoctfm.principal.domain.station.calculated.StationPercentils;
import com.uoctfm.principal.domain.station.calculated.StationRaw;
import org.springframework.stereotype.Service;

@Service
public class TimeseriesDatabaseRepository extends AbstractDatabaseRepository{

    @Override
    public void saveRaw(StationRaw stationRaw){};

    @Override
    public void savePercentils(StationPercentils stationPercentils){};

    @Override
    public void saveDerived(StationDerived stationDerived){};

}