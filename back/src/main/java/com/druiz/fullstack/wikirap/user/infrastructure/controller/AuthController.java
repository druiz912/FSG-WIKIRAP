package com.druiz.fullstack.wikirap.user.infrastructure.controller;

import com.druiz.fullstack.wikirap.user.application.UserService;
import com.druiz.fullstack.wikirap.user.domain.UserEnt;
import com.druiz.fullstack.wikirap.user.infrastructure.controller.dto.LoginDto;
import com.druiz.fullstack.wikirap.user.infrastructure.repo.UserRepo;
import com.druiz.fullstack.wikirap.utils.config.jwt.JwtGenerator;
import com.druiz.fullstack.wikirap.user.infrastructure.controller.dto.RegisterDto;
import com.druiz.fullstack.wikirap.user.infrastructure.controller.dto.UserOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador para llevar a cabo la autenticación utilizando JWT
 *
 * Se utiliza AuthenticationManager para autenticar las credenciales que son el
 * usuario y password que llegan por POST en el cuerpo de la petición
 *
 * Si las credenciales son válidas se genera un token JWT como respuesta
 */
// @CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("api/auth")

public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserRepo userRepository;
    @Autowired
    private JwtGenerator jwtGenerator;
    @Autowired
    private UserService userService;


    @PostMapping("login")
    public ResponseEntity<UserOutputDto> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new UserOutputDto(token), HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(registerDto);

        return new ResponseEntity<>("Welcome! " + registerDto.getUsername(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserOutputDto>> getAllUsers() {
        var x = userService.getAllUsers();
        return new ResponseEntity<>(x, HttpStatus.OK);
    }
}