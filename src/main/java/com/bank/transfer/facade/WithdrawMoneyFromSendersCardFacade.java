package com.bank.transfer.facade;

import com.bank.transfer.facade.constant.ProcessConstants;
import com.ws.withdraw_money_from_senders_card.ObjectFactory;
import com.ws.withdraw_money_from_senders_card.WithdrawMoneyFromSendersCardServicePortType;
import com.ws.withdraw_money_from_senders_card.WithdrawMoneyFromSendersCardServiceRequest;
import com.ws.withdraw_money_from_senders_card.WithdrawMoneyFromSendersCardServiceResponse;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class WithdrawMoneyFromSendersCardFacade extends AbstractTransferFacade implements ISoapWebServiceFacade {

    @Autowired
    private WithdrawMoneyFromSendersCardServicePortType withdrawMoneyFromSendersCardServicePortType;

    @Autowired
    @Qualifier("withdrawMoneyFromSendersCardObjectFactory")
    private ObjectFactory objectFactory;

    @Override
    public void invokeSoapWebService(DelegateExecution delegateExecution) {
        WithdrawMoneyFromSendersCardServiceRequest request =
                getInitWithdrawMoneyFromSendersCardServiceRequest(delegateExecution);
        WithdrawMoneyFromSendersCardServiceResponse response =
                withdrawMoneyFromSendersCardServicePortType.withdrawMoneyFromSendersCard(request);
        setCodeByResponse(response, delegateExecution);
    }

    private WithdrawMoneyFromSendersCardServiceRequest getInitWithdrawMoneyFromSendersCardServiceRequest(DelegateExecution delegateExecution) {
        WithdrawMoneyFromSendersCardServiceRequest request = objectFactory.createWithdrawMoneyFromSendersCardServiceRequest();
        request.setAccountNumber((String) delegateExecution.getVariable(ProcessConstants.SENDER_ACCOUNT_NUMBER_PROCESS_VARIABLE));
        request.setAmount(ProcessConstants.TRANSFER_AMOUNT_PROCESS_VARIABLE);
        request.setCurrency(ProcessConstants.CURRENCY_TYPE_PROCESS_VARIABLE);
        return request;
    }

    private void setCodeByResponse(WithdrawMoneyFromSendersCardServiceResponse response, DelegateExecution delegateExecution) {
        delegateExecution.setVariable(ProcessConstants.WITHDRAW_CODE_PROCESS_VARIABLE, response.getCode());
    }

    @Override
    public Object getInitRequest(DelegateExecution delegateExecution) {
        return null;
    }

    @Override
    public void mapResponseToExecutionVariables(Object response, DelegateExecution delegateExecution) {

    }
}
