package com.uoctfm.principal.repository.load.gis;

import com.uoctfm.principal.domain.load.databases.GlobalStatistical;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GisAccessRepositoryImplTest {

    @Autowired
    private GisAccessRepositoryImpl underTest;

    @Before
    public void setUp() {
        underTest.save(buildGlobalStatistical(10001L));
    }

    @Test
    public void findById_shouldReturnNoResult_whenIdIsFour() {
        assertThat(underTest.findById(4L)).isNull();
    }

    @Test
    public void findById_shouldReturnNoResult_whenIdIsNotValid() {
        assertThat(underTest.findById(1000L)).isNull();
    }

    private static GlobalStatistical buildGlobalStatistical(Long id) {
        GeometryFactory geometryFactory = new GeometryFactory();

        GlobalStatistical globalStatistical = new GlobalStatistical();
        globalStatistical.setAverage(0.1);
        globalStatistical.setEntropy(1);
        globalStatistical.setEntropyNormalized(0.2);
        globalStatistical.setPoint(geometryFactory.createPoint(new Coordinate(0, 0)));
        globalStatistical.setSystem(1);

        return globalStatistical;
    }

}