package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.repository.load.filesystem.FoldersRepository;
import com.uoctfm.principal.repository.load.filesystem.FoldersRepositoryImpl;
import com.uoctfm.principal.service.load.AbstractDatabaseService;
import org.slf4j.Logger;

import static java.time.LocalDate.now;
import static org.slf4j.LoggerFactory.getLogger;

public class FileSystemDatabaseService extends AbstractDatabaseService {

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
    public void saveRaw(){};

    @Override
    public void savePercentils(){};

    @Override
    public void saveDerived(){};

}
