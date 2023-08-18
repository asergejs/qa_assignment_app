package com.wandoofinance.qahomework.controllers;

import com.wandoofinance.qahomework.domain.dto.TransactionRequestDTO;
import com.wandoofinance.qahomework.repository.PaymentRepository;
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
    private final UserService       userService;

    public PaymentController(PaymentRepository paymentRepository, UserService userService) {
        this.paymentRepository = paymentRepository;
        this.userService = userService;
    }

    @PostMapping("/add-funds")
    public ResponseEntity<?> addFunds(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        var user = userService.getCurrentUser();
        if (user.isPresent()) {
            var payment = paymentRepository.save(transactionReqToPaymentEntity(transactionRequestDTO, FUNDING, user.get()));
            return ok().body("Payment imported, id: " + payment.getId());
        }
        return status(400).body("Something went wrong");
    }

}
