package com.uoctfm.principal.service.transformation;

import com.uoctfm.principal.domain.extraction.Location;
import com.uoctfm.principal.domain.extraction.Station;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.gis.GlobalStatistical;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;

@Service
public class LocationAndStationMergeServiceImpl implements LocationAndStationMergeService {

    Logger logger = getLogger(LocationAndStationMergeServiceImpl.class);

    @Override
    public GlobalStatistical mergeStatisticalDate(StationsLocationDTO stationLocationDTO, StationStatistics stationStatistics) {
        Location location = stationLocationDTO.getLocationList().get(1);
        GeometryFactory geometryFactory = new GeometryFactory();

        GlobalStatistical globalStatistical = new GlobalStatistical();
        globalStatistical.setPoint(geometryFactory.createPoint(new Coordinate(location.getLatitude(), location.getLongitude())));
        if (nonNull(stationStatistics)) {
            globalStatistical.setEntropyNormalized(stationStatistics.getEntropyNormalized());
            globalStatistical.setEntropy(stationStatistics.getEntropy());
            globalStatistical.setAverage(stationStatistics.getAverage());
        }
        return globalStatistical;
    }

    @Override
    public GlobalStatistical updateStatisticalData(GlobalStatistical globalStatistical, StationStatistics stationStatistics) {
        globalStatistical.setAverage(stationStatistics.getAverage());
        globalStatistical.setEntropy(stationStatistics.getEntropy());
        globalStatistical.setEntropyNormalized(stationStatistics.getEntropyNormalized());

        return globalStatistical;
    }

    @Override
    public Set<StationSystemRaw> mergeRawData(StationsLocationDTO stationLocationDTO, StationRaw stationRaw) {
        Set<StationSystemRaw> stationSystemRawSet = new HashSet<>();
        Map<Integer, Station> stationList = stationRaw.getStationStatusDTO().getStationList();
        GeometryFactory geometryFactory = new GeometryFactory();

        stationList.values().forEach(stationId -> {
            Station station = stationList.get(stationId.getId());
            Location location = stationLocationDTO.getLocationList().get(stationId.getId());
            StationSystemRaw stationSystemRaw = new StationSystemRaw();
            stationSystemRaw.setStation(station.getId());
            stationSystemRaw.setNumBicicles(station.getNumBicicles());
            stationSystemRaw.setStationSize(station.getSizeStation());
            if (isNull(location)) {
                logger.warn("Fail setting to location point for {}, location is null", station.getId());
            } else if (isNull(location.getLatitude()) || isNull(location.getLongitude())) {
                logger.warn("Fail setting to location point for {} and address {} ", location.getId(), location.getAddress());
            } else {
                stationSystemRaw.setPoint(geometryFactory.createPoint(new Coordinate(location.getLatitude(), location.getLongitude())));
            }

            stationSystemRawSet.add(stationSystemRaw);
        });

        return stationSystemRawSet;
    }
}
