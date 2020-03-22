package com.uoctfm.princial.service.station;

import com.uoctfm.princial.domain.station.StationDerived;
import com.uoctfm.princial.domain.station.StationPercentils;
import com.uoctfm.princial.domain.station.StationRaw;
import com.uoctfm.princial.domain.station.StationStatusDTO;

public interface StationCalculation {

    public StationPercentils calculatePercentils(StationStatusDTO stationStatusDTO);

    public StationDerived calculateDerived(StationStatusDTO stationStatusDTO, StationStatusDTO lastStationStatusDTO);

    public StationRaw calculateRaw(StationStatusDTO stationStatusDTO);

}
