package com.github.evgenius1424.spring_boot_playground;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class JsonMapperUtil {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T mapJsonToObject(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Failed to map JSON to " + clazz.getSimpleName(), e);
        }
    }

    public static <T> List<T> mapJsonToList(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(
                    json,
                    objectMapper.getTypeFactory().constructCollectionType(List.class, clazz)
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to map JSON to List of " + clazz.getSimpleName(), e);
        }
    }
}