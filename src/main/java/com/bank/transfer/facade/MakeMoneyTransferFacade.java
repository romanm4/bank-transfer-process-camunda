package com.bank.transfer.facade;

import com.bank.transfer.facade.constant.ProcessConstants;
import com.ws.make_money_transfer.MakeMoneyTransferServicePortType;
import com.ws.make_money_transfer.MakeMoneyTransferServiceRequest;
import com.ws.make_money_transfer.MakeMoneyTransferServiceResponse;
import com.ws.make_money_transfer.ObjectFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MakeMoneyTransferFacade extends AbstractTransferFacade implements ISoapWebServiceFacade {

    @Autowired
    private MakeMoneyTransferServicePortType makeMoneyTransferServicePortType;

    @Autowired
    @Qualifier("makeMoneyTransferObjectFactory")
    private ObjectFactory objectFactory;

    @Override
    public void invokeSoapWebService(DelegateExecution delegateExecution) {
        MakeMoneyTransferServiceRequest request = getInitMakeMoneyTransferServiceRequest(delegateExecution);
        MakeMoneyTransferServiceResponse response = makeMoneyTransferServicePortType.makeMoneyTransfer(request);

    }

    private MakeMoneyTransferServiceRequest getInitMakeMoneyTransferServiceRequest(DelegateExecution execution) {
        MakeMoneyTransferServiceRequest request = objectFactory.createMakeMoneyTransferServiceRequest();
        request.setAccountNumber((String) execution.getVariable(ProcessConstants.TRANSFER_RECIPIENT_ACCOUNT_NUMBER_PROCESS_VARIABLE));
        request.setCode((String) execution.getVariable(ProcessConstants.WITHDRAW_CODE_PROCESS_VARIABLE));
        return request;
    }

    private void setTransferStatusByResponse() {

    }

    @Override
    public Object getInitRequest(DelegateExecution delegateExecution) {
        return null;
    }

    @Override
    public void mapResponseToExecutionVariables(Object response, DelegateExecution delegateExecution) {

    }
}
