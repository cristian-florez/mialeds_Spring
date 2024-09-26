package com.mialeds.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private LoginSucessHandler loginSucessHandler;

    // Definimos el codificador de contraseñas como un bean
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Definimos el DaoAuthenticationProvider para evitar referencia circular
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Usamos el UserDetailsService
        authProvider.setPasswordEncoder(passwordEncoder()); // Aplicamos el codificador de contraseñas
        return authProvider;
    }

    // Configuramos la cadena de filtros de seguridad
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/", "/css/**", "/js/**", "/img/**", "/users/**").permitAll() // Las rutas públicas
                .requestMatchers("/principal").hasAnyRole("ADMIN", "USER") // Protegemos "/principal" con roles específicos
                .anyRequest().authenticated() // Cualquier otra petición requiere autenticación
            )
            .formLogin((form) -> form
                .loginPage("/index") // Página de inicio de sesión
                .loginProcessingUrl("/index") // URL de procesamiento del formulario de login
                .successHandler(loginSucessHandler) // Manejador de éxito
                .defaultSuccessUrl("/principal") // Redirigir después de login exitoso
                .permitAll()
            )
            .logout((logout) -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // URL para logout
                .logoutSuccessUrl("/index") // Redirigir tras logout
                .permitAll()
            )
            .exceptionHandling((exceptions) -> exceptions
                .accessDeniedPage("/error_403") // Página de error en caso de acceso denegado
            );

        return http.build();
    }

    // Configuramos el proveedor de autenticación
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider()); // Utilizamos el DaoAuthenticationProvider
    }
}
