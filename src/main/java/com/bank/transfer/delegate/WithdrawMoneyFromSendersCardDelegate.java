package com.bank.transfer.delegate;

import com.bank.transfer.facade.WithdrawMoneyFromSendersCardFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WithdrawMoneyFromSendersCardDelegate implements JavaDelegate {

    @Autowired
    private WithdrawMoneyFromSendersCardFacade withdrawMoneyFromSendersCardFacade;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        withdrawMoneyFromSendersCardFacade.executeWithdrawMoneyFromCard(delegateExecution);
    }
}
