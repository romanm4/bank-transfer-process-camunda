package com.bank.transfer.facade;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public abstract class AbstractTransferFacade {

    @Autowired
    private ObjectMapper objectMapper;

    public Map<String, Object> getInitProcessVariableByObject(Object response) {
        return objectMapper.convertValue(response, new TypeReference<Map<String, Object>>() {});
    }

}
