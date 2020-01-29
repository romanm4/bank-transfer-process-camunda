package com.bank.transfer.facade;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Component;


@Component
public abstract class AbstractTransferFacade {

    public abstract Object getInitRequest(DelegateExecution delegateExecution);

    public abstract void mapResponseToExecutionVariables(Object response, DelegateExecution delegateExecution);

}
