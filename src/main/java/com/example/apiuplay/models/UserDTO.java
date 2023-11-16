package com.example.apiuplay.models;

public class UserDTO {

    private Long id;
    private String username;
    private String email;

    private String password;
    private String name;
    private String lastname;
    private String phonenumber;
    private int utncoin;

    public UserDTO() {

    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
