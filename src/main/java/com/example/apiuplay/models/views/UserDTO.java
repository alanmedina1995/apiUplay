package com.example.apiuplay.models.views;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Long id;
    private String username;
    private String email;

    private String password;
    private String name;
    private String lastname;
    private String phonenumber;
    private Double utncoin;

    public UserDTO() {

    }

}
