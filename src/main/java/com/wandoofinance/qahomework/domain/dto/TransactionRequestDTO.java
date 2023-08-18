package com.wandoofinance.qahomework.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wandoofinance.qahomework.domain.model.TransactionType;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequestDTO {
    @JsonProperty("accountNumber")
    private String accountNumber;

    @JsonProperty("accountHolderFullName")
    private String accountHolderFullName;

    @JsonProperty("accountHolderPersonalId")
    private String accountHolderPersonalId;

    @JsonProperty("transactionType")
    private TransactionType transactionType;

    @JsonProperty("investorId")
    private String investorId;

    @JsonProperty("amount")
    private AmountDTO amount;

    @JsonProperty("bookingDate")
    private LocalDate bookingDate;

    @Getter
    @Setter
    public static class AmountDTO {
        @JsonProperty("currency")
        private String currency;

        @JsonProperty("amount")
        private BigDecimal amount;
    }
}

