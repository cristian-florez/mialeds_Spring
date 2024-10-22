package com.mialeds.controllers; // Paquete que contiene los controladores de la aplicación


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller; // Importamos la anotación que define esta clase como un controlador
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; // Importamos la anotación para manejar solicitudes GET
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mialeds.services.EmailService;


// Esta clase es responsable de manejar las solicitudes relacionadas con el inicio de sesión 
@Controller
public class LoginController {

    @Autowired
    private EmailService emailService;

    // Método que maneja la solicitud GET para la página de inicio de sesión
    @GetMapping("/login")
    public String index() {
        return "index"; // Retorna el nombre de la vista que se mostrará (index.html)
    }

    //metodo para enviar un correo electronico
    @PostMapping("/restaurarClave")
    public String restaurarClave(
        @RequestParam("cedula_olvide_clave") String cedula,
        @RequestParam("correo_olvide_clave") String correo,
        Model model) {
        try {
            emailService.enviarCorreo(cedula, correo);
            return "redirect:/login";
        } catch (Exception e) {
            return "redirect:/login";
        }
    }
}
