package com.bank.transfer.facade;

import com.bank.transfer.api.ws.SoapWebServiceConnector;
import com.bank.transfer.api.ws.wsdl.CheckPossibilityServiceRequest;
import com.bank.transfer.api.ws.wsdl.CheckPossibilityServiceResponse;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.client.core.WebServiceTemplate;


@Component
public class CheckClientFoundsFacade extends AbstractTransferFacade {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    @Value("${mock.soap.check-possibility}")
    private String url;

    public void executeCheckClientFounds(DelegateExecution delegateExecution) {
        CheckPossibilityServiceRequest request = new CheckPossibilityServiceRequest();
        request.setAccountNumber("123");
        request.setCurrencyType("123");
        request.setTransferAmount(123);
        CheckPossibilityServiceResponse response =
                (CheckPossibilityServiceResponse) webServiceTemplate.marshalSendAndReceive(url, request);
        delegateExecution.setVariable("isInsufficient", true);
    }

}
