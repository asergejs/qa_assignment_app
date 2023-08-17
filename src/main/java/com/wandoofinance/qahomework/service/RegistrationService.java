package com.wandoofinance.qahomework.service;


import com.wandoofinance.qahomework.domain.dto.RegistrationRequestDTO;
import com.wandoofinance.qahomework.domain.dto.UserDTO;
import com.wandoofinance.qahomework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.wandoofinance.qahomework.mapper.UserDTOMapper.toUserDTO;
import static com.wandoofinance.qahomework.mapper.UserEntityMapper.registrationReqToUserEntity;
import static java.util.Optional.empty;

@Slf4j
@Component
public class RegistrationService {

    private final UserRepository userRepository;

    public RegistrationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserDTO> registerUser(RegistrationRequestDTO registrationRequestDTO) {
        try {
            var user = userRepository.save(registrationReqToUserEntity(registrationRequestDTO));
            log.info("Registering user with email: {}", user.getEmail());
            return Optional.of(toUserDTO(user));
        } catch (Exception ex) {
            log.error("Exception while signing up", ex);
            return empty();
        }
    }

}
