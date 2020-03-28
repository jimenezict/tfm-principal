package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.StationDerived;
import com.uoctfm.principal.domain.station.StationPercentils;
import com.uoctfm.principal.domain.station.StationRaw;
import com.uoctfm.principal.domain.station.StationStatusDTO;

public interface StationCalculation {

    public StationPercentils calculatePercentils(StationStatusDTO stationStatusDTO);

    public StationDerived calculateDerived(StationStatusDTO stationStatusDTO, StationStatusDTO lastStationStatusDTO);

    public StationRaw calculateRaw(StationStatusDTO stationStatusDTO);

}
