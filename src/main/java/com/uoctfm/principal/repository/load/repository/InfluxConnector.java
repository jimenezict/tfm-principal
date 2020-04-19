package com.uoctfm.principal.repository.load.repository;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

import static java.util.Objects.isNull;

public class InfluxConnector {

    private static InfluxDB influxConnector = null;
    private String influxConnectionString = "http://localhost:8086";

    private InfluxConnector() {
        influxConnector = InfluxDBFactory.connect(influxConnectionString, "uoc", "uoc");
    }

    public static InfluxDB getInfluxConnector() {
        if(isNull(influxConnector)){
            new InfluxConnector();
        }
        return influxConnector;
    }
}
