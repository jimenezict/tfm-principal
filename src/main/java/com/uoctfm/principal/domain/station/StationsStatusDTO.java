package com.uoctfm.principal.domain.station;

import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static java.time.LocalDateTime.now;

@Entity
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
