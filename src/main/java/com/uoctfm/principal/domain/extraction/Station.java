package com.uoctfm.principal.domain.extraction;

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
        return sizeStation != 0 ? (numBicicles * 100) / (sizeStation) : 0;
    }

    public Integer getPercentil() {
        return getPercentage() == 100 ? 9 : getPercentage() / 10;
    }

}
