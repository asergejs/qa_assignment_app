package com.wandoofinance.qahomework.service;

import com.wandoofinance.qahomework.domain.dto.TransactionRequestDTO;
import com.wandoofinance.qahomework.domain.entity.Payment;
import com.wandoofinance.qahomework.domain.entity.User;
import com.wandoofinance.qahomework.repository.PaymentRepository;
import com.wandoofinance.qahomework.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.wandoofinance.qahomework.domain.model.TransactionType.FUNDING;
import static com.wandoofinance.qahomework.mapper.PaymentEntityMapper.transactionReqToPaymentEntity;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentService {

    private final UserService userService;
    private final PaymentRepository paymentRepository;
    private final UserRepository userRepository;

    public Optional<Long> handleAddFundsPayment(TransactionRequestDTO transactionRequestDTO) {
        var user = userService.getCurrentUser();

        return user.map(maybeUser -> {
            Payment payment = transactionReqToPaymentEntity(transactionRequestDTO, FUNDING, maybeUser);
            maybeUser.setBalance(maybeUser.getBalance().add(payment.getAmount()));

            paymentRepository.save(payment);
            userRepository.save(maybeUser);

            return payment.getId();
        });
    }

}
