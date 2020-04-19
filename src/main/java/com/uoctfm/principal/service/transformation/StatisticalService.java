package com.uoctfm.principal.service.transformation;

import com.uoctfm.principal.domain.extraction.StationsStatusDTO;

public interface StatisticalService {

    public int calculateEntropy(StationsStatusDTO stationsStatus);

    public double calculateAverage(StationsStatusDTO stationsStatus);

}
