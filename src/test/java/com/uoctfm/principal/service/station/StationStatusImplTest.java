package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.StationStatusDTO;
import com.uoctfm.principal.repository.configuration.SystemSampleRepository;
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

    @Mock
    private SystemSampleRepository systemSampleRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getListStationStatus_shouldReturnStationStatusDTOObject_whenItIsCallToAValidEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationStatusDTO.class))
          .thenReturn(new StationStatusDTO());

        StationStatusDTO stationStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationStatusDTO).isNotNull();
        assertThat(stationStatusDTO).isInstanceOf(StationStatusDTO.class);
    }

    @Test
    public void getListStationStatus_shouldReturnNullOObject_whenItIsCallToAValidEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationStatusDTO.class))
                .thenReturn(null);

        StationStatusDTO stationStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationStatusDTO).isNull();
    }

    @Test
    public void getListStationStatus_shouldThrownException_whenItFailsToCallEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationStatusDTO.class))
                .thenThrow(RestClientException.class);

        StationStatusDTO stationStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);
        assertThat(stationStatusDTO).isNull();
    }

    @Test
    public void getLastListStationStatus_shouldReturnNull_whenNotFindSampleById(){
        when(systemSampleRepository.findById(0)).thenReturn(null);

        assertThat(underTest.getLastListStationStatus(0)).isNull();

        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }

    @Test
    public void getLastListStationStatus_shouldReturnNull_whenFindSampleByIdButIsOld(){
        StationStatusDTO stationStatusDTO = new StationStatusDTO();
        stationStatusDTO.setExecutionDateTime(LocalDateTime.of(2000, 1, 1, 0,0));
        when(systemSampleRepository.findById(0)).thenReturn(stationStatusDTO);

        assertThat(underTest.getLastListStationStatus(0)).isNull();

        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }

    @Test
    public void getLastListStationStatus_shouldReturnNull_whenFindSampleByIdButIsNew(){
        StationStatusDTO stationStatusDTO = new StationStatusDTO();
        stationStatusDTO.setExecutionDateTime(LocalDateTime.now());
        when(systemSampleRepository.findById(0)).thenReturn(stationStatusDTO);

        StationStatusDTO retStationStatusDTO = underTest.getLastListStationStatus(0);

        assertThat(retStationStatusDTO.getExecutionDateTime()).isEqualTo(stationStatusDTO.getExecutionDateTime());
        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }

    @Test
    public void getLastListStationStatus_shouldReturnNull_whenFindSampleByIdButIs1minuteOld(){
        StationStatusDTO stationStatusDTO = new StationStatusDTO();
        stationStatusDTO.setExecutionDateTime(LocalDateTime.now().minusMinutes(1));
        when(systemSampleRepository.findById(0)).thenReturn(stationStatusDTO);

        StationStatusDTO retStationStatusDTO = underTest.getLastListStationStatus(0);

        assertThat(retStationStatusDTO.getExecutionDateTime()).isEqualTo(stationStatusDTO.getExecutionDateTime());
        verify(systemSampleRepository).findById(0);
        verifyNoMoreInteractions(systemSampleRepository);
    }

}