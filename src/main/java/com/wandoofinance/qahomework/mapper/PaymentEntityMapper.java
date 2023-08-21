package com.wandoofinance.qahomework.mapper;

import com.wandoofinance.qahomework.domain.dto.TransactionRequestDTO;
import com.wandoofinance.qahomework.domain.entity.Payment;
import com.wandoofinance.qahomework.domain.entity.User;
import com.wandoofinance.qahomework.domain.model.TransactionType;
import lombok.SneakyThrows;

import static com.wandoofinance.qahomework.JsonConverter.toJsonString;

public class PaymentEntityMapper {

    @SneakyThrows
    public static Payment transactionReqToPaymentEntity(TransactionRequestDTO transactionRequestDTO, TransactionType type, User user) {
        var paymentEntity = new Payment();
        paymentEntity.setType(type);
        paymentEntity.setAmount(transactionRequestDTO.getAmount().getAmount());
        paymentEntity.setUser(user);
        paymentEntity.setRawResponse(toJsonString(transactionRequestDTO));
        return paymentEntity;
    }

}
