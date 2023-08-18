package com.wandoofinance.qahomework.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api")
public class PaymentController {

    @GetMapping("/test")
    public ResponseEntity<String> testEndpoint() {
        return ok().body("ok");
    }

}
