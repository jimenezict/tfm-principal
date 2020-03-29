package com.uoctfm.principal.domain.station;

public class Station {

    private Integer id;

    private Integer numBicicles;

    private Integer sizeStation;


    public Station(Integer id, Integer numBicicles, Integer sizeStation) {
        this.id = id;
        this.numBicicles = numBicicles;
        this.sizeStation = sizeStation;
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

    public Integer getPercentage() {
        return (numBicicles * 100) / (numBicicles + sizeStation);
    }

    public Integer getPercentil() {
        return getPercentage() / 10;
    }

}
