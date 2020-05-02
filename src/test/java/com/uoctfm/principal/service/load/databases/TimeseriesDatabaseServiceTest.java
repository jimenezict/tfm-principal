package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.principal.TestBuildHelper.buildSystemConfigurationDTO;
import static com.uoctfm.principal.TestDataBuildHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TimeseriesDatabaseServiceTest {

    private final static String SYSTEM_NAME = "NeverLand";

    @InjectMocks
    TimeseriesDatabaseService timeseriesDatabaseService;

    @Before
    public void setUp() {
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setName(SYSTEM_NAME);

        timeseriesDatabaseService = new TimeseriesDatabaseService();
        timeseriesDatabaseService.databaseServiceSetter(
                buildStationDerived(),
                buildStationPercentil(),
                buildStationRaw(),
                buildStationStatistics(),
                "Time Series",
                systemConfigurationDTO);

        timeseriesDatabaseService.initialize();
    }

    @Test
    public void initialize_shouldSetUpInfluxConnector_whenProcessNameIsValid() {
        assertThat(timeseriesDatabaseService.influxConnector).isNotNull();
        assertThat(timeseriesDatabaseService.getProcessName()).isEqualTo(SYSTEM_NAME);
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
        timeseriesDatabaseService.saveStatistics();
    }

}