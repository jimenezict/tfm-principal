package com.uoctfm.principal.repository.station;

import com.uoctfm.principal.domain.station.StationDerived;
import com.uoctfm.principal.domain.station.StationPercentils;
import com.uoctfm.principal.domain.station.StationRaw;

public class GisDatabaseRepository extends AbstractDatabaseRepository{

    @Override
    public void saveRaw(StationRaw stationRaw){};

    @Override
    public void savePercentils(StationPercentils stationPercentils){};

    @Override
    public void saveDerived(StationDerived stationDerived){};

}
