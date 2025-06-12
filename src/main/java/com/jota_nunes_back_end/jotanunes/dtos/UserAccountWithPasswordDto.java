package com.jota_nunes_back_end.jotanunes.dtos;

import com.jota_nunes_back_end.jotanunes.models.UserAccount;

public class UserAccountWithPasswordDto {
    private String firstName;
    private String email;
    private String numberRegister;
    private String passwordPlain;

    public UserAccountWithPasswordDto(UserAccount userAccount, String passwordPlain) {
        this.firstName = userAccount.getFirstName();
        this.email = userAccount.getEmail();
        this.numberRegister = userAccount.getNumberRegister();
        this.passwordPlain = passwordPlain;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumberRegister() {
        return numberRegister;
    }

    public String getPasswordPlain() {
        return passwordPlain;
    }
}
