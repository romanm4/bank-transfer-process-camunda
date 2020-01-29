package com.bank.transfer.delegate;

import com.bank.transfer.facade.ISoapWebServiceFacade;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MakeMoneyTransferDelegate implements JavaDelegate {

    @Autowired
    @Qualifier("makeMoneyTransferFacade")
    private ISoapWebServiceFacade soapWebServiceFacade;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        soapWebServiceFacade.invokeSoapWebService(delegateExecution);
    }
}
