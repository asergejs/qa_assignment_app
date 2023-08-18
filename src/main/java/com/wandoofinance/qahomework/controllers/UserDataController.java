package com.wandoofinance.qahomework.controllers;

import com.wandoofinance.qahomework.domain.dto.UpdatePersonalDataRequestDTO;
import com.wandoofinance.qahomework.domain.model.Message;
import com.wandoofinance.qahomework.domain.model.RegisterUserResponse;
import com.wandoofinance.qahomework.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping(path = "/api")
public class UserDataController {

    private final RegistrationService registrationService;

    public UserDataController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/personal-data")
    public ResponseEntity<?> updatePersonalData(@RequestBody @Valid UpdatePersonalDataRequestDTO updatePersonalDataRequestDTO) {
        var user = registrationService.updatePersonalData(updatePersonalDataRequestDTO);
        return user.isPresent()
               ?  status(201).body(new RegisterUserResponse(user.get(), new Message("SUCCESS", "Personal info updated")))
               :  status(400).body(new Message("FAIL", "Something went wrong"));
    }
}
