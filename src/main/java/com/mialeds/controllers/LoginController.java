package com.mialeds.controllers; // Paquete que contiene los controladores de la aplicación


import org.springframework.stereotype.Controller; // Importamos la anotación que define esta clase como un controlador
import org.springframework.web.bind.annotation.GetMapping; // Importamos la anotación para manejar solicitudes GET


// Esta clase es responsable de manejar las solicitudes relacionadas con el inicio de sesión 
@Controller
public class LoginController {

    // Método que maneja la solicitud GET para la página de inicio de sesión
    @GetMapping("/login")
    public String index() {
        return "index"; // Retorna el nombre de la vista que se mostrará (index.html)
    }
}
