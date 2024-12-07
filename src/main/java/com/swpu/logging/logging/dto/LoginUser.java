package com.swpu.logging.logging.dto;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    private String vcode;
}

