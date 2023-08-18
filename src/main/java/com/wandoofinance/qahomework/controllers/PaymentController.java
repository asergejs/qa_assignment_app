package com.wandoofinance.qahomework.controllers;

import com.wandoofinance.qahomework.domain.dto.TransactionRequestDTO;
import com.wandoofinance.qahomework.repository.PaymentRepository;
import com.wandoofinance.qahomework.service.PaymentService;
import com.wandoofinance.qahomework.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.wandoofinance.qahomework.domain.model.TransactionType.FUNDING;
import static com.wandoofinance.qahomework.mapper.PaymentEntityMapper.transactionReqToPaymentEntity;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestController
@RequestMapping(path = "/api")
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final PaymentService    paymentService;

    public PaymentController(PaymentRepository paymentRepository, PaymentService paymentService) {
        this.paymentRepository = paymentRepository;
        this.paymentService = paymentService;
    }

    @PostMapping("/add-funds")
    public ResponseEntity<?> addFunds(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        return paymentService.handleAddFundsPayment(transactionRequestDTO)
                .map(paymentId -> ok().body("Payment imported, id: " + paymentId))
                .orElse(status(400).body("Something went wrong"));
    }

}
