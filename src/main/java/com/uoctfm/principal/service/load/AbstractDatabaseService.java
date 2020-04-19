package com.uoctfm.principal.service.load;

import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;

public abstract class AbstractDatabaseService {

    private String systemName;

    public final void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public void saveRaw(StationRaw stationRaw){};

    public void savePercentils(StationPercentils stationPercentils, StationStatistics stationStatistics){};

    public void saveDerived(StationDerived stationDerived){};

}
