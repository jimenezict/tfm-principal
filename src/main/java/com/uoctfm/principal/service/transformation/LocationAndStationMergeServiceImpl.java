package com.uoctfm.principal.service.transformation;

import com.uoctfm.principal.domain.extraction.StationsLocationDTO;
import com.uoctfm.principal.domain.load.databases.GlobalStatistical;
import com.uoctfm.principal.domain.transformation.StationRaw;
import com.uoctfm.principal.domain.transformation.StationStatistics;
import org.springframework.stereotype.Service;

@Service
public class LocationAndStationMergeServiceImpl implements LocationAndStationMergeService {
    @Override
    public GlobalStatistical mergeStatisticalDate(StationsLocationDTO stationLocationDTO, StationStatistics stationStatistics) {
        return null;
    }

    @Override
    public void mergeRawlData(StationsLocationDTO stationLocationDTO, StationRaw stationRaw) {

    }
}
