package com.uoctfm.principal;

import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;

import static com.uoctfm.principal.TestBuildHelper.buildStationsStatusDTO;

public class TestDataBuildHelper {

    public static StationDerived buildStationDerived() {
        StationDerived stationDerived = new StationDerived();
        stationDerived.addStationStatus(1, -3);
        stationDerived.addStationStatus(2, 8);
        stationDerived.addStationStatus(3, 0);
        stationDerived.addStationStatus(4, -1);
        stationDerived.addStationStatus(5, 3);

        return stationDerived;
    }

    public static StationPercentils buildStationPercentil() {
        return new StationPercentils(10, 10, 10, 10, 10, 10, 10, 10, 10, 10);
    }

    public static StationRaw buildStationRaw() {
        return new StationRaw(buildStationsStatusDTO());
    }

    public static StationStatistics buildStationStatistics() {
        return new StationStatistics(20, 12.3, 4.12);
    }
}
