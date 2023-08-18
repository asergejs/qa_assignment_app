package com.wandoofinance.qahomework.service;

import com.wandoofinance.qahomework.domain.dto.TransactionRequestDTO;
import com.wandoofinance.qahomework.domain.entity.Payment;
import com.wandoofinance.qahomework.domain.entity.User;
import com.wandoofinance.qahomework.repository.PaymentRepository;
import com.wandoofinance.qahomework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.wandoofinance.qahomework.domain.model.TransactionType.FUNDING;
import static com.wandoofinance.qahomework.mapper.PaymentEntityMapper.transactionReqToPaymentEntity;

@Slf4j
@Component
public class PaymentService {

    private final UserService       userService;
    private final PaymentRepository paymentRepository;
    private final UserRepository    userRepository;

    public PaymentService(UserService userService, PaymentRepository paymentRepository, UserRepository userRepository) {
        this.userService = userService;
        this.paymentRepository = paymentRepository;
        this.userRepository = userRepository;
    }

    public Optional<Long> handleAddFundsPayment(TransactionRequestDTO transactionRequestDTO) {
        var user = userService.getCurrentUser();

        if (user.isPresent()) {
            User currentUser = user.get();
            Payment payment = transactionReqToPaymentEntity(transactionRequestDTO, FUNDING, currentUser);
            currentUser.setBalance(currentUser.getBalance().add(payment.getAmount()));

            paymentRepository.save(payment);
            userRepository.save(currentUser);

            return Optional.of(payment.getId());
        }

        return Optional.empty();
    }

}
