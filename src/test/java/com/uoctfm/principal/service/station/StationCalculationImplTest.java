package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.transformation.StationStatistics;
import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.service.transformation.StatisticalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.uoctfm.principal.TestBuildHelper.buildStationsStatusDTO;
import static com.uoctfm.principal.TestBuildHelper.buildStationsStatusDTO_higherId;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StationCalculationImplTest {

    @Mock
    private StatisticalService statisticalService;

    @InjectMocks
    private StationCalculation underTest = new StationCalculationImpl();

    @Test
    public void calculateStatistics_shouldCreateStatisticsPOJO_whenAllCalculationAreSuccess() {
        when(statisticalService.calculateEntropy(any())).thenReturn(3);
        when(statisticalService.calculateAverage(any())).thenReturn(30.0);

        StationStatistics calculateStatistics = underTest.calculateStatistics(buildStationsStatusDTO());

        assertThat(calculateStatistics.getAverage()).isEqualTo(30.0);
        assertThat(calculateStatistics.getEntropy()).isEqualTo(3);
        assertThat(calculateStatistics.getEntropyNormalized()).isEqualTo(1);
    }

    @Test
    public void calculatePercentils_shouldCalculatePercentil_whenUsesTheHelperClass() {

        StationPercentils stationPercentils = underTest.calculatePercentils(buildStationsStatusDTO());

        assertThat(stationPercentils.getP0()).isEqualTo(33);
        assertThat(stationPercentils.getP1()).isEqualTo(0);
        assertThat(stationPercentils.getP2()).isEqualTo(0);
        assertThat(stationPercentils.getP3()).isEqualTo(33);
        assertThat(stationPercentils.getP4()).isEqualTo(0);
        assertThat(stationPercentils.getP5()).isEqualTo(0);
        assertThat(stationPercentils.getP6()).isEqualTo(0);
        assertThat(stationPercentils.getP7()).isEqualTo(33);
        assertThat(stationPercentils.getP8()).isEqualTo(0);
        assertThat(stationPercentils.getP9()).isEqualTo(0);

    }

    @Test
    public void calculateRaw_shouldCalculatePercentil_whenUsesTheHelperClass() {

        StationsStatusDTO stationsStatusDTO = underTest.calculateRaw(buildStationsStatusDTO()).getStationStatusDTO();

        assertThat(stationsStatusDTO.getExecutionDateTime()).isAfter(LocalDateTime.now().minusMinutes(1l));
        assertThat(stationsStatusDTO.getNumberStations());

        assertThat(stationsStatusDTO.getNumberStations()).isEqualTo(3);

        Station station = stationsStatusDTO.getStationList().get(2);
        assertThat(station.getId()).isEqualTo(2);
        assertThat(station.getNumBicicles()).isEqualTo(0);
    }

    @Test
    public void calculateDerived_shouldValueZero_whenHasNoLastSample() {
        StationDerived stationDerived = underTest.calculateDerived(buildStationsStatusDTO(), null);

        assertThat(stationDerived.getStationsStatusDTO().get(1)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(2)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(3)).isEqualTo(0);
    }

    @Test
    public void calculateDerived_shouldValueZero_whenSamplesAreIdentical() {
        StationDerived stationDerived = underTest.calculateDerived(buildStationsStatusDTO(), buildStationsStatusDTO());

        assertThat(stationDerived.getStationsStatusDTO().get(1)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(2)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(3)).isEqualTo(0);
    }

    @Test
    public void calculateDerived_shouldValueZero_whenStationsIdAreDifferent() {
        StationDerived stationDerived = underTest.calculateDerived(buildStationsStatusDTO(), buildStationsStatusDTO_higherId());

        assertThat(stationDerived.getStationsStatusDTO().get(1)).isEqualTo(10);
        assertThat(stationDerived.getStationsStatusDTO().get(2)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(3)).isEqualTo(22);

        assertThat(stationDerived.getStationsStatusDTO().get(4)).isEqualTo(-10);
        assertThat(stationDerived.getStationsStatusDTO().get(5)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(6)).isEqualTo(-22);
    }
}