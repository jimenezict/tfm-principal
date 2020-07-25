package com.uoctfm.principal.repository.configuration;

import com.uoctfm.principal.domain.configuration.SystemStatisticsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SystemStatisticsRepository extends JpaRepository<SystemStatisticsDTO, Long> {

    SystemStatisticsDTO save(SystemStatisticsDTO systemStatisticsDTO);

}
