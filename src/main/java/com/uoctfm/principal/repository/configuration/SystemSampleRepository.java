package com.uoctfm.principal.repository.configuration;

import com.uoctfm.principal.domain.station.StationStatusDTO;
import org.springframework.data.repository.CrudRepository;

public interface SystemSampleRepository extends CrudRepository<StationStatusDTO, Long> {

    StationStatusDTO findById(Integer id);

}
