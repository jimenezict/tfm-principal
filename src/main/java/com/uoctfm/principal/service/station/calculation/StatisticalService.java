package com.uoctfm.principal.service.station.calculation;

import com.uoctfm.principal.domain.station.StationsStatusDTO;

public interface StatisticalService {

    public int calculateEntropy(StationsStatusDTO stationsStatus);

    public double calculateAverage(StationsStatusDTO stationsStatus);

}
