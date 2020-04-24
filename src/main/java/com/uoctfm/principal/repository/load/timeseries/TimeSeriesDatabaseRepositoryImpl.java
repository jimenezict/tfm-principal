package com.uoctfm.principal.repository.load.timeseries;

import org.influxdb.dto.BatchPoints;
import org.springframework.stereotype.Repository;

@Repository
public class TimeSeriesDatabaseRepositoryImpl implements TimeSeriesDatabaseRepository {

    @Override
    public void saveListPoint(InfluxConnector influxConnector, BatchPoints point) {
        influxConnector.getInfluxConnector().write(point);
    }

}
