package com.uoctfm.principal.repository.load.gis;

import com.uoctfm.principal.domain.load.databases.gis.GlobalStatistical;
import com.uoctfm.principal.domain.load.databases.gis.StationSystemRaw;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.TreeSet;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GisAccessRepositoryImpl implements GisAccessRepository {

    Logger logger = getLogger(GisAccessRepositoryImpl.class);

    @Autowired
    private GlobalStatisticalRepository globalStatisticalRepository;

    @Autowired
    private StationSystemRawRepository stationSystemRawRepository;

    @Override
    public GlobalStatistical findById(Long id) {
        return globalStatisticalRepository.findById(id).orElse(null);
    }

    @Override
    public GlobalStatistical findBySystem(Integer system) {
        return globalStatisticalRepository.findBySystem(system).orElse(null);
    }

    @Override
    public void saveGlobal(GlobalStatistical globalStatistical) {
        globalStatisticalRepository.save(globalStatistical);
    }

    @Override
    public void saveStationSystem(Set<StationSystemRaw> stationSystemRawToSave) {
        stationSystemRawRepository.saveAll(stationSystemRawToSave);
    }

    @Override
    public TreeSet<StationSystemRaw> findStationSystem(Integer systemId) {
        return stationSystemRawRepository.findAllBySystem(systemId);
    }

}
