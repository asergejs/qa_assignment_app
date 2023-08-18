package com.wandoofinance.qahomework.service;


import com.wandoofinance.qahomework.AuthenticationHandler;
import com.wandoofinance.qahomework.domain.entity.User;
import com.wandoofinance.qahomework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;


@Slf4j
@Component
public class UserService {

    private final UserRepository userRepository;
    private final AuthenticationHandler authenticationHandler;

    public UserService(UserRepository userRepository, AuthenticationHandler authenticationHandler) {
        this.userRepository = userRepository;
        this.authenticationHandler = authenticationHandler;
    }

    public Optional<User> getCurrentUser() {
        Long userId = authenticationHandler.getCurrentUserId();
        return userRepository.findById(userId);
    }

    public Optional<BigDecimal> getUserBalance() {
       return getCurrentUser().map(User::getBalance);
    }

}
