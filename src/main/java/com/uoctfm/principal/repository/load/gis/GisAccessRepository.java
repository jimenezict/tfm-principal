package com.uoctfm.principal.repository.load.gis;

import com.uoctfm.principal.domain.load.databases.GlobalStatistical;

public interface GisAccessRepository {

    public GlobalStatistical findById(Long id);

    public void save(GlobalStatistical globalStatistical);
}