package com.uoctfm.principal.repository.configuration;

import com.uoctfm.principal.domain.station.StationsStatusDTO;
import org.springframework.data.repository.CrudRepository;

public interface SystemSampleRepository extends CrudRepository<StationsStatusDTO, Long> {

    StationsStatusDTO findById(Integer id);

}
