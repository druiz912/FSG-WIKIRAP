package com.druiz.fullstack.wikirap.user.infrastructure.controller.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterDto {

    @NotEmpty
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
