package com.bank.transfer.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
public class CheckDailyLimitDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("CheckDailyLimitDelegate");
        System.out.println(delegateExecution.getVariable("getClientFoundsResponse"));
    }
}
