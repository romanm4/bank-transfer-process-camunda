package com.bank.transfer.delegate;

import com.bank.transfer.facade.CheckClientFoundsFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CheckClientFoundsDelegate implements JavaDelegate {

    @Autowired
    private CheckClientFoundsFacade checkClientFoundsFacade;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        checkClientFoundsFacade.executeCheckClientFounds(delegateExecution);
    }
}
