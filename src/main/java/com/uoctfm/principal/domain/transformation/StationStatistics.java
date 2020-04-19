package com.uoctfm.principal.domain.transformation;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class StationStatistics {

    private int entropy;
    private double entropyNormalized;
    private double average;
    private LocalDateTime time;

    public StationStatistics(int entropy, double entropyNormalized, double average) {
        this.entropy = entropy;
        this.entropyNormalized = entropyNormalized;
        this.average = average;
        time = now();
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

    public LocalDateTime getTime(){
        return time;
    }
}
