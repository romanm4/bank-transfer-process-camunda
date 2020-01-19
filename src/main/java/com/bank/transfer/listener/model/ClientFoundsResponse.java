package com.bank.transfer.listener.model;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.validation.annotation.Validated;


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
        "founds"
})
public class ClientFoundsResponse {

    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("founds")
    private String founds;

}
