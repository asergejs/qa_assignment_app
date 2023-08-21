package com.wandoofinance.qahomework.controllers;

import com.wandoofinance.qahomework.domain.dto.UpdatePersonalDataRequestDTO;
import com.wandoofinance.qahomework.domain.model.Message;
import com.wandoofinance.qahomework.domain.model.RegisterUserResponse;
import com.wandoofinance.qahomework.service.RegistrationService;
import com.wandoofinance.qahomework.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class UserDataController {

    private final RegistrationService registrationService;
    private final UserService userService;

    @PostMapping("/personal-data")
    public ResponseEntity<?> updatePersonalData(@RequestBody @Valid UpdatePersonalDataRequestDTO updatePersonalDataRequestDTO) {
        var user = registrationService.updatePersonalData(updatePersonalDataRequestDTO);
        return user.isPresent()
               ?  status(201).body(new RegisterUserResponse(user.get(), new Message("SUCCESS", "Personal info updated")))
               :  status(400).body(new Message("FAIL", "Something went wrong"));
    }

    @GetMapping("/balance")
    public ResponseEntity<?> getBalance() {
        return userService.getUserBalance()
                .map(balance -> ResponseEntity.ok().body(balance.toString()))
                .orElse(status(400).body("Something went wrong"));
    }

}
