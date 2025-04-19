package com.jota_nunes_back_end.jotanunes.models;

import com.jota_nunes_back_end.jotanunes.enums.RoleUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table (name = "user_account")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "O campo nome é obrigatório.")
    @Column(nullable = false)
    private String firstName;

    @NotBlank(message = "O campo sobrenome é obrigatório.")
    @Column(nullable = false)
    private String lastName;

    @NotBlank(message = "O campo e-mail é obrigatório.")
    @Email(message = "O e-mail é inválido.")
    @Column(nullable = false, unique = true)
    private String email;

    private String numberRegister;

    private String password;

    @NotBlank(message = "O campo telefone é obrigatório.")
    @Column(nullable = false)
    private String phone;

    @Enumerated(EnumType.STRING)
    private RoleUser roleUser;

    public UserAccount() {}

    public UserAccount(String firstName, String lastName, String email, String numberRegister, String password, String phone, RoleUser roleUser) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

}
