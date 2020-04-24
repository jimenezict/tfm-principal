package com.uoctfm.principal;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.Station;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.now;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class TestBuildHelper {

    public static SystemConfigurationDTO buildSystemConfigurationDTO(){
        SystemConfigurationDTO systemConfiguration = new SystemConfigurationDTO();
        systemConfiguration.setId(0);
        systemConfiguration.setName("Barcino");
        systemConfiguration.setSystemStationEndPoint("http://localhost:8483/status/");
        systemConfiguration.setSystemLocationEndPoint("http://localhost:8483/location/");
        systemConfiguration.setSaveInFileSystem(false);
        systemConfiguration.setSaveInGIS(false);
        systemConfiguration.setSaveInTimeSeries(false);

        return systemConfiguration;
    }

    public static List<SystemConfigurationDTO> buildSystemConfigurationListDTO(){
        List<SystemConfigurationDTO> systemConfigurationList = new ArrayList<>();
        systemConfigurationList.add(buildSystemConfigurationDTO());
        systemConfigurationList.add(buildSystemConfigurationDTO());

        return systemConfigurationList;
    }

    public static StationsStatusDTO buildStationsStatusDTO() {
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(now());

        stationsStatusDTO.addStation(new Station(1, 10, 30));
        stationsStatusDTO.addStation(new Station(2, 0, 30));
        stationsStatusDTO.addStation(new Station(3, 22, 30));

        return stationsStatusDTO;
    }

    public static StationsStatusDTO buildStationsStatusDTO_higherId() {
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(now());

        stationsStatusDTO.addStation(new Station(4, 10, 30));
        stationsStatusDTO.addStation(new Station(5, 0, 30));
        stationsStatusDTO.addStation(new Station(6, 22, 30));

        return stationsStatusDTO;
    }

    public static BatchPoints buildDummyBatchPoint(String database) {
        BatchPoints batchPoints = BatchPoints
                .database(database)
                .retentionPolicy("defaultPolicy")
                .build();

        Point p1 = Point.measurement("dummy").time(System.currentTimeMillis(), MILLISECONDS).addField("id", 1).build();
        Point p2 = Point.measurement("dummy").time(System.currentTimeMillis(), MILLISECONDS).addField("id", 2).build();
        Point p3 = Point.measurement("dummy").time(System.currentTimeMillis(), MILLISECONDS).addField("id", 3).build();

        batchPoints.point(p1);
        batchPoints.point(p2);
        batchPoints.point(p3);

        return batchPoints;
    }
}
