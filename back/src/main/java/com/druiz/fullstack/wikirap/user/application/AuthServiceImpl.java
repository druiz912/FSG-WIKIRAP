package com.druiz.fullstack.wikirap.user.application;

import com.druiz.fullstack.wikirap.user.domain.Role;
import com.druiz.fullstack.wikirap.user.domain.User;
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
        User user = userRepo.findByEmailAndPassword(email, password);
        UserOutputDto dto = new UserOutputDto(user);
        return dto;
    }

    @Override
    public UserOutputDto findByUsername(String username) {
        User user = userRepo.findByUsername(username);
        UserOutputDto dto = new UserOutputDto(user);
        return dto;
    }

    @Override
    public User register(RegisterDto dto) {
        // Check 1: username
        if (userRepo.existsByUsername(dto.getUsername())) {
            throw new UnprocessableException("Error: Username is already taken!");
        }

        // Check 2: email
        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new UnprocessableException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(dto.getUsername(),
                dto.getEmail(),
                encoder.encode(dto.getPassword()));

        userRepo.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public User upgradeRole(String username, String role) {
        User userFind = userRepo.findByUsername(username);
        return userRepo.save(userFind);
    }

    @Override
    public UserOutputDto saveUser(RegisterDto user) {
        if (user == null) throw new UnprocessableException("User is null");
        // Condicional para añadir un rol según el correo
        User newUser = new User(user);
        Role roles;
        // Cuidado con el error de no añadir nada a la tabla ROLES
        if (newUser.getEmail().contains("daniel")) {
            roles = roleRepo.findByName("ADMIN").get();
        } else {
            roles = roleRepo.findByName("USER").get();
        }
        newUser.addRole(roles);
        userRepo.save(newUser);
        return new UserOutputDto(newUser);
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
