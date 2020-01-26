package com.bank.transfer.facade;

import com.ws.check_possibility_service.CheckPossibilityServicePortType;
import com.ws.check_possibility_service.CheckPossibilityServiceRequest;
import com.ws.check_possibility_service.CheckPossibilityServiceResponse;
import com.ws.check_possibility_service.ObjectFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class CheckPossibilityMoneyTransferFacade extends AbstractTransferFacade {

    @Autowired
    private CheckPossibilityServicePortType checkPossibilityServicePortType;

    @Autowired
    @Qualifier("checkPossibilityObjectFactory")
    private ObjectFactory objectFactory;

    public void executeCheckClientFounds(DelegateExecution delegateExecution) {
        CheckPossibilityServiceRequest request =
                getInitCheckPossibilityServiceRequest(delegateExecution);
        CheckPossibilityServiceResponse response = checkPossibilityServicePortType.checkPossibility(request);
        setInsufficientStatusByResponse(response, delegateExecution);
    }

    private CheckPossibilityServiceRequest getInitCheckPossibilityServiceRequest(DelegateExecution delegateExecution) {
        CheckPossibilityServiceRequest checkPossibilityServiceRequest =
                objectFactory.createCheckPossibilityServiceRequest();
        checkPossibilityServiceRequest.setAccountNumber((String) delegateExecution.getVariable(ProcessConstants.SENDER_ACCOUNT_NUMBER_PROCESS_VARIABLE));
        checkPossibilityServiceRequest.setCurrencyType((String) delegateExecution.getVariable(ProcessConstants.CURRENCY_TYPE_PROCESS_VARIABLE));
        checkPossibilityServiceRequest.setTransferAmount((Double) delegateExecution.getVariable(ProcessConstants.TRANSFER_AMOUNT_PROCESS_VARIABLE));
        return checkPossibilityServiceRequest;
    }

    private void setInsufficientStatusByResponse(CheckPossibilityServiceResponse response, DelegateExecution delegateExecution) {
        if (response.getStatus().equals(ResponseConstants.CLIENT_FOUNDS_RESPONSE_STATUS))
            delegateExecution.setVariable(ProcessConstants.IS_INSUFFICIENT_PROCESS_VARIABLE, true);
        else
            delegateExecution.setVariable(ProcessConstants.IS_INSUFFICIENT_PROCESS_VARIABLE, false);
    }

}
