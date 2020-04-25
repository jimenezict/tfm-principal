package com.uoctfm.principal.repository.load.filesystem;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class FoldersRepositoryImpl implements FoldersRepository {

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

    }
}
