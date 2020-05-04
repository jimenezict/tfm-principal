package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import com.uoctfm.principal.domain.transformation.StationDataWrapper;
import com.uoctfm.principal.repository.load.gis.GisAccessRepository;
import com.uoctfm.principal.service.extraction.stationLocation.StationLocation;
import com.uoctfm.principal.service.transformation.LocationAndStationMergeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static com.uoctfm.principal.TestBuildHelper.*;
import static com.uoctfm.principal.TestDataBuildHelper.*;
import static com.uoctfm.principal.TestDataBuildHelper.buildStationStatistics;
import static java.lang.Integer.valueOf;
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
        verify(gisAccessRepository).saveGlobal(any());

        verifyNoMoreInteractions(stationLocation);
        verifyNoMoreInteractions(locationAndStationMergeService);
        verifyNoMoreInteractions(gisAccessRepository);
    }

    @Test
    public void saveRaw_shouldSaveStationSystem_whenThereIsNoDataOnDatabase() {
        when(locationAndStationMergeService.mergeRawData(any(), any())).thenReturn(buildStationSystemRawSet());
        when(gisAccessRepository.findStationSystem(anyInt())).thenReturn(new TreeSet<StationSystemRaw>());

        underTest.saveRaw(stationDataWrapper, systemConfigurationDTO);

        verify(stationLocation).getListLocationStatus(any());
        verify(locationAndStationMergeService).mergeRawData(any(), any());
        verify(gisAccessRepository).findStationSystem(anyInt());

        ArgumentCaptor stationSystemRaw = ArgumentCaptor.forClass(StationSystemRaw.class);
        verify(gisAccessRepository).saveStationSystem((Set<StationSystemRaw>) stationSystemRaw.capture());

        Set<StationSystemRaw> stationSystemCaptor = (Set<StationSystemRaw>) stationSystemRaw.getValue();

        assertThat(stationSystemCaptor.size()).isEqualTo(5);
        StationSystemRaw firstElementOnList = (StationSystemRaw) stationSystemCaptor.toArray()[0];
        assertThat(firstElementOnList.getId()).isNull();
        assertThat(firstElementOnList.getSystem()).isEqualTo(0);

        verifyNoMoreInteractions(stationLocation);
        verifyNoMoreInteractions(locationAndStationMergeService);
        verifyNoMoreInteractions(gisAccessRepository);
    }
    @Test
    public void saveRaw_shouldSaveStationSystem_whenThereIsUniqueMatchingElementOnDatabase() {
        when(locationAndStationMergeService.mergeRawData(any(), any())).thenReturn(buildStationSystemRawSet());
        Set<StationSystemRaw> stationSystemRawFromDatabase = new TreeSet<>();
        // We have to merge this station as belongs to system 0
        stationSystemRawFromDatabase.add(buildStationSystemRaw(1L, 0, 1, null, 30, 10));
        // We have to discard this station as belongs to system 1
        stationSystemRawFromDatabase.add(buildStationSystemRaw(2L, 1, 1, null, 30, 10));
        // We have to merge this station as belongs to system 0, we should validate that the station size and num of bicicles had change
        stationSystemRawFromDatabase.add(buildStationSystemRaw(3L, 0, 3, null, 100, 90));

        when(gisAccessRepository.findStationSystem(anyInt())).thenReturn((TreeSet<StationSystemRaw>) stationSystemRawFromDatabase);

        underTest.saveRaw(stationDataWrapper, systemConfigurationDTO);

        verify(stationLocation).getListLocationStatus(any());
        verify(locationAndStationMergeService).mergeRawData(any(), any());
        verify(gisAccessRepository).findStationSystem(anyInt());

        ArgumentCaptor stationSystemRaw = ArgumentCaptor.forClass(StationSystemRaw.class);
        verify(gisAccessRepository).saveStationSystem((Set<StationSystemRaw>) stationSystemRaw.capture());

        Set<StationSystemRaw> stationSystemCaptor = (Set<StationSystemRaw>) stationSystemRaw.getValue();

        assertThat(stationSystemCaptor.size()).isEqualTo(5);

        // As this element was already on the database,
        // we have to validate that it has provided the right Id
        StationSystemRaw firstElementOnList = (StationSystemRaw) stationSystemCaptor.toArray()[0];
        assertThat(firstElementOnList.getId()).isEqualTo(1L);
        assertThat(firstElementOnList.getSystem()).isEqualTo(0);
        assertThat(firstElementOnList.getStation()).isEqualTo(1);

        // As this element was not on the database,
        // we have to validate that its Id is null
        // The values are coming from the client location instead
        StationSystemRaw secondElementOnList = (StationSystemRaw) stationSystemCaptor.toArray()[1];
        assertThat(secondElementOnList.getId()).isNull();
        assertThat(secondElementOnList.getSystem()).isEqualTo(0);
        assertThat(secondElementOnList.getStation()).isEqualTo(2);

        // As this element was already on the database, we have to validate
        // that it has provided the right Id and the size of station and num
        // of bikes had been overwritten with the value from the client location
        // instead of the ones from the database
        StationSystemRaw thirdElementOnList = (StationSystemRaw) stationSystemCaptor.toArray()[2];
        assertThat(thirdElementOnList.getId()).isEqualTo(3L);
        assertThat(thirdElementOnList.getSystem()).isEqualTo(0);
        assertThat(thirdElementOnList.getStation()).isEqualTo(3);
        assertThat(thirdElementOnList.getNumBicicles()).isNotEqualTo(100);
        assertThat(thirdElementOnList.getStationSize()).isNotEqualTo(90);

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