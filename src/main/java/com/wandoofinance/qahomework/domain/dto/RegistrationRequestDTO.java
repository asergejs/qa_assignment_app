package com.wandoofinance.qahomework.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class RegistrationRequestDTO {

    @NotEmpty
    @JsonProperty("email")
    private String email;

    @NotEmpty
    @JsonProperty("password")
    private String password;

}
