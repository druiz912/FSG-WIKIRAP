package com.druiz.fullstack.wikirap.user.infrastructure.controller.dto;


import com.druiz.fullstack.wikirap.user.domain.UserEnt;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UserOutputDto {

    @NotEmpty
    private Integer id;

    @NotEmpty
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String role;

    private String accessToken;
    private String tokenType = "Bearer ";

    public UserOutputDto(String accessToken){
        this.accessToken = accessToken;
    }

    public UserOutputDto(UserEnt newUserEnt) {
        this.id = newUserEnt.getId();
        this.username = newUserEnt.getUsername();
        this.email = newUserEnt.getEmail();
    }
}
