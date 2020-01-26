package com.bank.transfer.delegate;

import com.bank.transfer.facade.CheckRecipientInSystemFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckRecipientInSystemDelegate implements JavaDelegate {

    @Autowired
    private CheckRecipientInSystemFacade checkRecipientInSystemFacade;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        checkRecipientInSystemFacade.executeCheckRecipient(delegateExecution);
    }
}
