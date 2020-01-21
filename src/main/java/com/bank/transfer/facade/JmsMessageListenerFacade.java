package com.bank.transfer.facade;

import com.bank.transfer.listener.model.BankTransferMessageRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import java.util.Map;

@Component
public class JmsMessageListenerFacade extends AbstractTransferFacade {

    @Autowired
    private RuntimeService runtimeService;

    public void startBankTransferProcess(ObjectMessage objectMessage) throws JsonProcessingException, JMSException {
        BankTransferMessageRequest bankTransferMessageRequest = (BankTransferMessageRequest) objectMessage.getObject();
        Map<String, Object> variables = getInitProcessVariableByObject(bankTransferMessageRequest);
        runtimeService.startProcessInstanceByKey("bankTransferProcessId", variables);
    }

}
