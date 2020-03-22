package com.uoctfm.princial.flow;

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

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StationFlowTest {

    @Mock
    SystemConfiguration systemConfiguration;
    @Mock
    private StationStatus stationStatus;
    @Mock
    private StationCalculation stationCalculation;
    @Mock
    private StationDataStoring stationDataStoring;

    @InjectMocks
    StationFlow underTest = new StationFlow();

    @Test
    public void getStatedExample(){
        when(systemConfiguration.getSystemConfigurationBy(0)).thenReturn(null);
        underTest.executeById(0);
    }




}