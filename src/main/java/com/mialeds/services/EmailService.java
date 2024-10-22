package com.mialeds.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.mialeds.models.Usuario;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private UsuarioService usuarioService;

    private final Logger logger = LoggerFactory.getLogger(EmailService.class);

    public boolean enviarCorreo(String cedula, String destinatario) {

        try {
            SimpleMailMessage mensaje = new SimpleMailMessage();
            Usuario usuario = usuarioService.buscarUsuarioAutenticacion(cedula);
    
            if(usuario == null || !usuario.getCorreoElectronico().equals(destinatario)) {
                return false;
            }

            String claveNueva = usuarioService.generarContraseña();
            usuarioService.cambiarContrasena(cedula, claveNueva);
    
            mensaje.setFrom("mialeds06@gmail.com");
            mensaje.setTo(destinatario);
            mensaje.setSubject("Cambiar contraseña");
            mensaje.setText("Estimado usuario,\n\n" +
                        "Hemos recibido una solicitud para cambiar su contraseña.\n" +
                        "Por favor, recuerde cambiar su contraseña después de iniciar sesión.\n\n" +
                        "Para acceder a su cuenta, use la siguiente contraseña temporal:\n" +
                        "Contraseña temporal: " + claveNueva + "\n\n" +
                        "Una vez que haya iniciado sesión, le recomendamos que cambie su contraseña a una más segura.\n\n" +
                        "El equipo de soporte de MIALEDS");
    
            mailSender.send(mensaje);
        } catch (Exception e) {
            logger.error("Error al enviar el correo: " + e.getMessage());
        }

        return true;

    }
}
