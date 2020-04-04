package com.uoctfm.principal.domain.station.calculated;

import com.uoctfm.principal.domain.station.StationsStatusDTO;

public class StationRaw {

    private StationsStatusDTO stationStatusDTO;

    public StationRaw(StationsStatusDTO stationsStatusDTO) {
        this.stationStatusDTO = stationsStatusDTO;
    }

    public StationsStatusDTO getStationStatusDTO(){
        return stationStatusDTO;
    }

}
