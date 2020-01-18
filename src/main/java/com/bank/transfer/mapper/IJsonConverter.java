package com.bank.transfer.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Map;

public interface IJsonConverter {
    Object getConvertJsonToObject(String json) throws JsonProcessingException;
}
