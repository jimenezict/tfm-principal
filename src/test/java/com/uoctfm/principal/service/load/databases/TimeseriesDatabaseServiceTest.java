package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import com.uoctfm.principal.domain.transformation.StationDataWrapper;
import com.uoctfm.principal.repository.load.timeseries.TimeSeriesDatabaseRepository;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.transformation.LocationAndStationMergeService;
import org.influxdb.dto.BatchPoints;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static com.uoctfm.principal.TestBuildHelper.*;
import static com.uoctfm.principal.TestDataBuildHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TimeseriesDatabaseServiceTest {

    private final static String SYSTEM_NAME = "NeverLand";

    @InjectMocks
    TimeseriesDatabaseService timeseriesDatabaseService;

    @Mock
    TimeSeriesDatabaseRepository timeSeriesDatabaseRepository;

    @Mock
    StationLocation stationLocation;

    @Mock
    LocationAndStationMergeService locationAndStationMergeService;

    @Captor
    ArgumentCaptor batchCaptor;

    StationDataWrapper stationDataWrapper;
    SystemConfigurationDTO systemConfigurationDTO;
    Set<StationSystemRaw> stationSystemRaws;

    @Before
    public void setUp() {
        stationDataWrapper = new StationDataWrapper(buildStationDerived(),
                buildStationPercentil(),
                buildStationRaw(),
                buildStationStatistics());

        systemConfigurationDTO = buildSystemConfigurationDTO();
        stationSystemRaws = buildBarcinoStationSystemRawSet();
    }

    @Test
    public void saveRaw_shouldGenerateTheRawDataObject_whenExecutesTheSaveRawStep() {
        when(locationAndStationMergeService.mergeRawData(any(), any())).thenReturn(stationSystemRaws);
        timeseriesDatabaseService.saveRaw(stationDataWrapper, systemConfigurationDTO);

        verify(timeSeriesDatabaseRepository).saveListPoint(any(), (BatchPoints) batchCaptor.capture());

        BatchPoints batchPoints = (BatchPoints) batchCaptor.getValue();
        assertThat(batchPoints.getDatabase()).isEqualTo("Barcino");
        assertThat(batchPoints.getPoints().size()).isEqualTo(5);

        verifyNoMoreInteractions(timeSeriesDatabaseRepository);
    }

    @Test
    public void saveDerived_shouldGenerateTheDerivedDataObject_whenExecutesTheSaveDerivedStep() {
        timeseriesDatabaseService.saveDerived(stationDataWrapper, systemConfigurationDTO);

        verify(timeSeriesDatabaseRepository).saveListPoint(any(), (BatchPoints) batchCaptor.capture());

        BatchPoints batchPoints = (BatchPoints) batchCaptor.getValue();
        assertThat(batchPoints.getDatabase()).isEqualTo("Barcino");
        assertThat(batchPoints.getPoints().size()).isEqualTo(5);

        verifyNoMoreInteractions(timeSeriesDatabaseRepository);
    }

    @Test
    public void savePercentils_shouldGenerateThePercentilsDataObject_whenExecutesTheSavePercentilStep() {
        timeseriesDatabaseService.saveStatistics(stationDataWrapper, systemConfigurationDTO);

        verify(timeSeriesDatabaseRepository, times(2)).saveListPoint(any(), any());
        verifyNoMoreInteractions(timeSeriesDatabaseRepository);
    }

}