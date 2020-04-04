package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.domain.station.calculated.StationDerived;
import com.uoctfm.principal.domain.station.calculated.StationPercentils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.uoctfm.principal.TestBuildHelper.stationsStatusDTO;
import static com.uoctfm.principal.TestBuildHelper.stationsStatusDTO_higherId;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StationCalculationImplTest {

    @InjectMocks
    private StationCalculation underTest = new StationCalculationImpl();

    @Test
    public void calculatePercentils_shouldCalculatePercentil_whenUsesTheHelperClass(){

        StationPercentils stationPercentils = underTest.calculatePercentils(stationsStatusDTO());

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
    public void calculateRaw_shouldCalculatePercentil_whenUsesTheHelperClass(){

        StationsStatusDTO stationsStatusDTO = underTest.calculateRaw(stationsStatusDTO()).getStationStatusDTO();

        assertThat(stationsStatusDTO.getExecutionDateTime()).isAfter(LocalDateTime.now().minusMinutes(1l));
        assertThat(stationsStatusDTO.getNumberStations());

        assertThat(stationsStatusDTO.getNumberStations()).isEqualTo(3);

        Station station = stationsStatusDTO.getStationList().get(2);
        assertThat(station.getId()).isEqualTo(2);
        assertThat(station.getNumBicicles()).isEqualTo(0);
    }

    @Test
    public void calculateDerived_shouldValueZero_whenHasNoLastSample() {
        StationDerived stationDerived = underTest.calculateDerived(stationsStatusDTO(), null);

        assertThat(stationDerived.getStationsStatusDTO().get(1)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(2)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(3)).isEqualTo(0);
    }

    @Test
    public void calculateDerived_shouldValueZero_whenSamplesAreIdentical() {
        StationDerived stationDerived = underTest.calculateDerived(stationsStatusDTO(), stationsStatusDTO());

        assertThat(stationDerived.getStationsStatusDTO().get(1)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(2)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(3)).isEqualTo(0);
    }

    @Test
    public void calculateDerived_shouldValueZero_whenStationsIdAreDifferent() {
        StationDerived stationDerived = underTest.calculateDerived(stationsStatusDTO(), stationsStatusDTO_higherId());

        assertThat(stationDerived.getStationsStatusDTO().get(1)).isEqualTo(10);
        assertThat(stationDerived.getStationsStatusDTO().get(2)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(3)).isEqualTo(22);

        assertThat(stationDerived.getStationsStatusDTO().get(4)).isEqualTo(-10);
        assertThat(stationDerived.getStationsStatusDTO().get(5)).isEqualTo(0);
        assertThat(stationDerived.getStationsStatusDTO().get(6)).isEqualTo(-22);
    }
}