package com.example.apiuplay.models.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String phonenumber;
    private int utncoin;

    public User(String username, String email, String password,
                String name, String lastname, String phonenumber) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.utncoin = 25;
    }

    public User(Long id, String username, String email, String password,
                String name, String lastname, String phonenumber) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.utncoin = 25;
    }

    public User() {
    }

}
