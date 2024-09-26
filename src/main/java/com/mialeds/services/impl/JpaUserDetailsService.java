package com.mialeds.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mialeds.models.Rol;
import com.mialeds.models.Usuario;
import com.mialeds.repositories.UsuarioRepository;
import java.util.*;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCedula(username);
        List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        for (Rol rol : usuario.getRoles()) {
            roles.add(new SimpleGrantedAuthority(rol.getNombreRol()));
        }


        return new User(usuario.getCedula(), usuario.getContrasena(), usuario.getEstado(), true, true, true, roles);
    }

}
