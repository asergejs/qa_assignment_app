package com.wandoofinance.qahomework.controllers;

import com.wandoofinance.qahomework.domain.model.UpdatePersonalDataRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api")
public class UserDataController {

    @PostMapping("/personal-data")
    public ResponseEntity<String> updatePersonalData(@RequestBody @Valid UpdatePersonalDataRequest updatePersonalDataRequest) {

        return ok().body("ok");
    }
}
