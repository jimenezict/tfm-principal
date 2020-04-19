package com.uoctfm.principal.domain.configuration.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uoctfm.principal.domain.extraction.Station;

import javax.persistence.AttributeConverter;
import java.io.IOException;
import java.util.Map;

public class JpaConverterJson implements AttributeConverter<Object, String> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Object meta) {
        try {
            return objectMapper.writeValueAsString(meta);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    @Override
    public Map<Integer, Station> convertToEntityAttribute(String dbData) {
        try {
            return (Map<Integer, Station>) objectMapper.readValue(dbData, Object.class);
        } catch (IOException ex) {
            return null;
        }
    }
}
