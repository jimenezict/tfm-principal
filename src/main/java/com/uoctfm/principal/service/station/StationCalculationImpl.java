package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.domain.station.calculated.StationDerived;
import com.uoctfm.principal.domain.station.calculated.StationPercentils;
import com.uoctfm.principal.domain.station.calculated.StationRaw;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;

import static java.util.Objects.isNull;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class StationCalculationImpl implements StationCalculation {
    private Logger logger = getLogger(StationCalculationImpl.class);

    @Override
    public StationPercentils calculatePercentils(StationsStatusDTO stationsStatusDTO) {

        logger.info("Starting the calculations of the percentils for {} over {} stations",
                "TBD",
                stationsStatusDTO.getNumberStations());
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
        logger.info("Starting the calculations of the derivates for {} over {} stations",
                "TBD",
                stationsStatusDTO.getNumberStations());

        if (isNull(lastStationsStatusDTO)) {
            logger.warn("There is no sample from the last capture, all values will be zero");
            return emptyStationCalculation(stationsStatusDTO);
        }
        return stationCalculation(stationsStatusDTO, lastStationsStatusDTO);
    }

    private StationDerived emptyStationCalculation(StationsStatusDTO stationsStatusDTO) {
        StationDerived stationDerived = new StationDerived();
        stationsStatusDTO
                .getStationList()
                .keySet()
                .forEach(id -> {
                    stationDerived.addStationStatus(id, 0);
                });
        stationDerived.hasNoPreviousStatus();
        return stationDerived;
    }

    private StationDerived stationCalculation(StationsStatusDTO stationsStatusDTO, StationsStatusDTO lastStationsStatusDTO) {
        StationDerived stationDerived = new StationDerived();
        HashSet<Integer> unionKeys = new HashSet<>(stationsStatusDTO.getStationList().keySet());
        unionKeys.addAll(lastStationsStatusDTO.getStationList().keySet());
        unionKeys.forEach(id -> {
            Integer newValue =
                    stationsStatusDTO.getStationList().containsKey(id) ?
                    stationsStatusDTO.getStationList().get(id).getNumBicicles() : 0;
            Integer oldValue =
                    lastStationsStatusDTO.getStationList().containsKey(id) ?
                    lastStationsStatusDTO.getStationList().get(id).getNumBicicles() : 0;
            stationDerived.addStationStatus(id, newValue - oldValue);
        });
        return stationDerived;
    }

    @Override
    public StationRaw calculateRaw(StationsStatusDTO stationsStatusDTO) {
        logger.info("Starting the calculations of the calculateRaw for {} over {} stations",
                "TBD",
                stationsStatusDTO.getNumberStations());
        return new StationRaw(stationsStatusDTO);
    }
}
