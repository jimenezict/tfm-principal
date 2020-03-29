package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.repository.configuration.SystemSampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;

@Service
public class StationStatusImpl implements StationStatus {

    private Logger logger = LoggerFactory.getLogger(StationStatusImpl.class);

    @Autowired
    private SystemSampleRepository systemSampleRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public StationsStatusDTO getListStationStatus(String systemStationEndPoints) {
        try {
            StationsStatusDTO stationsStatusDTO = restTemplate.getForObject(systemStationEndPoints, StationsStatusDTO.class);
            logger.info("Captured StationsStatusDTO from the end-point {}", systemStationEndPoints);
            return stationsStatusDTO;
        } catch (RestClientException e) {
            logger.error("Fail on capturing StationsStatusDTO from the end-point {}", systemStationEndPoints);
        }
        return null;
    }

    @Override
    public StationsStatusDTO getLastListStationStatus(Integer id) {
        StationsStatusDTO systemStationDTO = systemSampleRepository.findById(id);
        if (isNull(systemStationDTO)) {
            logger.warn("Not found any sample for {} on database", id);
            return null;
        }
        boolean wasOnLastFiveMinutes = systemStationDTO.getExecutionDateTime().isAfter(LocalDateTime.now().minusMinutes(5));
        if (!wasOnLastFiveMinutes) logger.warn("Not found any sample for {} on database, for the last 5 minutes", id);
        return wasOnLastFiveMinutes ? systemStationDTO : null;
    }
}
