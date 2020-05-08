package com.uoctfm.principal.service.configuration;

import com.uoctfm.principal.domain.configuration.SystemConfigurationDTO;
import com.uoctfm.principal.repository.configuration.SystemConfigurationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static com.uoctfm.principal.TestBuildHelper.buildSystemConfigurationDTO;
import static com.uoctfm.principal.TestBuildHelper.buildSystemConfigurationListDTO;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SystemConfigurationImplTest {

    @InjectMocks
    private SystemConfiguration underTest = new SystemConfigurationImpl();

    @Mock
    private SystemConfigurationRepository systemConfigurationRepository;

    @Test
    public void getSystemConfigurationBy_shouldReturnSystemConfiguration_whenIdIsOnDatabase() {
        when(systemConfigurationRepository.findById(0)).thenReturn(buildSystemConfigurationDTO());
        SystemConfigurationDTO systemConfiguration = underTest.getSystemConfigurationBy(0);

        assertThat(systemConfiguration.getId()).isEqualTo(0);
        assertThat(systemConfiguration.getSystemStationEndPoint())
                .isEqualTo("http://localhost:8483/status/");
        assertThat(systemConfiguration.getSystemLocationEndPoint())
                .isEqualTo("http://localhost:8483/location/");
        assertThat(systemConfiguration.getName()).isEqualTo("Barcino");
        assertThat(systemConfiguration.getSaveInFileSystem()).isFalse();
        assertThat(systemConfiguration.getSaveInTimeSeries()).isFalse();
        assertThat(systemConfiguration.getSaveInGIS()).isFalse();

        verify(systemConfigurationRepository).findById(0);
        verifyNoMoreInteractions(systemConfigurationRepository);
    }

    @Test
    public void getSystemConfigurationBy_shouldReturnSystemConfiguration_whenIsNotOnDatabase() {
        when(systemConfigurationRepository.findById(0)).thenReturn(null);
        SystemConfigurationDTO systemConfiguration = underTest.getSystemConfigurationBy(0);

        assertThat(systemConfiguration).isNull();

        verify(systemConfigurationRepository).findById(0);
        verifyNoMoreInteractions(systemConfigurationRepository);
    }

    @Test
    public void getSystemConfigurationBy_shouldReturnNull_whenThrowsException() {
        when(systemConfigurationRepository.findById(0)).thenThrow(RuntimeException.class);
        SystemConfigurationDTO systemConfiguration = underTest.getSystemConfigurationBy(0);

        assertThat(systemConfiguration).isNull();

        verify(systemConfigurationRepository).findById(0);
        verifyNoMoreInteractions(systemConfigurationRepository);
    }

    @Test
    public void getSystemConfiguration_shouldReturnListOfSystemConfiguration_whenDatabaseHas2Registers() {
        when(systemConfigurationRepository.findAll()).thenReturn(buildSystemConfigurationListDTO());
        List<SystemConfigurationDTO> systemConfigurationList = underTest.getSystemConfiguration();

        assertThat(systemConfigurationList.size()).isEqualTo(2);

        verify(systemConfigurationRepository).findAll();
        verifyNoMoreInteractions(systemConfigurationRepository);
    }

    @Test
    public void getSystemConfiguration_shouldReturnEmptyListOfSystemConfiguration_whenDatabaseNoHasRegisters() {
        when(systemConfigurationRepository.findAll()).thenReturn(new ArrayList<>());
        List<SystemConfigurationDTO> systemConfigurationList = underTest.getSystemConfiguration();

        assertThat(systemConfigurationList.size()).isEqualTo(0);

        verify(systemConfigurationRepository).findAll();
        verifyNoMoreInteractions(systemConfigurationRepository);
    }

    @Test
    public void getSystemConfiguration_shouldReturnEmptyListOfSystemConfiguration_whenThrowsException() {
        when(systemConfigurationRepository.findAll()).thenThrow(RuntimeException.class);
        List<SystemConfigurationDTO> systemConfigurationList = underTest.getSystemConfiguration();

        assertThat(systemConfigurationList.size()).isEqualTo(0);

        verify(systemConfigurationRepository).findAll();
        verifyNoMoreInteractions(systemConfigurationRepository);
    }
}