package com.bank.transfer.delegate;

import com.bank.transfer.client.IRestConsumer;
import com.bank.transfer.mapper.IJsonConverter;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CheckClientFoundsDelegate implements JavaDelegate {

    @Autowired
    private IRestConsumer restConsumer;

    @Autowired
    private IJsonConverter jsonConverter;

    @Value("${mock.found.client.url}")
    private String url;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        delegateExecution.setVariable("isLimit", true);
        delegateExecution.setVariable("isInsufficient", true);
        delegateExecution.setVariable("isRecipientExist", true);
        delegateExecution.setVariable("getClientFoundsResponse", jsonConverter.getConvertJsonToMap(
                        restConsumer.sendGetHttpMethod(url)
                ));
    }
}
