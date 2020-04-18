package com.uoctfm.principal.repository.load;

import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;
import com.uoctfm.principal.domain.calculated.StationStatistics;

public abstract class AbstractDatabaseRepository {

    public void saveRaw(StationRaw stationRaw){};

    public void savePercentils(StationPercentils stationPercentils, StationStatistics stationStatistics){};

    public void saveDerived(StationDerived stationDerived){};

}
