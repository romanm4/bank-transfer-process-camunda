package com.bank.transfer.facade;

import com.bank.transfer.mapper.IJsonConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Map;

@Component
public class JmsMessageListenerFacade {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private IJsonConverter jsonConverter;

    public void startBankTransferProcess(ObjectMessage objectMessage) throws JsonProcessingException, JMSException {
        runtimeService.startProcessInstanceByKey(
                "bankTransferProcessId",
                getInitProcessVariableByMessage(objectMessage.getObject())
        );
    }

    private Map<String, Object> getInitProcessVariableByMessage(Object message) throws JsonProcessingException {
        return jsonConverter.getConvertJsonToMap(message);
    }
}
