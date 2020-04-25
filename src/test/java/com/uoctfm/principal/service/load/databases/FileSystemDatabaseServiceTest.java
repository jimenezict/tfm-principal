package com.uoctfm.principal.service.load.databases;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemDatabaseServiceTest {

    private FileSystemDatabaseService underTest = new FileSystemDatabaseService();

    private static final String SYSTEM_NAME = "SystemName";

    @Before
    public void setUp() {
        underTest.databaseServiceSetter(null, null, null, null, "File System", SYSTEM_NAME);
    }

    @Test
    public void initialize_shouldCreateFolder_whenNotExists () {
        String newFolder = SYSTEM_NAME + "/" + now().toString();

        underTest.initialize();

        assertThat(Files.exists(Paths.get(newFolder))).isTrue();
    }

    @Test
    public void initialize_shouldNotCreateFolder_whenTheAlreadyExists () {
        underTest.initialize();
        underTest.initialize();
    }

    @After
    public void setDown() throws IOException {
        FileUtils.deleteDirectory(new File(SYSTEM_NAME));
    }

}