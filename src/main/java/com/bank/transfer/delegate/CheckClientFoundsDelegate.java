package com.bank.transfer.delegate;

import com.bank.transfer.listener.model.ClientFoundsResponse;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class CheckClientFoundsDelegate implements JavaDelegate {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    @Value("${mock.found.client.url}")
    private String url;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("isLimit", true);
        delegateExecution.setVariable("isInsufficient", true);
        delegateExecution.setVariable("isRecipientExist", true);
        ResponseEntity<ClientFoundsResponse> responseResponseEntity =
                restTemplate.exchange(url, HttpMethod.GET, httpEntity, ClientFoundsResponse.class);
        System.out.println(responseResponseEntity.getBody().toString());
    }
}
