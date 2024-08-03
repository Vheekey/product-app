package com.vheekey.crud.employee.models;

import com.vheekey.crud.employee.enums.Teams;

public class Employee {
    private String name;
    private String email;
    private boolean isActive;
    private Teams team;
    private Integer id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public Teams getTeam() {
        return team;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
