package com.bank.transfer.facade;

import com.bank.transfer.facade.constant.ProcessConstants;
import com.bank.transfer.facade.constant.ResponseConstants;
import com.ws.check_recipient_in_system.CheckRecipientServicePortType;
import com.ws.check_recipient_in_system.CheckRecipientServiceRequest;
import com.ws.check_recipient_in_system.CheckRecipientServiceResponse;
import com.ws.check_recipient_in_system.ObjectFactory;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CheckRecipientInSystemFacade extends AbstractTransferFacade implements ISoapWebServiceFacade {

    @Autowired
    private CheckRecipientServicePortType checkRecipientServicePortType;

    @Autowired
    @Qualifier("checkRecipientObjectFactory")
    private ObjectFactory objectFactory;

    @Override
    public void invokeSoapWebService(DelegateExecution delegateExecution) {
        CheckRecipientServiceRequest request = getInitCheckRecipientServiceRequest(delegateExecution);
        CheckRecipientServiceResponse response = checkRecipientServicePortType.checkRecipient(request);
        setRecipientExistStatusByResponse(response, delegateExecution);
    }

    private CheckRecipientServiceRequest getInitCheckRecipientServiceRequest(DelegateExecution delegateExecution) {
        Map<String, Object> transferRecipient =
                (Map<String, Object>) delegateExecution.getVariable(ProcessConstants.TRANSFER_RECIPIENT_PROCESS_VARIABLE);
        CheckRecipientServiceRequest request = objectFactory.createCheckRecipientServiceRequest();
        request.setAccountNumber((String) transferRecipient.get(ProcessConstants.TRANSFER_RECIPIENT_ACCOUNT_NUMBER_PROCESS_VARIABLE));
        return request;
    }

    private void setRecipientExistStatusByResponse(CheckRecipientServiceResponse response, DelegateExecution delegateExecution) {
        if (response.getStatus().equals(ResponseConstants.TRANSFER_RECIPIENT_RESPONSE_STATUS))
            delegateExecution.setVariable("isRecipientExist", true);
    }
}
