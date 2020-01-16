package com.bank.transfer.common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class BankTransferMessage implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    private String accountNumber;
    private double transferAmount;
    private CurrencyType currencyType;
    private TransferRecipient transferRecipient;

    public BankTransferMessage(String accountNumber, double transferAmount, CurrencyType currencyType, TransferRecipient transferRecipient) {
        this.accountNumber = accountNumber;
        this.transferAmount = transferAmount;
        this.currencyType = currencyType;
        this.transferRecipient = transferRecipient;
    }
}
