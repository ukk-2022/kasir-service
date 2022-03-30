package com.backend.appService.auth.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;
    private String password;
    private String id;
    private Integer idRole;
    private String tokenKey;
    private String responseMessage;
}
