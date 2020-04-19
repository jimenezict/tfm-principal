package com.uoctfm.principal.service.load;

import com.uoctfm.principal.domain.transformation.StationStatistics;
import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;

public interface StationDataStoring {

    public void stationDataStoring(SystemConfigurationDTO systemConfigurationDTO,
                                   StationDerived stationDerived,
                                   StationPercentils stationPercentils,
                                   StationRaw stationRaw,
                                   StationStatistics stationStatistics);

}
