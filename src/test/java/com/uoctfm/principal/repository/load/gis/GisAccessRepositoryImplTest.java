package com.uoctfm.principal.repository.load.gis;

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

    @Test
    public void findById_shouldReturnNoResult_whenIdIsFour() {
        assertThat(underTest.findById(4L)).isNull();
    }

    @Test
    public void findById_shouldReturnNoResult_whenIdIsNotValid() {
        assertThat(underTest.findById(1000L)).isNull();
    }

}