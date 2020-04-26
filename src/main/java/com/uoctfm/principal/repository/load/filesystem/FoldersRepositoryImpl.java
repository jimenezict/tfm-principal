package com.uoctfm.principal.repository.load.filesystem;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

@Repository
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
        (new File(system + "/" + date.toString())).mkdir();
        (new File(system + "/" + date.toString() + "/" + "derived")).mkdir();
        (new File(system + "/" + date.toString() + "/" + "statistics")).mkdir();
        (new File(system + "/" + date.toString() + "/" + "raw")).mkdir();
        (new File(system + "/" + date.toString() + "/" + "percentils")).mkdir();
    }
}
