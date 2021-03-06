package com.uoctfm.principal.flow;

import com.uoctfm.principal.service.configuration.SystemConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

import static com.uoctfm.principal.TestBuildHelper.buildSystemConfigurationListDTOWithAUnabledConfiguration;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class MainFlowImplTest {

    @Mock
    private SystemConfiguration systemConfiguration;

    @Mock
    private SystemFlowImpl systemFlowImpl;

    @InjectMocks
    MainFlowImpl underTest = new MainFlowImpl();

    @Test
    public void execute_shouldAvoidExecutions_whenNotFoundConfiguration() {
        when(systemConfiguration.getSystemConfiguration()).thenReturn(new ArrayList<>());

        underTest.execute();

        verify(systemConfiguration).getSystemConfiguration();
        verifyNoMoreInteractions(systemConfiguration);

        verifyNoInteractions(systemFlowImpl);
    }

    @Test
    public void execute_shouldExecute2Times_whenTwoConfigurationEnablesOneConfigurationDisabled() {
        when(systemConfiguration.getSystemConfiguration()).thenReturn(buildSystemConfigurationListDTOWithAUnabledConfiguration());

        underTest.execute();

        verify(systemConfiguration).getSystemConfiguration();

        verifyNoMoreInteractions(systemConfiguration);
    }

}