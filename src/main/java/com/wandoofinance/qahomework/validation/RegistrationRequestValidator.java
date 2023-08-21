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
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            return new ValidationStatus(false, new Message("fail", "Email already exists."));
        }

        return new ValidationStatus(true);
    }

}
