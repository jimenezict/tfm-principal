package com.uoctfm.principal.service.load.databases;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.uoctfm.principal.domain.load.databases.StationDerivedCsv;
import com.uoctfm.principal.repository.load.filesystem.FoldersRepository;
import com.uoctfm.principal.repository.load.filesystem.FoldersRepositoryImpl;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.now;
import static java.util.Arrays.asList;
import static org.slf4j.LoggerFactory.getLogger;

public class FileSystemDatabaseService extends AbstractDatabaseService {

    @Autowired
    FoldersRepository foldersRepository;

    Logger logger = getLogger(FileSystemDatabaseService.class);
    String dateSystemFolder;

    @Override
    public void initialize() {
        foldersRepository = new FoldersRepositoryImpl();

        if(!foldersRepository.hasSystemFolder(processName)) {
            logger.info("Creating system folder {}", processName);
            foldersRepository.createSystemFolder(processName);
        }

        if(!foldersRepository.hasDateFolder(processName, now())) {
            logger.info("Creating date folder for system {}", processName);
            foldersRepository.createDateFolder(processName, now());
        }

        dateSystemFolder = processName + "/" + now().toString();
    }

    @Override
    public void saveRaw(){
        List<Object> stationList = new ArrayList<>();
        stationRaw.getStationStatusDTO().getStationList().values().forEach(x -> stationList.add(x));

        writeListOnFile(stationList, "raw", "raw");

    }

    @Override
    public void saveStatistics(){
        writeListOnFile(asList(stationPercentils), "percentils", "percentils");
        writeListOnFile(asList(stationStatistics), "statistics", "statistics");
    };

    @Override
    public void saveDerived(){
        List<StationDerivedCsv> stationList = new ArrayList<>();
        stationDerived.getStationsStatusDTO().keySet().forEach(x -> {
            stationList.add(new StationDerivedCsv(x, stationDerived.getStationsStatusDTO().get(x), LocalDateTime.now()));
        });

        writeListOnFile(asList(stationDerived), "derived", "derived");
    };

    private void writeListOnFile(List<Object> fileLine, String folder, String subName) {
        try {
            Writer writer = getFileWriter(folder, subName);
            StatefulBeanToCsv sbc = getStatefulBean(writer);

            sbc.write(fileLine);
            writer.close();
        } catch (Exception e) {
            logger.error("Fail on writing in {} File System process. ", processName, e);
        }
    }

    private FileWriter getFileWriter(String folder, String subName) throws IOException {
        return new FileWriter(dateSystemFolder
                + "/" + folder + "/" + subName + "-"
                + LocalTime.now().toString().replace(':','_').substring(0, 8)
                + ".csv");
    }

    private StatefulBeanToCsv getStatefulBean(Writer writer) {
        return new StatefulBeanToCsvBuilder(writer)
                .withSeparator(CSVWriter.DEFAULT_SEPARATOR)
                .build();
    }

}
