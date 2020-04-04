package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.calculated.StationPercentils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.principal.TestBuildHelper.stationsStatusDTO;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StationCalculationImplTest {

    @InjectMocks
    private StationCalculation underTest = new StationCalculationImpl();

    @Test
    public void stationDataStoring_shouldExecuteOnlyFileSystem_whenConfigurationAllowsFileSystem(){

        StationPercentils stationDerived = underTest.calculatePercentils(stationsStatusDTO());

        assertThat(stationDerived.getP0()).isEqualTo(33);
        assertThat(stationDerived.getP1()).isEqualTo(0);
        assertThat(stationDerived.getP2()).isEqualTo(0);
        assertThat(stationDerived.getP3()).isEqualTo(33);
        assertThat(stationDerived.getP4()).isEqualTo(0);
        assertThat(stationDerived.getP5()).isEqualTo(0);
        assertThat(stationDerived.getP6()).isEqualTo(0);
        assertThat(stationDerived.getP7()).isEqualTo(33);
        assertThat(stationDerived.getP8()).isEqualTo(0);
        assertThat(stationDerived.getP9()).isEqualTo(0);

    }

}