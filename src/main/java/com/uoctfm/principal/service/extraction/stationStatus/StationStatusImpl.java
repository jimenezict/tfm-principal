package com.uoctfm.principal.service.extraction.stationStatus;

import com.uoc.tfm.commons.domain.StationsStatus;
import com.uoctfm.principal.domain.configuration.LastSampleDTO;
import com.uoctfm.principal.domain.extraction.Station;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import com.uoctfm.principal.repository.configuration.LastSampleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static java.time.LocalDateTime.now;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class StationStatusImpl implements StationStatus {

    private Logger logger = LoggerFactory.getLogger(StationStatusImpl.class);

    @Autowired
    private LastSampleRepository lastSampleRepository;

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

    @Override
    public StationsStatusDTO getLastStationStatus(Integer id) {
        LastSampleDTO lastSample = new LastSampleDTO();
        try {
            lastSample = lastSampleRepository.findById(id);
        }catch (Exception e){
            logger.error("Fail on recovering last sample from the id: {}.", id, e.getMessage());
            return null;
        }

        if (isNull(lastSample)) {
            logger.warn("Not found any sample for {} on database", id);
            return null;
        }

        StationsStatusDTO stationsStatusDTO;
        try {
            stationsStatusDTO = mapStationStatus(lastSample);
        } catch (Exception e) {
            logger.error("Fail on mapping last sample from id {} to StationsStatusDTO.", id, e.getMessage());
            return null;
        }

        boolean wasOnLastFiveMinutes = stationsStatusDTO.getExecutionDateTime().isAfter(now().minusMinutes(60));
        if (!wasOnLastFiveMinutes) {
            logger.warn("Not found any sample for {} on database, for the last hour", id);
        } else {
            logger.info("Success: it was found a sample for {} on database, for the last hour", id);
        }
        return wasOnLastFiveMinutes ? stationsStatusDTO : null;
    }

    @Override
    public void saveLastStationStatus(StationsStatusDTO stationsStatusDTO, Integer id) {
        LastSampleDTO lastSampleDTO = mapLastSample(stationsStatusDTO, id);

        try {
            lastSampleRepository.save(lastSampleDTO);
        }catch (Exception e){
            logger.error("Fail on saving last sample from the id: {}.", id, e.getMessage());
            return;
        }

        logger.info("The sample from the id: {} has been saved.", id);
    }

    private LastSampleDTO mapLastSample(StationsStatusDTO stationsStatusDTO, Integer id) {
        LastSampleDTO lastSampleDTO = new LastSampleDTO();
        lastSampleDTO.setId(id);
        lastSampleDTO.setTime(now());
        lastSampleDTO.setLastSample(stationsStatusDTO.getStationList());

        return lastSampleDTO;
    }

    private StationsStatusDTO mapStationStatus(StationsStatus stationsStatus, String systemStationEndPoints) {
        StationsStatusDTO stationStatusDTO = new StationsStatusDTO();
        stationStatusDTO.setExecutionDateTime(stationsStatus.getTimestamp());
        if(stationStatusDTO.getStationList().size() == 0) {
            logger.warn("Due to an unexpected reason, the returned size for the call to {} is empty", systemStationEndPoints);
            return null;
        }

        stationsStatus.getStationStatusList().forEach(x -> {
            stationStatusDTO.addStation(new Station(x.getId(), x.getOccupacy(), x.getSize()));
        });

        return stationStatusDTO;
    }

    private StationsStatusDTO mapStationStatus(LastSampleDTO lastSample) {
        StationsStatusDTO stationStatusDTO = new StationsStatusDTO();
        stationStatusDTO.setExecutionDateTime(lastSample.getTime());
        stationStatusDTO.setStationList(lastSample.getLastSample());

        return stationStatusDTO;
    }}
