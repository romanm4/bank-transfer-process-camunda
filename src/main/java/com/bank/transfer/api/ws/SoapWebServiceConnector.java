package com.bank.transfer.api.ws;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class SoapWebServiceConnector extends WebServiceGatewaySupport {
    public Object callWebService(String url, Object request){
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(url, request);
        return res.getValue();
    }
}
