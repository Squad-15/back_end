package com.jota_nunes_back_end.jotanunes.controllers;

import com.jota_nunes_back_end.jotanunes.dtos.UserAccountDto;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.services.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserAccountController {
    @Autowired
    private UserAccountService userAccountService;

    @GetMapping("/all")
    public List<UserAccount> getAllUsers() {
        return userAccountService.getAllUser();
    }

    @GetMapping("/{id}")
    public Optional<UserAccount> getUserById(@PathVariable Long id) {
        return userAccountService.getUserById(id);
    }

    @PutMapping("/{id}")
    public UserAccount updateUser(@PathVariable Long id, @RequestBody UserAccountDto userAccountDto) {
        return userAccountService.updateUser(id, userAccountDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userAccountService.deleteUser(id);
    }
}
