package com.bank.transfer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface IJsonConverter {
    Map<String, Object> getConvertJsonToMap(Object json) throws JsonProcessingException;
}
