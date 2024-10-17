package com.mialeds.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mialeds.models.Usuario;
import com.mialeds.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    // Este método crea un usuario en la base de datos, encriptando la contraseña
    public Usuario crearUsuario(Usuario usuario) {
        try {
            usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));
            return usuarioRepository.save(usuario);
        } catch (Exception e) {
            logger.error("Error al crear el usuario: " + e.getMessage());
            return null;
        }
    }

    // Este método retorna un usuario por su cédula, utilizado en Spring Security para la autenticación
    public Optional<Usuario> buscarPorCedula(String cedula) {
        try {
            return usuarioRepository.findByCedula(cedula);
        } catch (Exception e) {
            logger.error("Error al buscar el usuario: " + e.getMessage());
            return null;
        }
    }

    // Este método retorna el id de un usuario por su cédula, utilizado en ventas
    public Integer obtenerIdPorCedula(String cedula) {
        return usuarioRepository.findIdByCedula(cedula)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con cédula: " + cedula));
    }

}
