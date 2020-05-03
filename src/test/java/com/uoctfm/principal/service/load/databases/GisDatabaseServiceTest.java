package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.transformation.StationDataWrapper;
import com.uoctfm.principal.repository.load.gis.GisAccessRepository;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.transformation.LocationAndStationMergeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.uoctfm.principal.TestBuildHelper.*;
import static com.uoctfm.principal.TestDataBuildHelper.*;
import static com.uoctfm.principal.TestDataBuildHelper.buildStationStatistics;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GisDatabaseServiceTest {

    private final static String SYSTEM_NAME = "NeverLand";

    @InjectMocks
    GisDatabaseService underTest;

    @Mock
    StationLocation stationLocation;

    @Mock
    GisAccessRepository gisAccessRepository;

    @Mock
    LocationAndStationMergeService locationAndStationMergeService;

    StationDataWrapper stationDataWrapper;
    SystemConfigurationDTO systemConfigurationDTO;

    @Before
    public void setUp() {
        when(stationLocation.getListLocationStatus(any())).thenReturn(buildStationsLocationDTO());
        stationDataWrapper = new StationDataWrapper(buildStationDerived(),
                buildStationPercentil(),
                buildStationRaw(),
                buildStationStatistics());

        systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setName(SYSTEM_NAME);
    }

    @Test
    public void initialize_shouldPopulateStationLocationDTO_whenPassesTheBuildFromTheHelper() {
        underTest.initialize(stationDataWrapper, systemConfigurationDTO);

        verifyNoInteractions(stationLocation);
        verifyNoInteractions(gisAccessRepository);
        verifyNoInteractions(locationAndStationMergeService);
    }

    @Test
    public void saveStatistics_shouldSaveStationLocationDTO_whenPassesTheBuildFromTheHelper() {
        when(locationAndStationMergeService.mergeStatisticalDate(any(), any())).thenReturn(buildGlobalStatistical());

        underTest.saveStatistics(stationDataWrapper, systemConfigurationDTO);

        verify(stationLocation).getListLocationStatus(any());
        verify(locationAndStationMergeService).mergeStatisticalDate(any(), any());
        verify(gisAccessRepository).findBySystem(any());
        verify(gisAccessRepository).save(any());

        verifyNoMoreInteractions(stationLocation);
        verifyNoMoreInteractions(locationAndStationMergeService);
        verifyNoMoreInteractions(gisAccessRepository);
    }

    @Test
    public void saveDerived_shouldDoNothing_whenCallsToSaveDerived() {
        underTest.saveDerived(stationDataWrapper, systemConfigurationDTO);

        verifyNoInteractions(stationLocation);
        verifyNoInteractions(locationAndStationMergeService);
        verifyNoInteractions(gisAccessRepository);
    }


}