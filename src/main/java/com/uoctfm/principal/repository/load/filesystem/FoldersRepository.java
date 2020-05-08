package com.uoctfm.principal.repository.load.filesystem;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;

import java.time.LocalDate;
import java.util.List;

public interface FoldersRepository {

    boolean hasSystemFolder(String system);

    boolean hasDateFolder(String system, LocalDate date);

    void createSystemFolder(String system);

    void createDateFolder(String system, LocalDate date);

    void writeListOnFile(List<Object> fileLine, String folder, SystemConfigurationDTO systemConfigurationDTO);

}
