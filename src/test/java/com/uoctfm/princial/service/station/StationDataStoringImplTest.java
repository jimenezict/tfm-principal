package com.uoctfm.princial.service.station;

import com.uoctfm.princial.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.princial.repository.station.FileSystemDatabaseRepository;
import com.uoctfm.princial.repository.station.GisDatabaseRepository;
import com.uoctfm.princial.repository.station.TimeseriesDatabaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static com.uoctfm.princial.StationFlowTestHelper.buildSystemConfigurationDTO;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StationDataStoringImplTest {

    @InjectMocks
    private StationDataStoring underTest = new StationDataStoringImpl();


    @Mock
    private FileSystemDatabaseRepository fileSystemDatabaseRepository = new FileSystemDatabaseRepository();

    @Mock
    private TimeseriesDatabaseRepository timeseriesDatabaseRepository = new TimeseriesDatabaseRepository();

    @Mock
    private GisDatabaseRepository gisDatabaseRepository = new GisDatabaseRepository();

    @Test
    public void stationDataStoring_shouldExecuteOnlyFileSystem_whenConfigurationAllowsFileSystem(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null);

        verifyNoMoreInteractions(fileSystemDatabaseRepository);
        verifyNoMoreInteractions(timeseriesDatabaseRepository);
        verifyNoMoreInteractions(gisDatabaseRepository);
    }

    @Test
    public void stationDataStoring_shouldExecuteOnly_whenConfigurationAllowsFileSystem(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setSaveInFileSystem(true);

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null);

        verify(fileSystemDatabaseRepository).saveRaw(any());
        verify(fileSystemDatabaseRepository).saveDerived(any());
        verify(fileSystemDatabaseRepository).savePercentils(any());

        verifyNoMoreInteractions(fileSystemDatabaseRepository);
        verifyNoInteractions(timeseriesDatabaseRepository);
        verifyNoInteractions(gisDatabaseRepository);
    }

    @Test
    public void stationDataStoring_shouldExecuteOnly_whenConfigurationAllowsTimeseries(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setSaveInTimeSeries(true);

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null);

        verify(timeseriesDatabaseRepository).saveRaw(any());
        verify(timeseriesDatabaseRepository).saveDerived(any());
        verify(timeseriesDatabaseRepository).savePercentils(any());

        verifyNoInteractions(fileSystemDatabaseRepository);
        verifyNoMoreInteractions(timeseriesDatabaseRepository);
        verifyNoInteractions(gisDatabaseRepository);
    }

    @Test
    public void stationDataStoring_shouldExecuteOnly_whenConfigurationAllowsGis(){
        SystemConfigurationDTO systemConfigurationDTO = buildSystemConfigurationDTO();
        systemConfigurationDTO.setSaveInGIS(true);

        underTest.stationDataStoring(systemConfigurationDTO, null, null, null);

        verify(gisDatabaseRepository).saveRaw(any());
        verify(gisDatabaseRepository).saveDerived(any());
        verify(gisDatabaseRepository).savePercentils(any());

        verifyNoInteractions(fileSystemDatabaseRepository);
        verifyNoInteractions(timeseriesDatabaseRepository);
        verifyNoMoreInteractions(gisDatabaseRepository);
    }

}