package com.uoctfm.princial.flow;

import com.uoctfm.princial.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.princial.domain.station.StationStatusDTO;
import com.uoctfm.princial.service.configuration.SystemConfiguration;
import com.uoctfm.princial.service.station.StationCalculation;
import com.uoctfm.princial.service.station.StationDataStoring;
import com.uoctfm.princial.service.station.StationStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.princial.StationFlowTestHelper.buildSystemConfigurationDTO;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StationFlowTest {

    @Mock
    private SystemConfiguration systemConfiguration;
    @Mock
    private StationStatus stationStatus;
    @Mock
    private StationCalculation stationCalculation;
    @Mock
    private StationDataStoring stationDataStoring;

    @InjectMocks
    StationFlow underTest = new StationFlow();

    @Test
    public void executeById_shouldSkip_whenNotFoundConfiguration() {
        when(systemConfiguration.getSystemConfigurationBy(0)).thenReturn(null);
        underTest.executeById(0);

        verify(systemConfiguration).getSystemConfigurationBy(0);
        verifyNoMoreInteractions(systemConfiguration);
        verifyNoInteractions(stationStatus);
        verifyNoInteractions(stationCalculation);
        verifyNoInteractions(stationDataStoring);
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
        verifyNoInteractions(stationDataStoring);
    }

    @Test
    public void executeById_shouldPass_whenFoundConfigurationAndReachValidStationRequest() {
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        when(systemConfiguration.getSystemConfigurationBy(0)).thenReturn(systemConfigurationDTO);
        when(stationStatus.getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint()))
                .thenReturn(new StationStatusDTO());
        underTest.executeById(0);

        verify(systemConfiguration).getSystemConfigurationBy(0);
        verify(stationStatus).getListStationStatus(systemConfigurationDTO.getSystemStationEndPoint());
        verify(stationStatus).getLastListStationStatus(systemConfigurationDTO.getId());
        verify(stationDataStoring).stationDataStoring(any(), any(), any(), any());
        verify(stationCalculation).calculateRaw(any());
        verify(stationCalculation).calculateDerived(any(), any());
        verify(stationCalculation).calculatePercentils(any());

        verifyNoMoreInteractions(stationStatus);
        verifyNoMoreInteractions(stationCalculation);
        verifyNoMoreInteractions(stationDataStoring);
    }



}