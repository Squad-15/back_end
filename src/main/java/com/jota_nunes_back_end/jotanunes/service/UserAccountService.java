package com.jota_nunes_back_end.jotanunes.service;

import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordService passwordService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserAccount createUser(UserAccount user) {
        // generate register number
        String nextRegisterNumber = generateNextRegisterNumber();
        user.setNumberRegister(nextRegisterNumber);

        // generate password and Crypt
        String rawPassword = passwordService.generateRandomPassword(6);
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        System.out.println("Senha temporária do usuário: " + rawPassword);
        return userAccountRepository.save(user);
    }

    private String generateNextRegisterNumber() {
        Optional<UserAccount> lastUser = userAccountRepository.findTopByOrderByNumberRegisterDesc();

        int nextSequence = 1;

        if (lastUser.isPresent()) {
            String lastRegister = lastUser.get().getNumberRegister();

            String baseNumber = lastRegister.substring(0, 5);

            try {
                nextSequence = Integer.parseInt(baseNumber) + 1;
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Número de registro inválido no banco de dados: " + lastRegister);
            }
        }

        if (nextSequence > 99999) {
            throw new IllegalStateException("Limite de registros atingido");
        }

        int randomDigit = new Random().nextInt(10);
        return String.format("%05d%d", nextSequence, randomDigit);
    }

}
