package com.bank.transfer.facade;

import org.camunda.bpm.engine.delegate.DelegateExecution;

public interface ISoapWebServiceFacade {
    void invokeSoapWebService(DelegateExecution delegateExecution);
}
