package com.uoctfm.princial.repository.configuration;

import com.uoctfm.princial.domain.station.StationStatusDTO;
import org.springframework.data.repository.CrudRepository;

public interface SystemSampleRepository extends CrudRepository<StationStatusDTO, Long> {

    StationStatusDTO findById(Integer id);

}
