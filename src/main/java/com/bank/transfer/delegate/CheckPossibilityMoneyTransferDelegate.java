package com.bank.transfer.delegate;

import com.bank.transfer.facade.CheckPossibilityMoneyTransferFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CheckPossibilityMoneyTransferDelegate implements JavaDelegate {

    @Autowired
    private CheckPossibilityMoneyTransferFacade checkPossibilityMoneyTransferFacade;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        checkPossibilityMoneyTransferFacade.executeCheckClientFounds(delegateExecution);
    }
}
