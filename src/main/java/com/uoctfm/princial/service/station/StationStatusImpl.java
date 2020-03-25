package com.uoctfm.princial.service.station;

import com.uoctfm.princial.domain.station.StationStatusDTO;
import com.uoctfm.princial.repository.configuration.SystemSampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Service
public class StationStatusImpl implements StationStatus {

    private Logger logger = LoggerFactory.getLogger(StationStatusImpl.class);

    @Autowired
    private SystemSampleRepository systemSampleRepository;

    @Override
    public StationStatusDTO getListStationStatus(String systemStationEndPoints) {
        RestTemplate restTemplate = new RestTemplate();

        try {
            StationStatusDTO stationStatusDTO = restTemplate.getForObject(systemStationEndPoints, StationStatusDTO.class);
            logger.info("Captured StationStatusDTO from the end-point {}", systemStationEndPoints);
            return stationStatusDTO;
        } catch (RestClientException e) {
            logger.error("Fail on capturing StationStatusDTO from the end-point {}", systemStationEndPoints);
        }
        return null;
    }

    @Override
    public StationStatusDTO getLastListStationStatus(Integer id) {
        StationStatusDTO systemStationDTO = systemSampleRepository.findById(id);
        if (isNull(systemStationDTO)) {
            logger.warn("Not found any sample for {} on database", id);
            return null;
        }
        boolean wasOnLastFiveMinutes = systemStationDTO.getExecutionDateTime().isAfter(LocalDateTime.now());
        if (!wasOnLastFiveMinutes) logger.warn("Not found any sample for {} on database, for the last 5 minutes", id);
        return wasOnLastFiveMinutes ? systemStationDTO : null;
    }
}
