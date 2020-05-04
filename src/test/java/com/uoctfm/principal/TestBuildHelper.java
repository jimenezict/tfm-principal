package com.uoctfm.principal;

import com.uoc.tfm.commons.domain.StationsLocation;
import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.Location;
import com.uoctfm.principal.domain.extraction.Station;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import com.uoctfm.principal.domain.load.databases.gis.GlobalStatistical;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static StationsLocation buildStationsLocation() {
        StationsLocation stationsLocation = new StationsLocation(LocalDate.now());

        stationsLocation.addStation(1,0,0,"Clot");
        stationsLocation.addStation(2,0,0,"SantVi");
        stationsLocation.addStation(3,0,0,"Alpens");
        stationsLocation.addStation(4,0,0,"Lima");
        stationsLocation.addStation(5,0,0,"Gracia");

        return stationsLocation;
    }

    public static StationsLocationDTO buildStationsLocationDTO() {
        StationsLocationDTO stationsLocationDTO = new StationsLocationDTO();
        stationsLocationDTO.setExecutionDateTime(now());
        stationsLocationDTO.addLocation(new Location(1,0,0,"Clot"));
        stationsLocationDTO.addLocation(new Location(2,0,0,"SantVi"));
        stationsLocationDTO.addLocation(new Location(3,0,0,"Alpens"));
        stationsLocationDTO.addLocation(new Location(4,0,0,"Lima"));
        stationsLocationDTO.addLocation(new Location(5,0,0,"Gracia"));

        return stationsLocationDTO;
    }

    public static GlobalStatistical buildGlobalStatistical() {
        GeometryFactory geometryFactory = new GeometryFactory();

        GlobalStatistical globalStatistical = new GlobalStatistical();
        globalStatistical.setAverage(0.1);
        globalStatistical.setEntropy(1);
        globalStatistical.setEntropyNormalized(0.2);
        globalStatistical.setPoint(geometryFactory.createPoint(new Coordinate(0, 0)));
        globalStatistical.setSystem(100000);

        return globalStatistical;
    }

    public static StationSystemRaw buildStationSystemRaw(Long id, Integer system, Integer station, com.vividsolutions.jts.geom.Point point, Integer stationSize, Integer numBicicles) {
        StationSystemRaw stationSystemRaw = new StationSystemRaw();
        stationSystemRaw.setId(id);
        stationSystemRaw.setSystem(system);
        stationSystemRaw.setStation(station);
        stationSystemRaw.setPoint(point);
        stationSystemRaw.setStationSize(stationSize);
        stationSystemRaw.setNumBicicles(numBicicles);

        return stationSystemRaw;
    }

    public static Set<StationSystemRaw> buildStationSystemRawSet() {
        Set<StationSystemRaw> stationSystemRawsSet = new HashSet<>();

        stationSystemRawsSet.add(buildStationSystemRaw(null, null, 1, null, 30, 10));
        stationSystemRawsSet.add(buildStationSystemRaw(null, null, 2, null, 30, 10));
        stationSystemRawsSet.add(buildStationSystemRaw(null, null, 3, null, 30, 10));
        stationSystemRawsSet.add(buildStationSystemRaw(null, null, 4, null, 30, 10));
        stationSystemRawsSet.add(buildStationSystemRaw(null, null, 5, null, 30, 10));

        return stationSystemRawsSet;
    }
}
