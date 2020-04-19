package com.uoctfm.principal.repository.load.service;

import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;
import com.uoctfm.principal.domain.calculated.StationStatistics;

public abstract class AbstractDatabaseService {

    private String systemName;

    public final void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void saveRaw(StationRaw stationRaw){};

    public void savePercentils(StationPercentils stationPercentils, StationStatistics stationStatistics){};

    public void saveDerived(StationDerived stationDerived){};

}
