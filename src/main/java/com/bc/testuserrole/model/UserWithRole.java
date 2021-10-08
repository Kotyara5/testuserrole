package com.bc.testuserrole.model;

import java.util.ArrayList;
import java.util.List;

public class UserWithRole {
    private User user;
    private List<Role> listRole = new ArrayList<>();

    public UserWithRole() {
    }

    public UserWithRole(User user, List<Role> listRole) {
        this.user = user;
        this.listRole.addAll(listRole);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Role> getListRole() {
        return listRole;
    }

    public void setListRole(List<Role> listRole) {
        this.listRole.addAll(listRole);
    }
}
