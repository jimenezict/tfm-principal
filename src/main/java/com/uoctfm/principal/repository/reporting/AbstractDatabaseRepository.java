package com.uoctfm.principal.repository.reporting;

import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;

public abstract class AbstractDatabaseRepository {

    public void saveRaw(StationRaw stationRaw){};

    public void savePercentils(StationPercentils stationPercentils){};

    public void saveDerived(StationDerived stationDerived){};

}
