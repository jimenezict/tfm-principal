package com.uoctfm.principal.repository.load.gis;

import com.uoctfm.principal.domain.load.databases.GlobalStatistical;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class GisAccessRepositoryImpl implements GisAccessRepository {

    Logger logger = getLogger(GisAccessRepositoryImpl.class);

    @Autowired
    private GlobalStatisticalRepository globalStatisticalRepository;

    @Override
    public GlobalStatistical findById(Long id) {
        return globalStatisticalRepository.findById(id).orElse(null);
    }

    @Override
    public GlobalStatistical findBySystem(Integer system) {
        return globalStatisticalRepository.findBySystem(system).orElse(null);
    }

    @Override
    public void save(GlobalStatistical globalStatistical) {
        globalStatisticalRepository.save(globalStatistical);
    }

}
