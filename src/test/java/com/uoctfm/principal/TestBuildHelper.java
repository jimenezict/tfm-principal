package com.uoctfm.principal;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.station.Station;
import com.uoctfm.principal.domain.station.StationsStatusDTO;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

public class TestBuildHelper {

    public static SystemConfigurationDTO buildSystemConfigurationDTO(){
        SystemConfigurationDTO systemConfiguration = new SystemConfigurationDTO();
        systemConfiguration.setId(0);
        systemConfiguration.setName("Barcino");
        systemConfiguration.setSystemStationEndPoint("http://localhost:8483/endpoint/");
        systemConfiguration.setSaveInFileSystem(false);
        systemConfiguration.setSaveInGIS(false);
        systemConfiguration.setSaveInTimeSeries(false);

        return systemConfiguration;
    }

    public static StationsStatusDTO stationsStatusDTO() {
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(now());

        stationsStatusDTO.addStation(new Station(1, 10, 30));
        stationsStatusDTO.addStation(new Station(2, 0, 30));
        stationsStatusDTO.addStation(new Station(3, 22, 30));

        return stationsStatusDTO;
    }

    public static StationsStatusDTO stationsStatusDTO_higherId() {
        StationsStatusDTO stationsStatusDTO = new StationsStatusDTO();
        stationsStatusDTO.setExecutionDateTime(now());

        stationsStatusDTO.addStation(new Station(4, 10, 30));
        stationsStatusDTO.addStation(new Station(5, 0, 30));
        stationsStatusDTO.addStation(new Station(6, 22, 30));

        return stationsStatusDTO;
    }
}
