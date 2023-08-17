package com.wandoofinance.qahomework.validation;

import com.wandoofinance.qahomework.domain.dto.RegistrationRequestDTO;
import com.wandoofinance.qahomework.domain.model.Message;
import com.wandoofinance.qahomework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RegistrationRequestValidator {

    final
    UserRepository userRepository;

    public RegistrationRequestValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ValidationStatus validate(RegistrationRequestDTO requestDTO) {

        if (requestDTO.getEmail() == null || requestDTO.getPassword() == null) {
            return new ValidationStatus(false, new Message("fail", "Mandatory fields are empty"));
        }

        if (requestDTO.getEmail().isBlank() || requestDTO.getPassword().isBlank()) {
            return new ValidationStatus(false, new Message("fail", "Incorrect format of mandatory fields"));
        }

        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            return new ValidationStatus(false, new Message("fail", "Wrong email"));
        }

        return new ValidationStatus(true);
    }

}
