package com.druiz.fullstack.wikirap.utils.config.security;

import com.druiz.fullstack.wikirap.utils.config.jwt.JwtAuthEntryPoint;
import com.druiz.fullstack.wikirap.utils.config.jwt.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Security {

    /**
     *
     */

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    // ================ CREACIÓN DE BEANS ======================
    @Bean
    public JwtRequestFilter authenticationJwtTokenFilter() {
        return new JwtRequestFilter();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /** Configuración de la seguridad */
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                //
                .csrf(csrf -> csrf.disable())
                //
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                //
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                //
                .authorizeRequests(auth -> {
                    auth.antMatchers("/artist").hasRole("USER");
                    auth.antMatchers("/album").hasRole("USER");
                    auth.antMatchers("/song").hasRole("USER");
                    auth.antMatchers("/person").hasRole("ADMIN");
                    auth.antMatchers("/api/auth/**").permitAll();
                    auth.antMatchers("/swagger*/**", "/webjars/**").permitAll();
                    auth.anyRequest().authenticated();
                })
                //
                .httpBasic(Customizer.withDefaults())
                //
                .addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    /**
     * Configuracion global de CORS para toda la aplicacion

    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {
        CorsConfiguration configuration = new CorsConfiguration();
        // configuration.setAllowedOrigins(List.of("http://localhost:4200", "https://angular-springboot-*.vercel.app"));
        configuration.setAllowedOriginPatterns(List.of("http://localhost:4200", "https://angular-springboot1-beta.vercel.app"));
        configuration.setAllowedMethods(List.of("GET", "POST", "OPTIONS", "DELETE", "PUT", "PATCH"));
        configuration.setAllowedHeaders(List.of("Access-Control-Allow-Origin", "X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
     */
}
