package com.example.apiuplay.models;

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private String name;
    private String lastname;
    private int utncoin;

    public UserDTO() {

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public int getUtncoin() {
        return utncoin;
    }

    public void setUtncoin(int utncoin) {
        this.utncoin = utncoin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
