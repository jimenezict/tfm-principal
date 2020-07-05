package com.uoctfm.principal.service.extraction.stationStatus;

import com.uoc.tfm.commons.domain.StationsStatus;
import com.uoctfm.principal.domain.extraction.Station;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static java.time.LocalDateTime.now;
import static java.util.Objects.nonNull;

@Service
public class StationStatusImpl implements StationStatus {

    private Logger logger = LoggerFactory.getLogger(StationStatusImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public StationsStatusDTO getListStationStatus(String systemStationEndPoints) {
        try {
            StationsStatus stationsStatus = restTemplate.getForObject(systemStationEndPoints, StationsStatus.class);
            logger.info("Captured StationsStatusDTO from the end-point {}", systemStationEndPoints);
            return nonNull(stationsStatus) ? mapStationStatus(stationsStatus, systemStationEndPoints) : null;
        } catch (RestClientException e) {
            logger.error("Fail on capturing from the end-point {}", systemStationEndPoints);
        } catch (HttpMessageConversionException e) {
            logger.error("Fail on parsing from end-point {}: ", systemStationEndPoints, e);
        } catch (Exception e) {
            logger.error("Fail with general exception from end-point {}: ", systemStationEndPoints, e);
        }
        return null;
    }

    private StationsStatusDTO mapStationStatus(StationsStatus stationsStatus, String systemStationEndPoints) {
        StationsStatusDTO stationStatusDTO = new StationsStatusDTO();
        stationStatusDTO.setExecutionDateTime(stationsStatus.getTimestamp());
        if(stationsStatus.getStationStatusList().isEmpty()) {
            logger.warn("Due to an unexpected reason, the returned size for the call to {} is empty", systemStationEndPoints);
            return null;
        }

        stationsStatus.getStationStatusList().forEach(x -> {
            stationStatusDTO.addStation(new Station(x.getId(), x.getOccupacy(), x.getSize()));
        });

        return stationStatusDTO;
    }
}
