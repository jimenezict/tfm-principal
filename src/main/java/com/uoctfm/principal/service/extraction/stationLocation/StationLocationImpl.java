package com.uoctfm.principal.service.extraction.stationLocation;

import com.uoc.tfm.commons.domain.StationsLocation;
import com.uoctfm.principal.domain.extraction.Location;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
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
public class StationLocationImpl implements StationLocation {

    private Logger logger = LoggerFactory.getLogger(StationLocationImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public StationsLocationDTO getListLocationStatus(String systemLocationEndPoints) {
        StationsLocation stationsLocation;
        try {
            stationsLocation = restTemplate.getForObject(systemLocationEndPoints, StationsLocation.class);
            logger.info("Captured StationsLocationDTO from the end-point {}", systemLocationEndPoints);
            return nonNull(stationsLocation) ? mapStationLocation(stationsLocation, systemLocationEndPoints) : null;
        } catch (RestClientException e) {
            logger.error("Fail on capturing from the end-point {}", systemLocationEndPoints);
        } catch (HttpMessageConversionException e) {
            logger.error("Fail on parsing from end-point {}: ", systemLocationEndPoints, e);
        } catch (Exception e) {
            logger.error("Fail with general exception from end-point {}: ", systemLocationEndPoints, e);
        }

        return null;
    }

    private StationsLocationDTO mapStationLocation(StationsLocation stationsLocation, String systemLocationEndPoints) {
        StationsLocationDTO stationLocationDTO = new StationsLocationDTO();
        if(stationsLocation.getStationLocationList().isEmpty()) {
            logger.warn("Due to an unexpected reason, the returned size for the call to {} is empty", systemLocationEndPoints);
            return null;
        }
        stationsLocation
                .getStationLocationList()
                .stream()
                .forEach(x -> {
                    stationLocationDTO.addLocation(new Location(x.getId(), x.getLatitude(), x.getLongitude(), x.getAddress()));
                });
        return stationLocationDTO;
    }
}
