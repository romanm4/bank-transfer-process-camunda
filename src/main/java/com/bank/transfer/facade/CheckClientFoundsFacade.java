package com.bank.transfer.facade;

import com.ws.CheckPossibilityServicePortType;
import com.ws.CheckPossibilityServiceRequest;
import com.ws.CheckPossibilityServiceResponse;
import com.ws.ObjectFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;;import java.util.Map;

@Component
public class CheckClientFoundsFacade extends AbstractTransferFacade {

    @Autowired
    private CheckPossibilityServicePortType checkPossibilityServicePortType;

    @Autowired
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
