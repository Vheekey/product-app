package com.vheekey.crud.employee.entities;

import com.vheekey.crud.employee.enums.Teams;
import jakarta.persistence.*;

@Entity
@Table(name = "employees")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;

    private String password;

    private boolean isActive = true;

    @Enumerated(EnumType.STRING)
    private Teams team;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Teams getTeam() {
        return team;
    }

    public void setTeam(Teams team) {
//        this.team = Teams.valueOf(team);
        this.team = team;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
