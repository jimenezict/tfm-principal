package com.uoctfm.principal.service.transformation;

import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicInteger;

@Service
public class StatisticalServiceImpl implements StatisticalService{

    @Override
    public int calculateEntropy(StationsStatusDTO stationsStatus) {
        AtomicInteger sum = new AtomicInteger();
        return stationsStatus.getStationList()
                .values()
                .stream()
                .mapToInt(station -> calculateIndividualEntropy(station.getPercentil()))
                .sum();
    }

    @Override
    public double calculateAverage(StationsStatusDTO stationsStatus) {
        return stationsStatus.getStationList()
                .values()
                .stream()
                .mapToInt(station -> station.getPercentil())
                .average()
                .orElse(0.0);
    }

    private int calculateIndividualEntropy(int percentil) {
        switch (percentil) {
            case 0:
            case 9:
                return 3;
            case 1:
            case 8:
                return 1;
        }
        return 0;
    }

}
