package com.uoctfm.princial.service.station;

import com.uoctfm.princial.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.princial.domain.station.StationDerived;
import com.uoctfm.princial.domain.station.StationPercentils;
import com.uoctfm.princial.domain.station.StationRaw;
import com.uoctfm.princial.domain.station.StationStatusDTO;

public interface StationDataStoring {

    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO,
                                            StationDerived stationDerived,
                                            StationPercentils stationPercentils,
                                            StationRaw stationRaw);

}
