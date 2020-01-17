package com.bank.transfer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class JsonConverter implements IJsonConverter {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Map<String, Object> getConvertJsonToMap(Object json) throws JsonProcessingException {
        return objectMapper.readValue(objectMapper.writeValueAsString(json), Map.class);
    }
}
