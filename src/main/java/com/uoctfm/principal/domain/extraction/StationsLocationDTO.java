package com.uoctfm.principal.domain.extraction;

import java.util.HashMap;
import java.util.Map;

public class StationsLocationDTO {

    private Map<Integer, Location> locationList;

    public StationsLocationDTO() {
        locationList = new HashMap<>();
    }

    public Map<Integer, Location> getLocationList() {
        return locationList;
    }

    public void addLocation(Location location) {
        locationList.put(location.getId(), location);
    }
}



