package com.uoctfm.principal.domain.configuration.converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class LocalDateTimeAttributeConverterTest {

    @InjectMocks
    LocalDateTimeAttributeConverter underTest = new LocalDateTimeAttributeConverter();

    @Test
    public void convertToDatabaseColumn_shouldReturnNowInTimestamp_whenNowInLocalDateTimeIsProvided() {
        LocalDateTime now = LocalDateTime.now();
        assertThat(underTest.convertToDatabaseColumn(now)).isEqualTo(Timestamp.valueOf(now));
    }

    @Test
    public void convertToEntityAttribute_shouldReturnThreeStationMap_whenUseLastSampleExampleString() {
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        assertThat(underTest.convertToEntityAttribute(now).toString().replace("T", " ")).isEqualTo(now.toString());
    }

}