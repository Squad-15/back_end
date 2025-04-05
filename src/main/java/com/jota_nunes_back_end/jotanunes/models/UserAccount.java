package com.jota_nunes_back_end.jotanunes.models;

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

    public UserAccount() {}

    public UserAccount(String username, String email, String numberRegister, String password) {
        this.username = username;
        this.email = email;
        this.numberRegister = numberRegister;
        this.password = password;
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

    @ManyToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
}
