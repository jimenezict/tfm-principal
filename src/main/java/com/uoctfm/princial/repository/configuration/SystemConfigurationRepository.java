package com.uoctfm.princial.repository.configuration;

import com.uoctfm.princial.domain.configuration.SystemConfigurationDTO;
import org.springframework.data.repository.CrudRepository;

public interface SystemConfigurationRepository extends CrudRepository<SystemConfigurationDTO, Long> {

    public SystemConfigurationDTO findById(Integer id);

}
