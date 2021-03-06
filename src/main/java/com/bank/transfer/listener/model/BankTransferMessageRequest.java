package com.bank.transfer.listener.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Validated
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "accountNumber",
        "transferAmount",
        "currencyType",
        "transferRecipient"
})
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
