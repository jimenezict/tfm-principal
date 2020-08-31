package com.uoctfm.principal.repository.load.timeseries;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.concurrent.TimeUnit.MILLISECONDS;

public class InfluxConnector {

    Logger logger = LoggerFactory.getLogger(InfluxConnector.class);
    private InfluxDB influxDb = null;

    private String connectionString = "http://localhost:8086";

    public InfluxConnector(String database) {
        influxDb = InfluxDBFactory.connect(connectionString);
        if(!influxDb.describeDatabases().contains(database)) {
            influxDb.createDatabase(database);
            logger.info("Creating {}", database);
        }
        influxDb.createRetentionPolicy("defaultPolicy", database, "10d", 1, true);
        influxDb.setLogLevel(InfluxDB.LogLevel.BASIC);
        influxDb.setDatabase(database);

        logger.info("Created a connection string to: {} with username {} and version {}.",
                connectionString, database, influxDb.version());
    }

    public InfluxDB getInfluxConnector() {
        return influxDb;
    }

    public void closeInfluxConnector() {
        if(nonNull(influxDb)) {
            influxDb.close();
            return;
        };
        logger.error("Connector was not close to {} with username {}");
    }
}
