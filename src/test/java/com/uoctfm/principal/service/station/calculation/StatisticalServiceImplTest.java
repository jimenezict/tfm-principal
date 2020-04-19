package com.uoctfm.principal.service.station.calculation;

import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;
import com.uoctfm.principal.service.transformation.StatisticalService;
import com.uoctfm.principal.service.transformation.StatisticalServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static java.time.LocalDateTime.now;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StatisticalServiceImplTest {

    @InjectMocks
    private StatisticalService underTest = new StatisticalServiceImpl();

    @Test
    public void calculateEntropy_shouldCalculateSystemEntropyAndResultZero_whenTheSystemIsWellDistributed() {
        assertThat(underTest.calculateEntropy(buildBalancedStationsStatusDTO())).isEqualTo(0);
    }

    @Test
    public void calculateEntropy_shouldCalculateSystemEntropyAndResultZero_whenTheSystemIsEmptyDistributed() {
        assertThat(underTest.calculateEntropy(buildEmptyStationStatusDTO())).isEqualTo(9);
    }

    @Test
    public void calculatePercentage_shouldReturnZero_whenTheSystemIsEmptyDistributed() {
        assertThat(underTest.calculateAverage(buildEmptyStationStatusDTO())).isEqualTo(0.0);
    }

    @Test
    public void calculatePercentage_shouldReturnZero_whenTheSystemIsWellDistributed() {
        assertThat(underTest.calculateAverage(buildBalancedStationsStatusDTO())).isEqualTo(5.333333333333333);
    }

    private StationsStatusDTO buildBalancedStationsStatusDTO() {
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(now());

        stationsStatusDTO.addStation(new Station(1, 10, 30));
        stationsStatusDTO.addStation(new Station(2, 20, 30));
        stationsStatusDTO.addStation(new Station(3, 22, 30));

        return stationsStatusDTO;
    }

    private StationsStatusDTO buildEmptyStationStatusDTO() {
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(now());

        stationsStatusDTO.addStation(new Station(1, 0, 30));
        stationsStatusDTO.addStation(new Station(2, 0, 30));
        stationsStatusDTO.addStation(new Station(3, 0, 30));

        return stationsStatusDTO;
    }


}