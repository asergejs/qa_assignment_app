package com.wandoofinance.qahomework.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignInDTO {

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;
}
