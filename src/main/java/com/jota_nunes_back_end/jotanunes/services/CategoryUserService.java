//package com.jota_nunes_back_end.jotanunes.services;
//
//import com.jota_nunes_back_end.jotanunes.repositories.CategoryUserRepository;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class CategoryUserService {
//    @Autowired
//    private  CategoryUserRepository categoryUserRepository;
//
//    public CategoryUserService(CategoryUserRepository categoryUserRepository) {
//        this.categoryUserRepository = categoryUserRepository;
//    }
//
//    @Transactional
//    public CategoriaUsuario associateUserToCategory(CategoriaUsuario categoriaUsuario) {
//        return categoryUserRepository.save(categoriaUsuario);
//    }
//
//    @Transactional
//    public void deleteAssociation(Long userAccountCategoryId) {
//        categoryUserRepository.deleteById(userAccountCategoryId);
//    }
//
//    @Transactional
//    public List<CategoriaUsuario> listPerUser(Long userId) {
//        return categoryUserRepository.findByUserAccount_Id(userId);
//    }
//}

package com.jota_nunes_back_end.jotanunes.services;

import com.jota_nunes_back_end.jotanunes.models.Categorias;
import com.jota_nunes_back_end.jotanunes.models.UserAccount;
import com.jota_nunes_back_end.jotanunes.repositories.CategoryRepository;
import com.jota_nunes_back_end.jotanunes.repositories.UserAccountRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryUserService {
    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public boolean associateCategoryToUser(Long userId, Integer categoriaId) {
        Optional<UserAccount> userOpt = userAccountRepository.findById(userId);
        Optional<Categorias> categoriaOpt = categoryRepository.findById(Long.valueOf(categoriaId));

        if (userOpt.isPresent() && categoriaOpt.isPresent()) {
            UserAccount user = userOpt.get();
            user.setCategoria(categoriaOpt.get());
            userAccountRepository.save(user);
            return true;
        }

        return false;
    }

    public Optional<Categorias> getCategoriaDoUsuario(Long userId) {
        return userAccountRepository.findById(userId)
                .map(UserAccount::getCategoria);
    }

    @Transactional
    public boolean removerCategoriaDoUsuario(Long userId) {
        Optional<UserAccount> userOpt = userAccountRepository.findById(userId);

        if (userOpt.isPresent()) {
            UserAccount user = userOpt.get();
            user.setCategoria(null);
            userAccountRepository.save(user);
            return true;
        }

        return false;
    }

}
