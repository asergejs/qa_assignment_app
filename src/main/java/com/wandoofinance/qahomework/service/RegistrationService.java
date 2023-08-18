package com.wandoofinance.qahomework.service;


import com.wandoofinance.qahomework.AuthenticationHandler;
import com.wandoofinance.qahomework.domain.dto.RegistrationRequestDTO;
import com.wandoofinance.qahomework.domain.dto.UpdatePersonalDataRequestDTO;
import com.wandoofinance.qahomework.domain.dto.UserDTO;
import com.wandoofinance.qahomework.mapper.UserDTOMapper;
import com.wandoofinance.qahomework.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

import static com.wandoofinance.qahomework.mapper.UserDTOMapper.toUserDTO;
import static com.wandoofinance.qahomework.mapper.UserEntityMapper.registrationReqToUserEntity;
import static java.util.Optional.empty;

@Slf4j
@Component
public class RegistrationService {

    private final UserRepository userRepository;
    private final AuthenticationHandler authenticationHandler;

    public RegistrationService(UserRepository userRepository, AuthenticationHandler authenticationHandler) {
        this.userRepository = userRepository;
        this.authenticationHandler = authenticationHandler;
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

    public Optional<UserDTO> updatePersonalData(UpdatePersonalDataRequestDTO updatePersonalDataRequestDTO) {
        try {
            var  user   = userRepository.findById(authenticationHandler.getCurrentUserId());
            user.ifPresent(it -> {
                it.setFirstName(updatePersonalDataRequestDTO.getFirstName());
                it.setSurname(updatePersonalDataRequestDTO.getSurname());
                it.setPersonalId(updatePersonalDataRequestDTO.getPersonalId());
                it.setBalance(BigDecimal.ZERO);
                userRepository.save(it);
            });
            return Optional.ofNullable(user.map(UserDTOMapper::toUserDTO)
                    .orElseThrow(Exception::new));
        } catch (Exception ex) {
            log.error("Exception while updating personal data", ex);
            return empty();
        }
    }

}
