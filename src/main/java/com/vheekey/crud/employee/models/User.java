package com.vheekey.crud.employee.models;


import com.vheekey.crud.employee.enums.Teams;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {
    private String name;

    @NotNull(message = "Email required")
    @Email
    private String email;

    @NotNull(message = "Password required")
    @Size(min = 6, max = 10, message = "Password must be between 6 - 10 characters")
    private String password;

    private boolean isActive;

    @NotNull(message = "Team required")
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
        this.team = team;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }
}
