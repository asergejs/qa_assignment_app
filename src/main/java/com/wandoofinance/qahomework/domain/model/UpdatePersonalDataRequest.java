package com.wandoofinance.qahomework.domain.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@Data
public class UpdatePersonalDataRequest {

    @NotBlank(message = "First name cannot be null or blank")
    @Pattern(regexp = "^[^0-9]+$", message = "First name cannot contain numbers")
    private String firstName;

    @NotBlank(message = "Surname cannot be null or blank")
    @Pattern(regexp = "^[^0-9]+$", message = "Surname cannot contain numbers")
    private String surname;

    @NotNull(message = "Personal ID cannot be null")
    @Pattern(regexp = "^[0-9]{9}$", message = "Personal ID must be a 9-digit numeric value")
    private String personalId;

}
