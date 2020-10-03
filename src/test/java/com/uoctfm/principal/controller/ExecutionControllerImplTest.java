package com.uoctfm.principal.controller;

import com.uoctfm.principal.domain.configuration.converter.JpaConverterJson;
import com.uoctfm.principal.flow.MainFlow;
import com.uoctfm.principal.flow.SystemFlow;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class ExecutionControllerImplTest {

    @InjectMocks
    ExecutionController underTest = new ExecutionControllerImpl();

    @Mock
    SystemFlow systemFlow;

    @Mock
    MainFlow mainFlow;

    @Test
    public void doExecution_shouldCallSystemFlow_onRegularExecution() {
        underTest.doExecution();

        verify(mainFlow).execute();
        verifyNoMoreInteractions(mainFlow);
        verifyNoInteractions(systemFlow);
    }

    @Test
    public void doExecutionById_shouldCallSystemFlow_onRegularExecution() {
        underTest.doExecutionById(1);

        verify(systemFlow).executeById(1);
        verifyNoMoreInteractions(systemFlow);
        verifyNoInteractions(mainFlow);
    }

}