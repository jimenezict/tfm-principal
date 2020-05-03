package com.uoctfm.principal.domain.transformation;

public class StationDataWrapper {

    private StationDerived stationDerived;
    private StationPercentils stationPercentils;
    private StationRaw stationRaw;
    private StationStatistics stationStatistics;

    public StationDataWrapper(StationDerived stationDerived, StationPercentils stationPercentils, StationRaw stationRaw, StationStatistics stationStatistics) {
        this.stationDerived = stationDerived;
        this.stationPercentils = stationPercentils;
        this.stationRaw = stationRaw;
        this.stationStatistics = stationStatistics;
    }

    public StationDerived getStationDerived() {
        return stationDerived;
    }

    public StationPercentils getStationPercentils() {
        return stationPercentils;
    }

    public StationRaw getStationRaw() {
        return stationRaw;
    }

    public StationStatistics getStationStatistics() {
        return stationStatistics;
    }
}
