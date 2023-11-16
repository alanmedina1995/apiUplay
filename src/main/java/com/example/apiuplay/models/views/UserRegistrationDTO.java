package com.example.apiuplay.models.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDTO {

    private String userName;
    private String email;
    private String password;
    private String name;
    private String lastName;
    private String phoneNumber;
    private Long questionId;
    private String answer;

}
