package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.StationsStatusDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StationStatusImplTest {

    private static final String DUMMY_SCB_TEXT = "http://localhost:8080/list-of-station/";

    @InjectMocks
    private StationStatus underTest = new StationStatusImpl();

//    @Mock
//    private SystemSampleRepository systemSampleRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getListStationStatus_shouldReturnStationStatusDTOObject_whenItIsCallToAValidEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsStatusDTO.class))
          .thenReturn(new StationsStatusDTO());

        StationsStatusDTO stationsStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationsStatusDTO).isNotNull();
        assertThat(stationsStatusDTO).isInstanceOf(StationsStatusDTO.class);
    }

    @Test
    public void getListStationStatus_shouldReturnNullOObject_whenItIsCallToAValidEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsStatusDTO.class))
                .thenReturn(null);

        StationsStatusDTO stationsStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationsStatusDTO).isNull();
    }

    @Test
    public void getListStationStatus_shouldThrownException_whenItFailsToCallEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsStatusDTO.class))
                .thenThrow(RestClientException.class);

        StationsStatusDTO stationsStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);
        assertThat(stationsStatusDTO).isNull();
    }
/*
    @Test
    public void getLastListStationStatus_shouldReturnNull_whenNotFindSampleById(){
        when(systemSampleRepository.findById(0)).thenReturn(null);

        assertThat(underTest.getLastListStationStatus(0)).isNull();

        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }

    @Test
    public void getLastListStationStatus_shouldReturnNull_whenFindSampleByIdButIsOld(){
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(LocalDateTime.of(2000, 1, 1, 0,0));
        when(systemSampleRepository.findById(0)).thenReturn(stationsStatusDTO);

        assertThat(underTest.getLastListStationStatus(0)).isNull();

        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }

    @Test
    public void getLastListStationStatus_shouldReturnNull_whenFindSampleByIdButIsNew(){
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(LocalDateTime.now());
        when(systemSampleRepository.findById(0)).thenReturn(stationsStatusDTO);

        StationsStatusDTO retStationsStatusDTO = underTest.getLastListStationStatus(0);

        assertThat(retStationsStatusDTO.getExecutionDateTime()).isEqualTo(stationsStatusDTO.getExecutionDateTime());
        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }

    @Test
    public void getLastListStationStatus_shouldReturnNull_whenFindSampleByIdButIs1minuteOld(){
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(LocalDateTime.now().minusMinutes(1));
        when(systemSampleRepository.findById(0)).thenReturn(stationsStatusDTO);

        StationsStatusDTO retStationsStatusDTO = underTest.getLastListStationStatus(0);

        assertThat(retStationsStatusDTO.getExecutionDateTime()).isEqualTo(stationsStatusDTO.getExecutionDateTime());
        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }
*/
}