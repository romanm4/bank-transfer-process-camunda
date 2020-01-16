package com.bank.transfer.listener;

import com.bank.transfer.facade.JmsMessageListenerFacade;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.activemq.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;


@Component
public class JmsMessageListener {

    @Autowired
    private JmsMessageListenerFacade jmsMessageListenerFacade;

    @JmsListener(destination = "bank-transaction-queue-in")
    public void receiveMessage(Message message) throws JMSException, JsonProcessingException {
        jmsMessageListenerFacade.startBankTransferProcess((ObjectMessage) message);
    }


}
