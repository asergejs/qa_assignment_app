package com.wandoofinance.qahomework.controllers;

import com.wandoofinance.qahomework.domain.dto.UpdatePersonalDataRequestDTO;
import com.wandoofinance.qahomework.service.RegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api")
public class UserDataController {

    private final RegistrationService registrationService;

    public UserDataController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("/personal-data")
    public ResponseEntity<String> updatePersonalData(@RequestBody @Valid UpdatePersonalDataRequestDTO updatePersonalDataRequestDTO) {
        registrationService.updatePersonalData(updatePersonalDataRequestDTO);
        return ok().body("ok");
    }
}
