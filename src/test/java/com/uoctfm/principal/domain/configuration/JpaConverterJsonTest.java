package com.uoctfm.principal.domain.configuration;

import com.uoctfm.principal.domain.configuration.converter.JpaConverterJson;
import com.uoctfm.principal.domain.extraction.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static com.uoctfm.principal.TestBuildHelper.buildStationsStatusDTO;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class JpaConverterJsonTest {

    private final static String LAST_SAMPLE_EXAMPLE = "{\"1\":{\"id\":1,\"numBicicles\":10,\"sizeStation\":30,\"percentil\":3,\"percentage\":33},\"2\":{\"id\":2,\"numBicicles\":0,\"sizeStation\":30,\"percentil\":0,\"percentage\":0},\"3\":{\"id\":3,\"numBicicles\":22,\"sizeStation\":30,\"percentil\":7,\"percentage\":73}}";

    @InjectMocks
    JpaConverterJson underTest = new JpaConverterJson();

    @Test
    public void convertToDatabaseColumn_shouldReturnLongString_whenMapsToString() {
        assertThat(underTest.convertToDatabaseColumn(buildStationsStatusDTO().getStationList())).hasSizeGreaterThan(50);
    }

    @Test
    public void convertToEntityAttribute_shouldReturnThreeStationMap_whenUseLastSampleExampleString() {
        Map<Integer, Station> mapOfStations = (Map<Integer, Station>) underTest.convertToEntityAttribute(LAST_SAMPLE_EXAMPLE);
        assertThat(mapOfStations.keySet().size()).isEqualTo(3);
    }
}