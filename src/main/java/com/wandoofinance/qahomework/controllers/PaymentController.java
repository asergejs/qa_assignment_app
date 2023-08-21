package com.wandoofinance.qahomework.controllers;

import com.wandoofinance.qahomework.domain.dto.PaymentDTO;
import com.wandoofinance.qahomework.domain.dto.TransactionRequestDTO;
import com.wandoofinance.qahomework.mapper.PaymentDTOMapper;
import com.wandoofinance.qahomework.repository.PaymentRepository;
import com.wandoofinance.qahomework.service.PaymentService;
import com.wandoofinance.qahomework.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final PaymentService paymentService;
    private final UserService userService;

    @PostMapping("/add-funds")
    public ResponseEntity<?> addFunds(@RequestBody @Valid TransactionRequestDTO transactionRequestDTO) {
        return paymentService.handleAddFundsPayment(transactionRequestDTO)
                .map(paymentId -> ok().body("Payment imported, id: " + paymentId))
                .orElse(status(400).body("Something went wrong"));
    }

    @GetMapping("/payments")
    public List<PaymentDTO> getPayments() {
        var user = userService.getCurrentUser();
        return user.map(value -> paymentRepository.findByUserId(value.getId())
                        .stream()
                        .map(PaymentDTOMapper::toPaymentDTO)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList());
    }

}
