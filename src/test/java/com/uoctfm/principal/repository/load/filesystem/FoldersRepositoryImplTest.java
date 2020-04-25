package com.uoctfm.principal.repository.load.filesystem;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Clock;

import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class FoldersRepositoryImplTest {

    @InjectMocks
    private FoldersRepositoryImpl underTest;

    private Clock clock = Clock.systemUTC();

    private String dateFolder;

    private final static String SYSTEM_NAME = "NeverLand";

    @Before
    public void setUp() throws IOException {
        Path path = Paths.get(SYSTEM_NAME);
        Files.createDirectories(path);
        dateFolder = clock.instant().toString().substring(0, 10);
        Path subPath = Paths.get(SYSTEM_NAME + "/" + dateFolder);
        Files.createDirectories(subPath);
    }

    @Test
    public void folderProperty_shouldBeJavedefaultDirectory_whenGetsPropertySystem () {
        assertThat(System.getProperty("user.dir")).contains("tfm-principal");
    }

    @Test
    public void hasDateFolder_shouldReturnTrue_whenFolderExists () {
        assertThat(underTest.hasSystemFolder(SYSTEM_NAME)).isTrue();
    }

    @Test
    public void hasSystemFolder_shouldReturnFalse_whenFolderNotExists () {
        assertThat(underTest.hasSystemFolder("FAKE-" + SYSTEM_NAME)).isFalse();
    }

    @Test
    public void hasSystemFolder_shouldReturnTrue_whenFolderExists () {
        assertThat(underTest.hasDateFolder(SYSTEM_NAME, now())).isTrue();
    }

    @Test
    public void hasDateFolder_shouldReturnFalse_whenFolderNotExists () {
        assertThat(underTest.hasDateFolder(SYSTEM_NAME, now().plusDays(3))).isFalse();
    }

    @Test
    public void createSystemFolder_shouldCreateFolder_whenDoesntExists () {
        String newFolder = SYSTEM_NAME + "_TEST_FOLDER";
        underTest.createSystemFolder(newFolder);

        assertThat(Files.exists(Paths.get(newFolder))).isTrue();
        assertThat(Files.exists(Paths.get(newFolder + "dummy"))).isFalse();
    }

    @Test
    public void createSystemFolder_shouldCreateDateFolder_whenDoesntExists () {
        String newFolder = SYSTEM_NAME + "/" + now().toString();
        underTest.createDateFolder(SYSTEM_NAME, now());

        assertThat(Files.exists(Paths.get(newFolder))).isTrue();
        assertThat(Files.exists(Paths.get(newFolder + "dummy"))).isFalse();
    }

    @After
    public void setDown() throws IOException {
        FileUtils.deleteDirectory(new File(SYSTEM_NAME));
    }

}