package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;
import com.uoctfm.principal.domain.station.StationsStatusDTO;

public interface StationCalculation {

    public StationPercentils calculatePercentils(StationsStatusDTO stationsStatusDTO);

    public StationDerived calculateDerived(StationsStatusDTO stationsStatusDTO, StationsStatusDTO lastStationsStatusDTO);

    public StationRaw calculateRaw(StationsStatusDTO stationsStatusDTO);

}
