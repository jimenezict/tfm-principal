package com.uoctfm.principal.service.station;

import com.uoc.tfm.commons.domain.StationsStatus;
import com.uoctfm.principal.domain.configuration.LastSampleDTO;
import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.repository.configuration.LastSampleRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
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
    private LastSampleRepository lastSampleRepository;

    @Mock
    private RestTemplate restTemplate;

    @Test
    public void getListStationStatus_shouldReturnStationStatusDTOObject_whenItIsCallToAValidEndpoint(){
        when(restTemplate.getForObject(DUMMY_SCB_TEXT, StationsStatus.class))
          .thenReturn(new StationsStatus());

        StationsStatusDTO stationsStatusDTO = underTest.getListStationStatus(DUMMY_SCB_TEXT);

        assertThat(stationsStatusDTO).isNotNull();
        assertThat(stationsStatusDTO).isInstanceOf(StationsStatusDTO.class);
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

    @Test
    public void getLastStationStatus_shouldReturnNull_whenNotFindSampleById(){
        when(lastSampleRepository.findById(0)).thenReturn(null);

        assertThat(underTest.getLastStationStatus(0)).isNull();

        verify(lastSampleRepository).findById(0);
        verifyNoMoreInteractions(lastSampleRepository);
    }

    @Test
    public void getLastStationStatus_shouldReturnNull_whenLastSampleThrowsException(){
        when(lastSampleRepository.findById(0)).thenThrow(RuntimeException.class);

        assertThat(underTest.getLastStationStatus(0)).isNull();

        verify(lastSampleRepository).findById(0);
        verifyNoMoreInteractions(lastSampleRepository);
    }

    @Test
    public void getLastStationStatus_shouldReturnNull_whenFindSampleByIdButIsOld(){
        LastSampleDTO lastSampleDTO = new LastSampleDTO();
        lastSampleDTO.setId(1);
        lastSampleDTO.setTime(LocalDateTime.of(2000, 1, 1, 0,0));
        lastSampleDTO.setLastSample(new HashMap<>());

        when(lastSampleRepository.findById(0)).thenReturn(lastSampleDTO);

        assertThat(underTest.getLastStationStatus(0)).isNull();
        assertThat(lastSampleDTO.getId()).isEqualTo(1);

        verify(lastSampleRepository).findById(0);
        verifyNoMoreInteractions(lastSampleRepository);
    }

    @Test
    public void getLastStationStatus_shouldReturnStationStatus_whenFindSampleByIdButIsNew(){
        LastSampleDTO lastSampleDTO = new LastSampleDTO();
        lastSampleDTO.setId(1);
        lastSampleDTO.setTime(now());
        lastSampleDTO.setLastSample(new HashMap<>());

        when(lastSampleRepository.findById(0)).thenReturn(lastSampleDTO);
        StationsStatusDTO stationsStatusDTO = underTest.getLastStationStatus(0);

        assertThat(stationsStatusDTO.getExecutionDateTime()).isEqualTo(lastSampleDTO.getTime());
        verify(lastSampleRepository).findById(0);
        verifyNoMoreInteractions(lastSampleRepository);
    }

    @Test
    public void getLastStationStatus_shouldMapCorrectly_whenFindSampleHasValidStations() {
        LastSampleDTO lastSampleDTO = new LastSampleDTO();
        lastSampleDTO.setId(1);
        lastSampleDTO.setTime(now());
        Map<Integer, Station> stationList = new HashMap<>();
        stationList.put(1, new Station(1,10,20));
        stationList.put(2, new Station(2,20,30));

        lastSampleDTO.setLastSample(stationList);

        when(lastSampleRepository.findById(0)).thenReturn(lastSampleDTO);
        StationsStatusDTO stationsStatusDTO = underTest.getLastStationStatus(0);

        assertThat(stationsStatusDTO.getExecutionDateTime()).isEqualTo(lastSampleDTO.getTime());
        assertThat(stationsStatusDTO.getStationList().size()).isEqualTo(2);

        verify(lastSampleRepository).findById(0);
        verifyNoMoreInteractions(lastSampleRepository);
    }

    @Test
    public void saveLastStationStatus_shouldSaveLastStationStatus_whenMapsFromValidStationStatus() {
        StationsStatusDTO stationStatus = buildStationsStatusDTO();

        underTest.saveLastStationStatus(stationStatus, 1);

        verify(lastSampleRepository).save(any());
        verifyNoMoreInteractions(lastSampleRepository);
    }

    @Test
    public void saveLastStationStatus_shouldCaptureException_whenSaveThrowsException() {
        StationsStatusDTO stationStatus = buildStationsStatusDTO();
        when(lastSampleRepository.save(any())).thenThrow(RuntimeException.class);

        underTest.saveLastStationStatus(stationStatus, 1);

        verify(lastSampleRepository).save(any());
        verifyNoMoreInteractions(lastSampleRepository);
    }

}