package com.uoctfm.principal.service.station;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.repository.load.service.FileSystemDatabaseService;
import com.uoctfm.principal.repository.load.service.GisDatabaseService;
import com.uoctfm.principal.repository.load.service.TimeseriesDatabaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.principal.TestBuildHelper.buildSystemConfigurationDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class StationDataStoringImplTest {

    @InjectMocks
    private StationDataStoring underTest = new StationDataStoringImpl();

    @Mock
    private FileSystemDatabaseService fileSystemDatabaseRepository = new FileSystemDatabaseService();

    @Mock
    private TimeseriesDatabaseService timeseriesDatabaseRepository = new TimeseriesDatabaseService();

    @Mock
    private GisDatabaseService gisDatabaseRepository = new GisDatabaseService();

    @Test
    public void stationDataStoring_shouldExecuteOnlyFileSystem_whenConfigurationAllowsFileSystem(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null, null);

        verifyNoMoreInteractions(fileSystemDatabaseRepository);
        verifyNoMoreInteractions(timeseriesDatabaseRepository);
        verifyNoMoreInteractions(gisDatabaseRepository);
    }

    @Test
    public void stationDataStoring_shouldExecuteOnly_whenConfigurationAllowsFileSystem(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setSaveInFileSystem(true);

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null, null);

        verify(fileSystemDatabaseRepository).saveRaw(any());
        verify(fileSystemDatabaseRepository).saveDerived(any());
        verify(fileSystemDatabaseRepository).savePercentils(any(), any());

        verifyNoMoreInteractions(fileSystemDatabaseRepository);
        verifyNoInteractions(timeseriesDatabaseRepository);
        verifyNoInteractions(gisDatabaseRepository);
    }

    @Test
    public void stationDataStoring_shouldExecuteOnly_whenConfigurationAllowsTimeseries(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setSaveInTimeSeries(true);

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null, null);

        verify(timeseriesDatabaseRepository).saveRaw(any());
        verify(timeseriesDatabaseRepository).saveDerived(any());
        verify(timeseriesDatabaseRepository).savePercentils(any(), any());

        verifyNoInteractions(fileSystemDatabaseRepository);
        verifyNoMoreInteractions(timeseriesDatabaseRepository);
        verifyNoInteractions(gisDatabaseRepository);
    }

    @Test
    public void stationDataStoring_shouldExecuteOnly_whenConfigurationAllowsGis(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setSaveInGIS(true);

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null, null);

        verify(gisDatabaseRepository).saveRaw(any());
        verify(gisDatabaseRepository).saveDerived(any());
        verify(gisDatabaseRepository).savePercentils(any(), any());

        verifyNoInteractions(fileSystemDatabaseRepository);
        verifyNoInteractions(timeseriesDatabaseRepository);
        verifyNoMoreInteractions(gisDatabaseRepository);
    }

}