package com.uoctfm.principal.service.extraction;

import com.uoc.tfm.commons.domain.StationsLocation;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocationImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static com.uoctfm.principal.TestBuildHelper.buildStationsLocation;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StationLocationImplTest {

    private static final String DUMMY_SCB_TEXT = "http://localhost:8080/list-of-station/";

    @InjectMocks
    private final StationLocation underTest = new StationLocationImpl();

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getListStationStatus_shouldReturnNullOObject_whenItIsCallToAValidEndpoint() {
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsLocation.class))
                .thenReturn(null);

        StationsLocationDTO stationsLocationDTO = underTest.getListLocationStatus(DUMMY_SCB_TEXT);

        assertThat(stationsLocationDTO).isNull();
    }

    @Test
    public void getListStationStatus_shouldThrownException_whenItFailsToCallEndpoint() {
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsLocation.class))
                .thenThrow(RestClientException.class);

        StationsLocationDTO stationsLocationDTO = underTest.getListLocationStatus(DUMMY_SCB_TEXT);
        assertThat(stationsLocationDTO).isNull();
    }

    @Test
    public void getListStationStatus_shouldReturnStationStatusDTOObjectAsOnTheBuilder_whenItIsCallToAValidEndpoint() {
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsLocation.class))
                .thenReturn(buildStationsLocation());

        StationsLocationDTO stationsLocationDTO = underTest.getListLocationStatus(DUMMY_SCB_TEXT);

        assertThat(stationsLocationDTO).isNotNull();
        assertThat(stationsLocationDTO.getLocationList().size()).isEqualTo(5);
        assertThat(stationsLocationDTO.getLocationList().get(3).getId()).isEqualTo(3);
        assertThat(stationsLocationDTO.getLocationList().get(3).getLatitude()).isEqualTo(0);
        assertThat(stationsLocationDTO.getLocationList().get(3).getLongitude()).isEqualTo(0);
        assertThat(stationsLocationDTO.getLocationList().get(3).getAddress()).isEqualTo("Alpens");

    }

}