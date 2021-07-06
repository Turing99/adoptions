package com.p5.adoption.model;

public class UserDTO {

    protected Integer id;
    private String password;
    private String email;

    public UserDTO(Integer id, String password, String email) {
        this.id = id;
        this.password = password;
        this.email = email;
    }

    public UserDTO() {
    }

    public Integer getId() {
        return id;
    }

    public UserDTO setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserDTO setEmail(String email) {
        this.email = email;
        return this;
    }
}
