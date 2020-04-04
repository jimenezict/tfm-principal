package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.domain.station.calculated.StationDerived;
import com.uoctfm.principal.domain.station.calculated.StationPercentils;
import com.uoctfm.principal.domain.station.calculated.StationRaw;

public class StationCalculationImpl implements StationCalculation {
    @Override
    public StationPercentils calculatePercentils(StationsStatusDTO stationsStatusDTO) {
        int[] intArray = new int[10];

        for(Station station : stationsStatusDTO.getStationList().values()){
            intArray[station.getPercentil()]++;
        }

        return new StationPercentils(
                intArray[0] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[1] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[2] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[3] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[4] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[5] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[6] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[7] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[8] * 100/ stationsStatusDTO.getNumberStations(),
                intArray[9] * 100/ stationsStatusDTO.getNumberStations()
        );
    }

    @Override
    public StationDerived calculateDerived(StationsStatusDTO stationsStatusDTO, StationsStatusDTO lastStationsStatusDTO) {
        return null;
    }

    @Override
    public StationRaw calculateRaw(StationsStatusDTO stationsStatusDTO) {
        return null;
    }
}
