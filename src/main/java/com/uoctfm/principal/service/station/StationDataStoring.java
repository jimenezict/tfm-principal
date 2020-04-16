package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.calculated.StationDerived;
import com.uoctfm.principal.domain.calculated.StationPercentils;
import com.uoctfm.principal.domain.calculated.StationRaw;

public interface StationDataStoring {

    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO,
                                            StationDerived stationDerived,
                                            StationPercentils stationPercentils,
                                            StationRaw stationRaw);

}
