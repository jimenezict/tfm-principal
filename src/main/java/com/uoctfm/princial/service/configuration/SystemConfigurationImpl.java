package com.uoctfm.princial.service.configuration;

import com.uoctfm.princial.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.princial.repository.configuration.SystemConfigurationRepository;
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
