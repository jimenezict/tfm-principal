package com.uoctfm.principal.repository.configuration;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemConfigurationRepository extends JpaRepository<SystemConfigurationDTO, Long> {

    SystemConfigurationDTO findById(Integer id);

}
