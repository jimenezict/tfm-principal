package com.uoctfm.principal.repository.load.filesystem;

import java.time.LocalDate;

public interface FoldersRepository {

    boolean hasSystemFolder(String system);

    boolean hasDateFolder(String system, LocalDate date);

    void createSystemFolder(String system);

    void createDateFolder(String system, LocalDate date);

}
