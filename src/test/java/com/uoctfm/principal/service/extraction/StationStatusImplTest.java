package com.uoctfm.principal.service.extraction;

import com.uoc.tfm.commons.domain.StationsStatus;
import com.uoctfm.principal.domain.extraction.Station;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import com.uoctfm.principal.service.extraction.stationStatus.StationStatus;
import com.uoctfm.principal.service.extraction.stationStatus.StationStatusImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.uoctfm.principal.TestBuildHelper.buildStationsStatusDTO;
import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StationStatusImplTest {

    private static final String DUMMY_SCB_TEXT = "http://localhost:8080/list-of-station/";

    @InjectMocks
    private StationStatus underTest = new StationStatusImpl();

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getListStationStatus_shouldReturnStationStatusDTOObject_whenItIsCallToAValidEndpointButHasNoStationsOnTheList(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsStatus.class))
          .thenReturn(new StationsStatus());

        StationsStatusDTO stationsStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationsStatusDTO).isNull();
    }

    @Test
    public void getListStationStatus_shouldReturnNullOObject_whenItIsCallToAValidEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsStatus.class))
                .thenReturn(null);

        StationsStatusDTO stationsStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationsStatusDTO).isNull();
    }

    @Test
    public void getListStationStatus_shouldThrownException_whenItFailsToCallEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsStatus.class))
                .thenThrow(RestClientException.class);

        StationsStatusDTO stationsStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);
        assertThat(stationsStatusDTO).isNull();
    }

}