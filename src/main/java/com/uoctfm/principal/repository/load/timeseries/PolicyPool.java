package com.uoctfm.principal.repository.load.timeseries;

import org.influxdb.InfluxDB;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import static com.uoctfm.principal.repository.load.timeseries.InfluxConnector.getInfluxConnector;
import static org.slf4j.LoggerFactory.getLogger;

@Component
public class PolicyPool {

    Logger logger = getLogger(PolicyPool.class);

    public void createDatabaseIfNotExists(String database) {
        InfluxDB influxConnector = getInfluxConnector();
        if(!influxConnector.describeDatabases().contains(database))
        {
            influxConnector.createDatabase(database);
            influxConnector.createRetentionPolicy(database + "Policy", database, "1d", 1, false);
            logger.info("Database {} created with its own policy of 1d retention", database);
        }
    }

}
