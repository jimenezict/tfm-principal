package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import com.uoctfm.principal.repository.load.timeseries.PolicyPool;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.stream.Collectors.toList;

@Service
public class TimeseriesDatabaseService extends AbstractDatabaseService {

    @Autowired
    PolicyPool policyPool;

    @Override
    public void initialize() {
        policyPool.createDatabaseIfNotExists(super.processName);
    }

    @Override
    public void saveRaw(StationRaw stationRaw) {
        List<Point> rawPoints = stationRaw.getStationStatusDTO().getStationList().values()
                .stream()
                .map(x -> {
                    return Point
                            .measurement("raw")
                            .time(System.currentTimeMillis(), MILLISECONDS)
                            .addField("station", x.getId())
                            .addField("numbicicles",x.getNumBicicles())
                            .addField("percentages",x.getPercentage())
                            .addField("percentil",x.getPercentil())
                            .addField("size",x.getSizeStation())
                            .build();
                })
                .collect(toList());
    }

    @Override
    public void savePercentils(StationPercentils stationPercentils, StationStatistics stationStatistics) {
    }

    ;

    @Override
    public void saveDerived(StationDerived stationDerived) {
    }

    ;

}
