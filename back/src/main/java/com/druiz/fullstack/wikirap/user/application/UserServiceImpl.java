package com.druiz.fullstack.wikirap.user.application;

import com.druiz.fullstack.wikirap.user.domain.Role;
import com.druiz.fullstack.wikirap.user.domain.UserEnt;
import com.druiz.fullstack.wikirap.user.infrastructure.controller.dto.RegisterDto;
import com.druiz.fullstack.wikirap.user.infrastructure.controller.dto.UserOutputDto;
import com.druiz.fullstack.wikirap.user.infrastructure.repo.RoleRepo;
import com.druiz.fullstack.wikirap.user.infrastructure.repo.UserRepo;
import com.druiz.fullstack.wikirap.utils.exceptions.UnprocessableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserOutputDto findByEmailAndPassword(String email, String password) {
        UserEnt userEnt = userRepo.findByEmailAndPassword(email, password);
        UserOutputDto dto = new UserOutputDto(userEnt);
        return dto;
    }

    @Override
    public UserOutputDto findByUsername(String username) {
        UserEnt userEnt = userRepo.findByUsername(username);
        UserOutputDto dto = new UserOutputDto(userEnt);
        return dto;
    }

    @Override
    public UserEnt upgradeRole(String username, String role) {
        UserEnt userEntFind = userRepo.findByUsername(username);
        return userRepo.save(userEntFind);
    }

    @Override
    public UserOutputDto saveUser(RegisterDto user) {
        if (user == null) throw new UnprocessableException("User is null");
        // Condicional para añadir un rol según el correo
        UserEnt newUserEnt = new UserEnt(user);
        Role roles;
        if (newUserEnt.getEmail().contains("test")) {
            roles = roleRepo.findByName("ADMIN").get();
        } else {
            roles = roleRepo.findByName("USER").get();
        }
        newUserEnt.addRole(roles);
        userRepo.save(newUserEnt);
        return new UserOutputDto(newUserEnt);
    }

    @Override
    public List<UserOutputDto> getAllUsers() {
        List<UserOutputDto> outputDtoList = new ArrayList<>();
        var users = userRepo.findAll();
        users.forEach(user -> {
            // Instanciamos cada entidad a output
            UserOutputDto dto = new UserOutputDto(user);
            outputDtoList.add(dto);
        });
        return outputDtoList;
    }
}
