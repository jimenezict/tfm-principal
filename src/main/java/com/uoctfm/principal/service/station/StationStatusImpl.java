package com.uoctfm.principal.service.station;

import com.uoc.tfm.commons.domain.StationsStatus;
import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static java.util.Objects.isNull;
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
            return nonNull(stationsStatus) ? mapStationStatus(stationsStatus) : null;
        } catch (RestClientException e) {
            logger.error("Fail on capturing from the end-point {}", systemStationEndPoints);
        } catch (HttpMessageConversionException e) {
            logger.error("Fail on parsing from end-point {}: ", systemStationEndPoints, e);
        }
        return null;
    }

    private StationsStatusDTO mapStationStatus(StationsStatus stationsStatus) {
        StationsStatusDTO stationStatusDTO = new StationsStatusDTO();

        stationsStatus.getStationStatusList().forEach(x -> {
            stationStatusDTO.addStation(new Station(x.getId(), x.getOccupacy(), x.getSize()));
        });

        return stationStatusDTO;
    }

    @Override
    public StationsStatusDTO getLastListStationStatus(Integer id) {
        // TO-DO: need to call to repository once its implemented the table
        StationsStatusDTO stationsStatusDTO = null;
        if (isNull(stationsStatusDTO)) {
            logger.warn("Not found any sample for {} on database", id);
            return null;
        }
        boolean wasOnLastFiveMinutes = stationsStatusDTO.getExecutionDateTime().isAfter(LocalDateTime.now().minusMinutes(5));
        if (!wasOnLastFiveMinutes) logger.warn("Not found any sample for {} on database, for the last 5 minutes", id);
        return wasOnLastFiveMinutes ? stationsStatusDTO : null;
    }
}
