package com.wandoofinance.qahomework.domain.model;

import com.wandoofinance.qahomework.domain.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserResponse {
    private UserDTO user;
    private Message message;
}
