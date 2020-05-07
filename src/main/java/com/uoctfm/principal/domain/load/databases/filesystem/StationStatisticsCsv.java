package com.uoctfm.principal.domain.load.databases.filesystem;

import com.uoctfm.principal.domain.transformation.StationStatistics;

import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class StationStatisticsCsv extends BaseCsv implements Serializable {

    private int entropy;
    private double entropyNormalized;
    private double average;

    public StationStatisticsCsv(StationStatistics stationStatistics) {
        super();
        this.entropy = stationStatistics.getEntropy();
        this.entropyNormalized = stationStatistics.getEntropyNormalized();
        this.average = stationStatistics.getAverage();
    }

    public int getEntropy() {
        return entropy;
    }

    public double getEntropyNormalized() {
        return entropyNormalized;
    }

    public double getAverage() {
        return average;
    }

}
