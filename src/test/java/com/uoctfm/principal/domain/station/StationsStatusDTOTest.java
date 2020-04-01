package com.uoctfm.principal.domain.station;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDateTime;

import static com.uoctfm.principal.TestBuildHelper.stationsStatusDTO;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class StationsStatusDTOTest {

    @Test
    public void station_shouldHas3Station_whenIsInitializedWithDefaultValues() {
        StationsStatusDTO stationsStatusDTO = stationsStatusDTO();

        assertThat(stationsStatusDTO.getExecutionDateTime()).isAfter(LocalDateTime.now().minusMinutes(1l));
        assertThat(stationsStatusDTO.getNumberStations()).isEqualTo(3);

        Station station = stationsStatusDTO.getStationList().get(2);
        assertThat(station.getId()).isEqualTo(2);
        assertThat(station.getNumBicicles()).isEqualTo(0);
    }

}