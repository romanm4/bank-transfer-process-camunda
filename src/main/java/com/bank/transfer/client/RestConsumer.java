package com.bank.transfer.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


@Component
public class RestConsumer implements IRestConsumer {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    @Override
    public String sendGetHttpMethod(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class).getBody();
    }
}
