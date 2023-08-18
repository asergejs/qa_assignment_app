package com.wandoofinance.qahomework.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wandoofinance.qahomework.domain.model.TransactionType;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionRequestDTO {

    @NotBlank(message = "Account number is required")
    @NotNull
    @JsonProperty("accountNumber")
    private String accountNumber;

    @NotBlank(message = "Account holder full name is required")
    @NotNull
    private String accountHolderFullName;

    @NotBlank(message = "Account holder personal ID is required")
    @NotNull
    private String accountHolderPersonalId;

    @NotNull(message = "Transaction type is required")
    @NotNull
    private TransactionType transactionType;

    @NotBlank(message = "Investor ID is required")
    @NotNull
    private String investorId;

    @Valid
    private AmountDTO amount;

    @NotNull(message = "Booking date is required")
    @PastOrPresent(message = "Booking date must be in the past or present")
    private LocalDate bookingDate;

    @Getter
    @Setter
    public static class AmountDTO {
        @NotBlank(message = "Currency is required")
        @NotNull
        private String currency;

        @NotNull(message = "Amount is required")
        @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
        private BigDecimal amount;
    }
}


