package com.uoctfm.principal.repository.load.filesystem;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static java.time.LocalDate.now;
import static org.slf4j.LoggerFactory.getLogger;

@Repository
public class FoldersRepositoryImpl implements FoldersRepository {

    Logger logger = getLogger(FoldersRepositoryImpl.class);

    @Override
    public boolean hasSystemFolder(String system) {
        return Files.exists(Paths.get(system));
    }

    @Override
    public boolean hasDateFolder(String system, LocalDate date) {
        return hasSystemFolder(system) && Files.exists(Paths.get(system + "/" + date.toString())) ? true : false;
    }

    @Override
    public void createSystemFolder(String system) {
        (new File(system)).mkdir();
    }

    @Override
    public void createDateFolder(String system, LocalDate date) {
        (new File(system + "/" + date.toString())).mkdir();
        (new File(system + "/" + date.toString() + "/" + "derived")).mkdir();
        (new File(system + "/" + date.toString() + "/" + "statistics")).mkdir();
        (new File(system + "/" + date.toString() + "/" + "raw")).mkdir();
        (new File(system + "/" + date.toString() + "/" + "percentils")).mkdir();
    }

    @Override
    public void writeListOnFile(List<Object> fileLine, String folder, SystemConfigurationDTO systemConfigurationDTO) {
        try {
            Writer writer = getFileWriter(folder, systemConfigurationDTO);
            StatefulBeanToCsv sbc = getStatefulBean(writer);

            sbc.write(fileLine);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            logger.error("Fail on writing in {} File System process. ", getDataSystemFolder(systemConfigurationDTO), e);
        }
    }

    private String getDataSystemFolder(SystemConfigurationDTO systemConfigurationDTO) {
        return systemConfigurationDTO.getName() + "/" + now().toString();
    }

    private FileWriter getFileWriter(String folder, SystemConfigurationDTO systemConfigurationDTO) throws IOException {
        return new FileWriter(getDataSystemFolder(systemConfigurationDTO)
                + "/" + folder + "/" + folder + "-"
                + LocalTime.now().toString().replace(':','_').substring(0, 8)
                + ".csv");
    }

    private StatefulBeanToCsv getStatefulBean(Writer writer) {
        return new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();
    }
}
