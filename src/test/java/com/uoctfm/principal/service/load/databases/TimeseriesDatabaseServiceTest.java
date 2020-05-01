package com.uoctfm.principal.service.load.databases;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.principal.TestDataBuildHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TimeseriesDatabaseServiceTest {

    @InjectMocks
    TimeseriesDatabaseService timeseriesDatabaseService;

    @Before
    public void setUp() {
        timeseriesDatabaseService = new TimeseriesDatabaseService();
        timeseriesDatabaseService.databaseServiceSetter(
                buildStationDerived(),
                buildStationPercentil(),
                buildStationRaw(),
                buildStationStatistics(),
                "Time Series",
                "NeverLand");

        timeseriesDatabaseService.initialize();
    }

    @Test
    public void initialize_shouldSetUpInfluxConnector_whenProcessNameIsValid() {
        assertThat(timeseriesDatabaseService.influxConnector).isNotNull();
        assertThat(timeseriesDatabaseService.getProcessName()).isEqualTo("NeverLand");
    }

    @Test
    public void saveRaw_shouldGenerateTheRawDataObject_whenExecutesTheSaveRawStep() {
        timeseriesDatabaseService.saveRaw();
    }

    @Test
    public void saveDerived_shouldGenerateTheDerivedDataObject_whenExecutesTheSaveDerivedStep() {
        timeseriesDatabaseService.saveDerived();
    }

    @Test
    public void savePercentils_shouldGenerateThePercentilsDataObject_whenExecutesTheSavePercentilStep() {
        timeseriesDatabaseService.savePercentils();
    }

}