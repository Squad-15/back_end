package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.AuthenticateDto;
import com.jota_nunes_back_end.jotanunes.dtos.LoginResponseDto;
import com.jota_nunes_back_end.jotanunes.dtos.UserAccountDto;
import com.jota_nunes_back_end.jotanunes.dtos.UserAccountWithPasswordDto;
import com.jota_nunes_back_end.jotanunes.infra.security.TokenService;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.services.UserAccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserAuthController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserAccount> createUser(@RequestBody @Valid UserAccountDto userAccountDto) {
        try {
            UserAccount created = userAccountService.createUser(userAccountDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticateDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.numberRegister(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserAccount) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }
}

