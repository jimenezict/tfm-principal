package com.uoctfm.principal.service.configuration;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.repository.configuration.SystemConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemConfigurationImpl implements SystemConfiguration {

    @Autowired
    private SystemConfigurationRepository systemConfigurationRepository;

    @Override
    public SystemConfigurationDTO getSystemConfigurationBy(Integer id) {
        return systemConfigurationRepository.findById(id);
    }
}