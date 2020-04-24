package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.extraction.Station;
import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import com.uoctfm.principal.repository.load.timeseries.InfluxConnector;
import com.uoctfm.principal.repository.load.timeseries.PolicyPool;
import com.uoctfm.principal.repository.load.timeseries.TimeSeriesDatabaseRepository;
import com.uoctfm.principal.repository.load.timeseries.TimeSeriesDatabaseRepositoryImpl;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static java.util.Arrays.asList;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.stream.Collectors.toList;

@Service
public class TimeseriesDatabaseService extends AbstractDatabaseService {

    InfluxConnector influxConnector;
    TimeSeriesDatabaseRepository timeSeriesDatabaseRepository;

    @Override
    public void initialize() {
        influxConnector = new InfluxConnector(super.processName);
        timeSeriesDatabaseRepository = new TimeSeriesDatabaseRepositoryImpl();
    }

    @Override
    public void saveRaw() {
        List<Point> rawPoints = stationRaw.getStationStatusDTO().getStationList().values()
                .stream()
                .map(x -> generateRawPoint(x))
                .collect(toList());

        timeSeriesDatabaseRepository.saveListPoint(influxConnector, mapToBatchPoints(rawPoints));
    }

    @Override
    public void savePercentils() {
        timeSeriesDatabaseRepository.saveListPoint(influxConnector, mapToBatchPoints(asList(generatePercentilPoint(stationPercentils))));
        timeSeriesDatabaseRepository.saveListPoint(influxConnector, mapToBatchPoints(asList(generateStatisticsPoint(stationStatistics))));
    }

    @Override
    public void saveDerived() {
        List<Point> derivedPoints = stationDerived.getStationsStatusDTO().keySet()
                .stream()
                .map(x -> generateDerivedPoint(x, stationDerived.getStationsStatusDTO().get(x)))
                .collect(toList());

        timeSeriesDatabaseRepository.saveListPoint(influxConnector, mapToBatchPoints(derivedPoints));
    }

    private Point generateDerivedPoint(Integer key, Integer value) {
        return Point.measurement("derived")
                .time(System.currentTimeMillis(), MILLISECONDS)
                .tag("station", key.toString())
                .addField("difference", value.toString())
                .build();
    }

    private Point generateStatisticsPoint(StationStatistics stationStatistics) {
        return Point.measurement("statistics")
                .time(System.currentTimeMillis(), MILLISECONDS)
                .tag("station", "global")
                .addField("average", stationStatistics.getAverage())
                .addField("entropy", stationStatistics.getEntropy())
                .addField("entropyNormalized", stationStatistics.getEntropyNormalized())
                .build();
    }

    private Point generatePercentilPoint(StationPercentils stationPercentils) {
        return Point.measurement("percentils")
                .time(System.currentTimeMillis(), MILLISECONDS)
                .tag("station", "global")
                .addField("p0", stationPercentils.getP0())
                .addField("p1", stationPercentils.getP1())
                .addField("p2", stationPercentils.getP2())
                .addField("p3", stationPercentils.getP3())
                .addField("p4", stationPercentils.getP4())
                .addField("p5", stationPercentils.getP5())
                .addField("p6", stationPercentils.getP6())
                .addField("p7", stationPercentils.getP7())
                .addField("p8", stationPercentils.getP8())
                .addField("p9", stationPercentils.getP9())
                .build();
    }

    private Point generateRawPoint(Station x) {
        return Point
                .measurement("raw")
                .time(System.currentTimeMillis(), MILLISECONDS)
                .tag("station", x.getId().toString())
                .addField("numbicicles",x.getNumBicicles())
                .addField("percentages",x.getPercentage())
                .addField("percentil",x.getPercentil())
                .addField("size",x.getSizeStation())
                .build();
    }

    private BatchPoints mapToBatchPoints(List<Point> rawPoints) {
        BatchPoints batchPoints = BatchPoints
                .database(super.processName)
                .retentionPolicy("defaultPolicy")
                .build();

        rawPoints.forEach(x -> batchPoints.point(x));
        return batchPoints;
    }

}
