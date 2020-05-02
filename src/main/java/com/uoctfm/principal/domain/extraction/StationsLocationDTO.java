package com.uoctfm.principal.domain.extraction;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

public class StationsLocationDTO {

    private LocalDateTime executionDateTime;

    private Map<Integer, Location> locationList;

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public StationsLocationDTO() {
        executionDateTime = now();
        locationList = new HashMap<>();
    }

    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }

    public void setStationList(Map<Integer, Location> locationList) {
        this.locationList = locationList;
    }

    public Map<Integer, Location> getLocationList() {
        return locationList;
    }

    public int getNumberStations() {
        return locationList.size();
    }

    public void addLocation(Location location) {
        locationList.put(location.getId(), location);
    }
}
