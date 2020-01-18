package com.bank.transfer.listener.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Validated
public class BankTransferMessageRequest implements Serializable {

    private static final long serialVersionUID = -295422703255886286L;

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("transferAmount")
    private double transferAmount;

    @JsonProperty("currencyType")
    private CurrencyType currencyType;

    @JsonProperty("transferRecipient")
    private TransferRecipient transferRecipient;

}
