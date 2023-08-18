package com.wandoofinance.qahomework.mapper;

import com.wandoofinance.qahomework.domain.dto.RegistrationRequestDTO;
import com.wandoofinance.qahomework.domain.dto.TransactionRequestDTO;
import com.wandoofinance.qahomework.domain.entity.Payment;
import com.wandoofinance.qahomework.domain.entity.User;
import com.wandoofinance.qahomework.domain.model.TransactionType;

public class PaymentEntityMapper {

    public static Payment transactionReqToPaymentEntity(TransactionRequestDTO transactionRequestDTO, TransactionType type, User user) {
        var paymentEntity = new Payment();
        paymentEntity.setType(type);
        paymentEntity.setAmount(transactionRequestDTO.getAmount().getAmount());
        paymentEntity.setUser(user);
        return paymentEntity;
    }


    public static User registrationReqToUserEntity(RegistrationRequestDTO registrationRequestDTO) {
      var userEntity = new User();
      userEntity.setEmail(registrationRequestDTO.getEmail());
      userEntity.setPassword(registrationRequestDTO.getPassword());
      return userEntity;
    }

}
