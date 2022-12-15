package com.druiz.fullstack.wikirap.utils.config.security;

import com.druiz.fullstack.wikirap.utils.config.jwt.JwtAuthEntryPoint;
import com.druiz.fullstack.wikirap.utils.config.jwt.JwtAuthFilter;
import com.druiz.fullstack.wikirap.utils.config.jwt.UserDetailsServiceCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Security {

    private JwtAuthEntryPoint authEntryPoint;
    private UserDetailsServiceCustom userDetailsService;
    @Autowired
    public Security(UserDetailsServiceCustom userDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

    /** Configuración de la seguridad */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .addFilterAfter(new JwtAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests(auth -> {
                    auth.antMatchers(HttpMethod.POST, "/person/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.POST, "/artist/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.POST, "/album/**").hasRole("ADMIN");
                    auth.antMatchers(HttpMethod.POST, "/song/**").hasRole("ADMIN");
                    //
                    auth.antMatchers(HttpMethod.GET, "/person/**").permitAll();
                    auth.antMatchers(HttpMethod.GET, "/artist/**").permitAll();
                    auth.antMatchers(HttpMethod.GET, "/album/**").permitAll();
                    auth.antMatchers(HttpMethod.GET, "/song/**").permitAll();
                    //
                    auth.antMatchers("/api/auth/**").permitAll();
                    auth.antMatchers("/swagger*/**").permitAll();
                    auth.anyRequest().authenticated();
                });

        return httpSecurity.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public  JwtAuthFilter jwtAuthenticationFilter() {
        return new JwtAuthFilter();
    }

}
