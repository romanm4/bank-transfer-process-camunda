package com.bank.transfer.facade;

import com.bank.transfer.listener.model.BankTransferMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
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
    private ObjectMapper objectMapper;

    public void startBankTransferProcess(ObjectMessage objectMessage) throws JsonProcessingException, JMSException {
        BankTransferMessageRequest bankTransferMessageRequest = (BankTransferMessageRequest) objectMessage.getObject();
        Map<String, Object> variables = getInitProcessVariableByObject(bankTransferMessageRequest);
        runtimeService.startProcessInstanceByKey("bankTransferProcessId", variables);
    }

    private Map<String, Object> getInitProcessVariableByObject(Object response) {
        return objectMapper.convertValue(response, new TypeReference<Map<String, Object>>() {});
    }

}
