package com.example.rc.samples;

import java.io.Serializable;

/**
 * Created by RENT on 2017-04-22.
 */

public class LoginModel implements Serializable {

    private String email;

    private String password;

    private String token;

    private Long id;

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getToken() {
        return token;
    }

    public Long getId() {
        return id;
    }
}
