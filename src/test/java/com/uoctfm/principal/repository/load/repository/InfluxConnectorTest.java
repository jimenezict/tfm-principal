package com.uoctfm.principal.repository.load.repository;

import org.influxdb.dto.Pong;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import static com.uoctfm.principal.repository.load.repository.InfluxConnector.getInfluxConnector;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class InfluxConnectorTest {

    @Before
    public void setUp() {
        getInfluxConnector().createDatabase("NeverLand");
    }

    @Test
    public void ping_shouldResponse_whenConnectsToTestDatabase() {
        Pong response = getInfluxConnector().ping();
        assertThat(response.getVersion()).isEqualTo("1.8.0");
    }

    @Test
    public void showdatabase_shouldReturnNeverLand_whenNeverLandDatabaseIsCreated() {
        assertThat(getInfluxConnector().describeDatabases().contains("NeverLand")).isTrue();
    }

    @Before
    public void setDown() {
        getInfluxConnector().deleteDatabase("NeverLand");
    }

}