package com.wandoofinance.qahomework.controllers;


import com.wandoofinance.qahomework.AuthenticationHandler;
import com.wandoofinance.qahomework.UserAuthenticationToken;
import com.wandoofinance.qahomework.domain.dto.RegistrationRequestDTO;
import com.wandoofinance.qahomework.domain.dto.SignInDTO;
import com.wandoofinance.qahomework.domain.dto.UserDTO;
import com.wandoofinance.qahomework.domain.model.Message;
import com.wandoofinance.qahomework.domain.model.RegisterUserResponse;
import com.wandoofinance.qahomework.service.UserAuthorizationService;
import com.wandoofinance.qahomework.service.RegistrationService;
import com.wandoofinance.qahomework.validation.RegistrationRequestValidator;
import com.wandoofinance.qahomework.validation.SignInRequestValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/public")
public class AuthenticationController {

    private final RegistrationService          registrationService;
    private final RegistrationRequestValidator registrationRequestValidator;
    private final UserAuthorizationService     authorizationService;
    private final SignInRequestValidator       signInRequestValidator;
    private final AuthenticationHandler        authenticationHandler;

    public AuthenticationController(RegistrationService registrationService, RegistrationRequestValidator registrationRequestValidator, UserAuthorizationService authorizationService, SignInRequestValidator signInRequestValidator, AuthenticationHandler authenticationHandler) {
        this.registrationService = registrationService;
        this.registrationRequestValidator = registrationRequestValidator;
        this.authorizationService = authorizationService;
        this.signInRequestValidator = signInRequestValidator;
        this.authenticationHandler = authenticationHandler;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestBody RegistrationRequestDTO req) {
        var validationStatus = registrationRequestValidator.validate(req);
        if (!validationStatus.isValid()) {
            return ResponseEntity.status(400).body(validationStatus.getMessage());
        }

        Optional<UserDTO> registeredUser = registrationService.registerUser(req);

        if (registeredUser.isPresent()) {
            authenticationHandler.authenticate(registeredUser.get().getId());
            return ResponseEntity.status(201)
                    .body(new RegisterUserResponse(registeredUser.get(), new Message("SUCCESS", "User registered")));
        }

        return ResponseEntity.status(400)
                .body(new Message("FAIL", "Something went wrong"));
    }

    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody SignInDTO signInDTO) {
        var validationStatus = signInRequestValidator.validate(signInDTO);
        if (!validationStatus.isValid()) {
            return ResponseEntity.status(400).body(validationStatus.getMessage());
        }

        Optional<UserDTO> authorizedUser = authorizationService.signIn(signInDTO);
        if (authorizedUser.isPresent()) {
            return ResponseEntity.status(201).body(new RegisterUserResponse(authorizedUser.get(), new Message("SUCCESS", "User authorized")));
        }
        return ResponseEntity.status(400).body(new Message("FAIL", "Unable to sign in"));
    }


}
