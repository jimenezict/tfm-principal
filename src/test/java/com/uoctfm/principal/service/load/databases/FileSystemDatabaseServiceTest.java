package com.uoctfm.principal.service.load.databases;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.domain.transformation.StationDataWrapper;
import com.uoctfm.principal.repository.load.filesystem.FoldersRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static com.uoctfm.principal.TestBuildHelper.buildSystemConfigurationDTO;
import static com.uoctfm.principal.TestDataBuildHelper.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FileSystemDatabaseServiceTest {

    @InjectMocks
    private FileSystemDatabaseService underTest;

    @Mock
    private FoldersRepository foldersRepository;

    @Captor
    ArgumentCaptor stationList;

    private static final String SYSTEM_NAME = "NeverLand";
    StationDataWrapper stationDataWrapper;
    SystemConfigurationDTO systemConfigurationDTO;

    @Before
    public void setUp() {
        stationDataWrapper = new StationDataWrapper(buildStationDerived(),
                buildStationPercentil(),
                buildStationRaw(),
                buildStationStatistics());

        systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setName(SYSTEM_NAME);
    }

    @Test
    public void initialize_shouldCreateFolder_whenNotExists () {
        when(foldersRepository.hasSystemFolder(any())).thenReturn(false);
        when(foldersRepository.hasDateFolder(any(), any())).thenReturn(false);

        underTest.initialize(stationDataWrapper, systemConfigurationDTO);

        verify(foldersRepository).createSystemFolder(any());
        verify(foldersRepository).createDateFolder(any(), any());
    }

    @Test
    public void initialize_shouldNotCreateFolder_whenTheAlreadyExists () {
        underTest.initialize(stationDataWrapper, systemConfigurationDTO);
        underTest.initialize(stationDataWrapper, systemConfigurationDTO);
    }

    @Test
    public void saveRaw_shouldSaveSameSizeAsStationRawList_whenUsesTheSetUpValues() {
        underTest.saveRaw(stationDataWrapper, systemConfigurationDTO);

        verify(foldersRepository).writeListOnFile((List<Object>) stationList.capture(), anyString(), any());
        List<Object> value = (List<Object>) stationList.getValue();
        assertThat(value.size()).isEqualTo(3);

        verifyNoMoreInteractions(foldersRepository);
    }

    @Test
    public void saveDerived_shouldSaveSameSizeAsStationDerivedList_whenUsesTheSetUpValues() {
        underTest.saveDerived(stationDataWrapper, systemConfigurationDTO);

        verify(foldersRepository).writeListOnFile((List<Object>) stationList.capture(), anyString(), any());
        List<Object> value = (List<Object>) stationList.getValue();
        assertThat(value.size()).isEqualTo(5);

        verifyNoMoreInteractions(foldersRepository);
    }

    @Test
    public void saveStatistics_shouldSaveStatisticAndPercentils_whenUsesTheSetUpValues() {
        underTest.saveStatistics(stationDataWrapper, systemConfigurationDTO);

        verify(foldersRepository, times(2))
                .writeListOnFile((List<Object>) stationList.capture(), anyString(), any());

        verifyNoMoreInteractions(foldersRepository);
    }

}