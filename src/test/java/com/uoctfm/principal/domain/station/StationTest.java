package com.uoctfm.principal.domain.station;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class StationTest {

    @Test
    public void station_shouldProvideValidValues_whenId1numBiciclesIs10AndSizeSstationIs20() {
        Station station = new Station(1, 10, 20);

        assertThat(station.getId()).isEqualTo(1);
        assertThat(station.getNumBicicles()).isEqualTo(10);
        assertThat(station.getSizeStation()).isEqualTo(20);
        assertThat(station.getPercentage()).isEqualTo(50);
        assertThat(station.getPercentil()).isEqualTo(5);
    }

    @Test
    public void station_shouldProvideValidValues_whenId2numBiciclesIs20AndSizeSstationIs20() {
        Station station = new Station(2, 20, 20);

        assertThat(station.getId()).isEqualTo(2);
        assertThat(station.getNumBicicles()).isEqualTo(20);
        assertThat(station.getSizeStation()).isEqualTo(20);
        assertThat(station.getPercentage()).isEqualTo(100);
        assertThat(station.getPercentil()).isEqualTo(9);
    }

    @Test
    public void station_shouldProvideValidValues_whenId3numBiciclesIs20AndSizeSstationIs20() {
        Station station = new Station(3, 0, 20);

        assertThat(station.getId()).isEqualTo(3);
        assertThat(station.getNumBicicles()).isEqualTo(0);
        assertThat(station.getSizeStation()).isEqualTo(20);
        assertThat(station.getPercentage()).isEqualTo(0);
        assertThat(station.getPercentil()).isEqualTo(0);
    }


}