package com.uoctfm.principal.service.configuration;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;

import java.util.List;

public interface SystemConfiguration {

    SystemConfigurationDTO getSystemConfigurationBy(Integer id);

    List<SystemConfigurationDTO> getSystemConfiguration();

}
