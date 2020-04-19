package com.uoctfm.principal.domain.transformation;

import com.uoctfm.principal.domain.extraction.StationsStatusDTO;

public class StationRaw {

    private StationsStatusDTO stationStatusDTO;

    public StationRaw(StationsStatusDTO stationsStatusDTO) {
        this.stationStatusDTO = stationsStatusDTO;
    }

    public StationsStatusDTO getStationStatusDTO(){
        return stationStatusDTO;
    }

}
