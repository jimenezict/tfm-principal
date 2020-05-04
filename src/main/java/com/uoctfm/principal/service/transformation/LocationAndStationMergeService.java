package com.uoctfm.principal.service.transformation;

import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.gis.GlobalStatistical;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;

import java.util.Set;

public interface LocationAndStationMergeService {

    Set<StationSystemRaw> mergeRawData(StationsLocationDTO stationLocationDTO, StationRaw stationRaw);

    GlobalStatistical mergeStatisticalDate(StationsLocationDTO stationLocationDTO, StationStatistics stationStatistics);

    GlobalStatistical updateStatisticalData(GlobalStatistical globalStatistical, StationStatistics stationStatistics);
}
