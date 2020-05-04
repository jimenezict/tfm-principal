package com.uoctfm.principal.repository.load.gis;

import com.uoctfm.principal.domain.load.databases.gis.GlobalStatistical;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;

import java.util.Set;
import java.util.TreeSet;

public interface GisAccessRepository {

    GlobalStatistical findById(Long id);

    GlobalStatistical findBySystem(Integer system);

    void saveGlobal(GlobalStatistical globalStatistical);

    void saveStationSystem(Set<StationSystemRaw> stationSystemRawToSave);

    TreeSet<StationSystemRaw> findStationSystem(Integer systemId);
}
