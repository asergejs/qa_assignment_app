package com.wandoofinance.qahomework.validation;

import com.wandoofinance.qahomework.domain.model.Message;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationStatus {
    private boolean isValid;
    private Message message;

    public ValidationStatus(boolean isValid) {
        this.isValid = isValid;
    }
}
