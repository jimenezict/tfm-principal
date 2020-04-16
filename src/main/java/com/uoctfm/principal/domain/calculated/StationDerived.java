package com.uoctfm.principal.domain.calculated;

import java.util.HashMap;
import java.util.Map;


public class StationDerived {

    private Map<Integer, Integer> stationsStatusDTO = new HashMap<Integer, Integer>();

    private boolean hasPreviousStatus = true;

    public Map<Integer, Integer> getStationsStatusDTO() {
        return stationsStatusDTO;
    }

    public void addStationStatus(Integer stationId, Integer derivedMeaseure) {
        stationsStatusDTO.put(stationId, derivedMeaseure);
    }

    public void hasNoPreviousStatus(){
        hasPreviousStatus = false;
    }

}
