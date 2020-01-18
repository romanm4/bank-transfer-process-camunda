package com.bank.transfer.listener.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class TransferRecipient implements Serializable {

    private String accountNumber;
    private String bankName;

    public TransferRecipient(String accountNumber, String bankName) {
        this.accountNumber = accountNumber;
        this.bankName = bankName;
    }
}
