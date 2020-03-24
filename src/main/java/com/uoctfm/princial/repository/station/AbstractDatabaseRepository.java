package com.uoctfm.princial.repository.station;

import com.uoctfm.princial.domain.station.StationDerived;
import com.uoctfm.princial.domain.station.StationPercentils;
import com.uoctfm.princial.domain.station.StationRaw;

public abstract class AbstractDatabaseRepository {

    public void saveRaw(StationRaw stationRaw){};

    public void savePercentils(StationPercentils stationPercentils){};

    public void saveDerived(StationDerived stationDerived){};

}
