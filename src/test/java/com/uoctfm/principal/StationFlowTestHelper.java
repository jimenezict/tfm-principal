package com.uoctfm.principal;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;

public class StationFlowTestHelper {

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
}