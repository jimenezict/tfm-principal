package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.StationStatusDTO;
import com.uoctfm.principal.repository.configuration.SystemSampleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StationStatusImplTest {

    private static final String DUMMY_SCB_TEXT = "http://localhost:8080/list-of-station/";

    @InjectMocks
    private StationStatus underTest = new StationStatusImpl();

    @Mock
    private SystemSampleRepository systemSampleRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getListStationStatus_shouldReturnStationStatusDTOObject_whenItIsCallToAValidEndpoint(){
        Mockito
                .when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationStatusDTO.class))
          .thenReturn(new StationStatusDTO());

        StationStatusDTO stationStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationStatusDTO).isNotNull();
        assertThat(stationStatusDTO).isInstanceOf(StationStatusDTO.class);
    }

    @Test
    public void getListStationStatus_shouldReturnNullOObject_whenItIsCallToAValidEndpoint(){
        Mockito
                .when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationStatusDTO.class))
                .thenReturn(null);

        StationStatusDTO stationStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationStatusDTO).isNull();
    }

    @Test
    public void getListStationStatus_shouldThrownException_whenItFailsToCallEndpoint(){
        Mockito
                .when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationStatusDTO.class))
                .thenThrow(RestClientException.class);

        StationStatusDTO stationStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);
        assertThat(stationStatusDTO).isNull();
    }

}