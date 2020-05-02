package com.uoctfm.principal.service.transformation;

import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.GlobalStatistical;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;

public interface LocationAndStationMergeService {

    GlobalStatistical mergeStatisticalDate(StationsLocationDTO stationLocationDTO, StationStatistics stationStatistics);

    void mergeRawlData(StationsLocationDTO stationLocationDTO, StationRaw stationRaw);
}
