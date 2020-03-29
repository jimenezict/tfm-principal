package com.uoctfm.principal.repository.station;

import com.uoctfm.principal.domain.station.calculated.StationDerived;
import com.uoctfm.principal.domain.station.calculated.StationPercentils;
import com.uoctfm.principal.domain.station.calculated.StationRaw;

public abstract class AbstractDatabaseRepository {

    public void saveRaw(StationRaw stationRaw){};

    public void savePercentils(StationPercentils stationPercentils){};

    public void saveDerived(StationDerived stationDerived){};

}
