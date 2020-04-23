package com.uoctfm.principal.flow;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;
import com.uoctfm.principal.service.configuration.SystemConfiguration;
import com.uoctfm.principal.service.load.databases.FileSystemDatabaseService;
import com.uoctfm.principal.service.load.databases.GisDatabaseService;
import com.uoctfm.principal.service.load.databases.TimeseriesDatabaseService;
import com.uoctfm.principal.service.transformation.StationCalculation;
import com.uoctfm.principal.service.extraction.StationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.principal.TestBuildHelper.buildSystemConfigurationDTO;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SystemFlowTest {

    @Mock
    private SystemConfiguration systemConfiguration;
    @Mock
    private StationStatus stationStatus;
    @Mock
    private StationCalculation stationCalculation;
    @Mock
    private FileSystemDatabaseService fileSystemDatabaseService;
    @Mock
    private GisDatabaseService gisDatabaseService;
    @Mock
    private TimeseriesDatabaseService timeseriesDatabaseService;

    @InjectMocks
    SystemFlow underTest = new SystemFlow();

    @Test
    public void executeById_shouldSkip_whenNotFoundConfiguration() {
        when(systemConfiguration.getSystemConfigurationBy(0)).thenReturn(null);
        underTest.executeById(0);

        verify(systemConfiguration).getSystemConfigurationBy(0);
        verifyNoMoreInteractions(systemConfiguration);
        verifyNoInteractions(stationStatus);
        verifyNoInteractions(stationCalculation);
    }

    @Test
    public void executeById_shouldSkip_whenFoundConfigurationButNotReachValidStationRequest() {
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        when(systemConfiguration.getSystemConfigurationBy(0)).thenReturn(systemConfigurationDTO);
        when(stationStatus.getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint())).thenReturn(null);
        underTest.executeById(0);

        verify(systemConfiguration).getSystemConfigurationBy(0);
        verify(stationStatus).getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint());
        verifyNoMoreInteractions(systemConfiguration);
        verifyNoMoreInteractions(stationStatus);
        verifyNoInteractions(stationCalculation);
    }

    @Test
    public void executeById_shouldPass_whenFoundConfigurationAndReachValidStationRequest() {
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        when(systemConfiguration.getSystemConfigurationBy(0)).thenReturn(systemConfigurationDTO);
        when(stationStatus.getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint()))
                .thenReturn(new StationsStatusDTO());
        underTest.executeById(0);

        verify(systemConfiguration).getSystemConfigurationBy(0);
        verify(stationStatus).getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint());
        verify(stationStatus).getLastStationStatus(systemConfigurationDTO.getId());
        verify(stationCalculation).calculateRaw(any());
        verify(stationCalculation).calculateDerived(any(), any());
        verify(stationCalculation).calculatePercentils(any());
        verify(stationCalculation).calculateStatistics(any());
        verify(stationStatus).saveLastStationStatus(any(), any());

        verifyNoMoreInteractions(stationStatus);
        verifyNoMoreInteractions(stationCalculation);
    }



}