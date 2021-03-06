package com.uoctfm.principal.service.transformation;

import com.uoctfm.principal.domain.transformation.StationDerived;
import com.uoctfm.principal.domain.transformation.StationPercentils;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import com.uoctfm.principal.domain.extraction.StationsStatusDTO;

public interface StationCalculation {

    public StationPercentils calculatePercentils(StationsStatusDTO stationsStatusDTO);

    public StationDerived calculateDerived(StationsStatusDTO stationsStatusDTO, StationsStatusDTO lastStationsStatusDTO);

    public StationRaw calculateRaw(StationsStatusDTO stationsStatusDTO);

    public StationStatistics calculateStatistics(StationsStatusDTO stationsStatus);

}
