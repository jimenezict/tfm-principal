package com.uoctfm.principal.repository.load.timeseries;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class InfluxConnector {

    Logger logger = LoggerFactory.getLogger(InfluxConnector.class);
    private static InfluxDB influxConnector = null;

    private String influxConnection = "http://localhost:8086";

    private InfluxConnector() {
        influxConnector = InfluxDBFactory.connect(influxConnection, "uoc", "uoc");
        logger.info("Created a connection string to: {} with username {} and version {}.",
                influxConnection, "uoc", influxConnector.version());
    }

    public static InfluxDB getInfluxConnector() {
        if(isNull(influxConnector)){
            new InfluxConnector();
        }
        return influxConnector;
    }
}
