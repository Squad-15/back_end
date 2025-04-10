package com.jota_nunes_back_end.jotanunes.models;

import com.jota_nunes_back_end.jotanunes.enums.RoleUser;
import jakarta.persistence.*;

@Entity
@Table (name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String email;

    private String numberRegister;

    private String password;

    private String phone;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public UserAccount() {}

    public UserAccount(String username, String email, String numberRegister, String password, String phone, RoleUser roleUser) {
        this.username = username;
        this.email = email;
        this.numberRegister = numberRegister;
        this.password = password;
        this.phone = phone;
        this.roleUser = roleUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumberRegister() {
        return numberRegister;
    }

    public void setNumberRegister(String numberRegister) {
        this.numberRegister = numberRegister;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public RoleUser getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(RoleUser roleUser) {
        this.roleUser = roleUser;
    }

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
