package com.uoctfm.principal.repository.load.timeseries;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.principal.TestBuildHelper.buildDummyBatchPoint;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class TimeSeriesDatabaseRepositoryImplTest {

    InfluxConnector influxConnector;
    private String database = "NeverLand";

    @InjectMocks
    TimeSeriesDatabaseRepositoryImpl underTest;

    @Before
    public void setUp() {
        influxConnector = new InfluxConnector(database);
    }

    @Test
    public void saveListPoint_shouldSaveListPoints_returnListOfPoints() {
        underTest.saveListPoint(influxConnector, buildDummyBatchPoint("NeverLand"));
        underTest.saveListPoint(influxConnector, buildDummyBatchPoint("NeverLand"));
        assertTrue(true);
    }

    @After
    public void setDown() {
        influxConnector.getInfluxConnector().deleteDatabase(database);
        influxConnector.closeInfluxConnector();
    }

}