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
        try {
            StationsLocation stationsLocation = restTemplate.getForObject(systemLocationEndPoints, StationsLocation.class);
            logger.info("Captured StationsLocationDTO from the end-point {}", systemLocationEndPoints);
            return nonNull(stationsLocation) ? mapStationLocation(stationsLocation) : null;
        } catch (RestClientException e) {
            logger.error("Fail on capturing from the end-point {}", systemLocationEndPoints);
        } catch (HttpMessageConversionException e) {
            logger.error("Fail on parsing from end-point {}: ", systemLocationEndPoints, e);
        }
        return null;
    }

    private static StationsLocationDTO mapStationLocation(StationsLocation stationsLocation) {
        StationsLocationDTO stationLocationDTO = new StationsLocationDTO();
        stationsLocation
                .getStationLocationList()
                .stream()
                .forEach(x -> {
                    stationLocationDTO.addLocation(new Location(x.getId(), x.getLatitude(), x.getLongitude(), x.getAddress()));
                });
        return stationLocationDTO;
    }
}