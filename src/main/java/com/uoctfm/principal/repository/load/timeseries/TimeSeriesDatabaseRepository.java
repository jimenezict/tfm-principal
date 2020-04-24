package com.uoctfm.principal.repository.load.timeseries;

import org.influxdb.dto.BatchPoints;

public interface TimeSeriesDatabaseRepository {

    public void saveListPoint(InfluxConnector influxConnector, BatchPoints point);

}
