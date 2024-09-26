package com.mialeds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mialeds.models.Rol;
import com.mialeds.models.Usuario;
import com.mialeds.repositories.RolRepository;
import com.mialeds.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
	private BCryptPasswordEncoder passwordEncoder;

    private final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

    public List<Usuario> listar() {
        try {
            return usuarioRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al listar los usuarios: " + e.getMessage());
            return null;
        }
    }

    public Usuario buscarPorId(int id) {
        try {
            return usuarioRepository.findById(id).orElse(null);
        } catch (Exception e) {
            logger.error("Error al buscar el usuario: " + e.getMessage());
            return null;
        }
    }

    public void insertar(Usuario usuario) {
        try {
            Usuario usuarioObjeto = usuario;
            usuarioObjeto.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            
            Rol rol = new Rol();

            rol.setNombreRol("ROLE_USER");
            rol = rolRepository.save(rol);

            usuarioObjeto.getRoles().add(rol);
            usuarioObjeto = usuarioRepository.save(usuarioObjeto);
        } catch (Exception e) {
            logger.error("Error al insertar el usuario: " + e.getMessage());
        }
    }
    

}
