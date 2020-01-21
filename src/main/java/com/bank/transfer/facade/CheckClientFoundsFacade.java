package com.bank.transfer.facade;

import com.bank.transfer.listener.model.ClientFoundsResponse;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class CheckClientFoundsFacade extends AbstractTransferFacade {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    @Value("${mock.found.client.url}")
    private String url;

    public void executeCheckClientFounds(DelegateExecution delegateExecution) {
        ResponseEntity<ClientFoundsResponse> responseEntity =
                restTemplate.exchange(
                        url + delegateExecution.getVariable("accountNumber"),
                        HttpMethod.GET,
                        httpEntity,
                        ClientFoundsResponse.class
                );
        delegateExecution.setVariable("isInsufficient", true);
    }

}
