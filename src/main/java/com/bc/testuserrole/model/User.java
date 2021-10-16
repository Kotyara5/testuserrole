package com.bc.testuserrole.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    private String name;
    @Id
    private String login;
    private String password;
    @ManyToMany
    @JoinTable(
            name = "userrole",
            joinColumns = @JoinColumn(name = "user_login"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    List<Role> roles;

    public User() {}
    public User(String name, String login, String password, List<Role> roles) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
