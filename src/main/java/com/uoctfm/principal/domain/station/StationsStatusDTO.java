package com.uoctfm.principal.domain.station;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

public class StationsStatusDTO {

    private LocalDateTime executionDateTime;

    private Map<Integer, Station> stationList;

    public LocalDateTime getExecutionDateTime() {
        return executionDateTime;
    }

    public StationsStatusDTO() {
        executionDateTime = now();
        stationList = new HashMap<>();
    }

    public void setExecutionDateTime(LocalDateTime executionDateTime) {
        this.executionDateTime = executionDateTime;
    }

    public void setStationList(Map<Integer, Station> stationList) {
        this.stationList = stationList;
    }

    public Map<Integer, Station> getStationList() {
        return stationList;
    }

    public int getNumberStations() {
        return stationList.size();
    }

    public void addStation(Station station) {
        stationList.put(station.getId(), station);
    }
}
