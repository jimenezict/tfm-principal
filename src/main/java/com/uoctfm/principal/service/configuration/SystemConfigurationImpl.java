package com.uoctfm.principal.service.configuration;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.repository.configuration.SystemConfigurationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
public class SystemConfigurationImpl implements SystemConfiguration {

    @Autowired
    private SystemConfigurationRepository systemConfigurationRepository;

    Logger logger = getLogger(SystemConfigurationImpl.class);

    @Override
    public SystemConfigurationDTO getSystemConfigurationBy(Integer id) {
        SystemConfigurationDTO systemConfigurationDTO = null;
        try{
            systemConfigurationDTO = systemConfigurationRepository.findById(id);
        }catch (Exception e){
            logger.error("Process {} got a problem reaching database: {}", id, e.getMessage());
        }
        return systemConfigurationDTO;
    }

    @Override
    public List<SystemConfigurationDTO> getSystemConfiguration() {
        List<SystemConfigurationDTO> systemConfigurationDTOList = new ArrayList<>();
        try{
            systemConfigurationDTOList = systemConfigurationRepository.findAll();
        }catch (Exception e){
            logger.error("Main process got a problem reaching database: {}", e.getMessage());
        }
        return systemConfigurationDTOList;
    }
}
