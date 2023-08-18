package com.wandoofinance.qahomework.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wandoofinance.qahomework.domain.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PaymentDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("type")
    private TransactionType transactionType;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("rawResponse")
    private String rawResponse;
}


