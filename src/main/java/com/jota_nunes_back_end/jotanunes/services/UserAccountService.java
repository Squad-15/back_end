package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.dtos.UserAccountDto;
import com.jota_nunes_back_end.jotanunes.dtos.UserAccountWithPasswordDto;
import com.jota_nunes_back_end.jotanunes.models.Profile;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.ProfileRepository;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordService passwordService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserAccountWithPasswordDto createUser(UserAccountDto userAccountDto) {
        UserAccount user = new UserAccount();
        user.setFirstName(userAccountDto.firstName);
        user.setLastName(userAccountDto.lastName);
        user.setEmail(userAccountDto.email);
        user.setPhone(userAccountDto.phone);
        user.setRoleUser(userAccountDto.roleUser);
        user.setUrlPicture(userAccountDto.urlPicture);

        // Gerar número de matrícula
        String nextRegisterNumber = generateNextRegisterNumber();
        user.setNumberRegister(nextRegisterNumber);

        // Gerar senha em texto puro
        String rawPassword = passwordService.generateRandomPassword(6);
        System.out.println("Raw password generated: " + rawPassword);

        // Criptografar a senha e salvar
        String encodedPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encodedPassword);

        // Criar e salvar o perfil
        Profile profile = new Profile();
        profile.setProfileName(userAccountDto.cargo);
        profile.setDepartment(userAccountDto.departamento);
        profile.setTypeConnection(userAccountDto.typeconnection);
        profile.setDateAdmission(userAccountDto.dataAdmissao);
        profile.setLocation(userAccountDto.location);

        Profile savedProfile = profileRepository.save(profile);
        user.setProfile(savedProfile);

        // Salvar usuário
        UserAccount savedUser = userAccountRepository.save(user);

        // Retornar DTO com senha em texto puro
        return new UserAccountWithPasswordDto(savedUser, rawPassword);
    }


    public List<UserAccount> getAllUser() {
       return userAccountRepository.findAll();
    }

    public Optional<UserAccount> getUserById(long id) {
        return userAccountRepository.findById(id);
    }

    public UserAccount updateUser(Long id, UserAccountDto userAccountDto) {
        return userAccountRepository.findById(id)
                .map(user -> {
                    user.setFirstName(userAccountDto.firstName);
                    user.setLastName(userAccountDto.lastName);
                    user.setEmail(userAccountDto.email);
                    user.setPhone(userAccountDto.phone);
                    user.setRoleUser(userAccountDto.roleUser);

                    Profile profile = user.getProfile();
                    if (profile != null) {
                        profile.setProfileName(userAccountDto.cargo);
                        profile.setDepartment(userAccountDto.departamento);
                        profile.setTypeConnection(userAccountDto.typeconnection);
                        profile.setDateAdmission(userAccountDto.dataAdmissao);
                        profile.setLocation(userAccountDto.location);
                        profileRepository.save(profile);
                    }

                    return userAccountRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void deleteUser(Long id) {
        userAccountRepository.deleteById(id);
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