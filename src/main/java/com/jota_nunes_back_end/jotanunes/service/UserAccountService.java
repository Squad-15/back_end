package com.jota_nunes_back_end.jotanunes.service;

import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount createUser(UserAccount user) {
        String nextRegisterNumber = generateNextRegisterNumber();
        user.setNumberRegister(nextRegisterNumber);
        return userAccountRepository.save(user);
    }

    private String generateNextRegisterNumber() {
        Optional<UserAccount> lastUser = userAccountRepository.findTopByOrderByNumberRegisterDesc();

        int nextNumber = 1;

        if (lastUser.isPresent()) {
            String lastRegister = lastUser.get().getNumberRegister();
            try {
                nextNumber = Integer.parseInt(lastRegister) + 1;
            } catch (NumberFormatException e) {
                throw new IllegalStateException("Número de registro inválido no banco de dados: " + lastRegister);
            }
        }

        if (nextNumber > 999999) {
            throw new IllegalStateException("Limite de matrículas atingido");
        }

        return String.format("%06d", nextNumber);
    }
}
