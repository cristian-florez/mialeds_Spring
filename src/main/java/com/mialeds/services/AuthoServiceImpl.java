package com.mialeds.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.mialeds.config.JwtService;
import com.mialeds.controllers.models.AuthResponse;
import com.mialeds.controllers.models.AuthenticationRequest;
import com.mialeds.controllers.models.RegisterRequest;
import com.mialeds.models.Role;
import com.mialeds.models.Usuario;
import com.mialeds.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthoServiceImpl implements AuthoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponse registrer(RegisterRequest request) {
        var usuario = Usuario.builder()
                .nombre(request.getNombre())
                .cedula(request.getCedula())
                .contrasena(passwordEncoder.encode(request.getContrasena()))
                .correoElectronico(request.getCorreoElectronico())
                .telefono(request.getTelefono())
                .role(Role.ADMIN)
                .build();
        usuarioRepository.save(usuario);
        var jwtToken = jwtService.generateToken(usuario);
        return AuthResponse.builder().token(jwtToken).build();
    }

    @Override
    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getCedula(), 
                request.getContrasena()
            )
        );
        var usuario = usuarioRepository.findByCedula(request.getCedula())
            .orElseThrow(() -> new RuntimeException("User not found"));
        var jwtToken = jwtService.generateToken(usuario);
        return AuthResponse.builder().token(jwtToken).build();
    }

}
