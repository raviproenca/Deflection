package org.javasource.models.dto;

import lombok.Data;

@Data
public class UserLoginDTO {
    private String email;
    private String password;
}
