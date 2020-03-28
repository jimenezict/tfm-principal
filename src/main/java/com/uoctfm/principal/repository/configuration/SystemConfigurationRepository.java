package com.uoctfm.principal.repository.configuration;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import org.springframework.data.repository.CrudRepository;

public interface SystemConfigurationRepository extends CrudRepository<SystemConfigurationDTO, Long> {

    public SystemConfigurationDTO findById(Integer id);

}
