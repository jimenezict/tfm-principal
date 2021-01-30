package com.uoctfm.principal.repository.load.timeseries;

import org.influxdb.dto.Pong;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class InfluxConnectorTest {

    InfluxConnector influxConnector;
    private String database = "NeverLand";

    @Before
    public void setUp() {
        influxConnector = new InfluxConnector(database);
    }

    @Test
    public void ping_shouldResponse_whenConnectsToTestDatabase() {
        Pong response = influxConnector.getInfluxConnector().ping();
        assertThat(response.getVersion()).isEqualTo("1.8.0");
    }S

    @Test
    public void showdatabase_shouldReturnNeverLand_whenNeverLandDatabaseIsCreated() {
        assertThat(influxConnector.getInfluxConnector().describeDatabases().contains(database)).isTrue();
    }

    @After
    public void setDown() {
        influxConnector.getInfluxConnector().deleteDatabase(database);
        influxConnector.closeInfluxConnector();
    }

}