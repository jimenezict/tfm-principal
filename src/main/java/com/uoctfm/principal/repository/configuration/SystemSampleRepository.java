package com.uoctfm.principal.repository.configuration;

import com.uoctfm.principal.domain.station.StationsStatusDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemSampleRepository extends JpaRepository<StationsStatusDTO, Long> {

    StationsStatusDTO findById(Integer id);

}
