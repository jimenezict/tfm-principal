package com.uoctfm.principal.domain.load.databases.filesystem;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.Serializable;

@JsonPropertyOrder({"time","date"})
public class StationDerivedCsv extends BaseCsv implements Serializable {

    private int station;
    private int measurement;

    public StationDerivedCsv(int station, int measurement){
        super();
        this.station = station;
        this.measurement = measurement;
    }

    public int getStation() {
        return station;
    }

    public int getMeasurement() {
        return measurement;
    }

}
