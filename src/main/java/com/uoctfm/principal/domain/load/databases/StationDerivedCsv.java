package com.uoctfm.principal.domain.load.databases;

import java.time.LocalDateTime;

public class StationDerivedCsv {

    private int station;
    private int measurement;
    private LocalDateTime time;

    public StationDerivedCsv(int station, int measurement, LocalDateTime time){
        this.station = station;
        this.measurement = measurement;
        this.time = time;
    }

    public int getStation() {
        return station;
    }

    public int getMeasurement() {
        return measurement;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
