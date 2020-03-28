package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.station.StationDerived;
import com.uoctfm.principal.domain.station.StationPercentils;
import com.uoctfm.principal.domain.station.StationRaw;

public interface StationDataStoring {

    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO,
                                            StationDerived stationDerived,
                                            StationPercentils stationPercentils,
                                            StationRaw stationRaw);

}
