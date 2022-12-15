package com.druiz.fullstack.wikirap.user.application;

import com.druiz.fullstack.wikirap.user.domain.UserEnt;
import com.druiz.fullstack.wikirap.user.infrastructure.controller.dto.RegisterDto;
import com.druiz.fullstack.wikirap.user.infrastructure.controller.dto.UserOutputDto;

import java.util.List;

public interface UserService {

    UserOutputDto findByEmailAndPassword(String email, String password);

    UserOutputDto findByUsername(String username);

    UserEnt upgradeRole(String username, String role);

    UserOutputDto saveUser(RegisterDto user);

    List<UserOutputDto> getAllUsers();
}
