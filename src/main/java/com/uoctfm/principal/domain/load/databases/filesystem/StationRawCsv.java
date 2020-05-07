package com.uoctfm.principal.domain.load.databases.filesystem;

import com.uoctfm.principal.domain.extraction.Station;

import java.io.Serializable;

public class StationRawCsv extends BaseCsv implements Serializable {

    private Integer id;
    private Integer numBicicles;
    private Integer sizeStation;

    public StationRawCsv(Station station) {
        super();
        this.id = station.getId();
        this.numBicicles = station.getNumBicicles();
        this.sizeStation = station.getSizeStation();
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumBicicles() {
        return numBicicles;
    }

    public Integer getSizeStation() {
        return sizeStation;
    }
}
