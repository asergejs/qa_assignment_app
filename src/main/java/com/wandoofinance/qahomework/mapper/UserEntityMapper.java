package com.wandoofinance.qahomework.mapper;

import com.wandoofinance.qahomework.domain.dto.RegistrationRequestDTO;
import com.wandoofinance.qahomework.domain.dto.UpdatePersonalDataRequestDTO;
import com.wandoofinance.qahomework.domain.entity.User;

public class UserEntityMapper {

    public static User registrationReqToUserEntity(RegistrationRequestDTO registrationRequestDTO) {
      var userEntity = new User();
      userEntity.setEmail(registrationRequestDTO.getEmail());
      userEntity.setPassword(registrationRequestDTO.getPassword());
      return userEntity;
    }

    public static User personalDataReqToUserEntity(User userEntity, UpdatePersonalDataRequestDTO updatePersonalDataRequestDTO) {
        userEntity.setFirstName(updatePersonalDataRequestDTO.getFirstName());
        return userEntity;
    }

}
