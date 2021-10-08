package com.bc.testuserrole.model;

import javax.persistence.*;

@Entity
@Table(name = "role")
public class Role {
    @Id
    private int id;
    private String name;

    public Role(){}
    public Role(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
