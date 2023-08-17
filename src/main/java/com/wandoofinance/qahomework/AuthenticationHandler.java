package com.wandoofinance.qahomework;

import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationHandler {

    public void authenticate(Long userId) {
        SecurityContextHolder.getContext().setAuthentication(new UserAuthenticationToken(userId));
    }

}
