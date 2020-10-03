package com.uoctfm.principal.service.configuration;

import com.uoctfm.principal.repository.configuration.SystemStatisticsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static com.uoctfm.principal.TestBuildHelper.buildSystemStatisticsDTO;
import static java.lang.Boolean.TRUE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SystemStatisticsImplTest {

    @InjectMocks
    private SystemStatistics underTest = new SystemStatisticsImpl();

    @Mock
    private SystemStatisticsRepository systemStatisticsRepository;

    @Test
    public void insert_shouldSave_whenNoExcption() {
        ReflectionTestUtils.setField(underTest, "systemStatistics", TRUE);
        underTest.insert(buildSystemStatisticsDTO());

        verify(systemStatisticsRepository).save(any());
        verifyNoMoreInteractions(systemStatisticsRepository);
    }

}