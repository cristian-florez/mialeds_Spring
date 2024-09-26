package com.mialeds.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller; // Cambiar a Controller
import org.springframework.ui.Model; // Importar Model
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mialeds.controllers.models.RegisterRequest;
import com.mialeds.services.AuthoService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.mialeds.controllers.models.AuthResponse;
import com.mialeds.controllers.models.AuthenticationRequest;

import lombok.RequiredArgsConstructor;

@Controller // Cambiar a Controller para manejar vistas
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthoService authoService;

    @PostMapping("/registrer")
    public ResponseEntity<AuthResponse> registrer(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authoService.registrer(request));
    }

    @PostMapping("/authenticate")
    public String authenticate(@ModelAttribute AuthenticationRequest request, Model model, HttpSession session, HttpServletResponse response) {
        AuthResponse authResponse = authoService.authenticate(request);
    
        if (authResponse != null) {
            // Crea una cookie con el token
            Cookie jwtCookie = new Cookie("jwt_token", authResponse.getToken());
            jwtCookie.setHttpOnly(true);  // Para evitar que sea accesible vía JS
            jwtCookie.setMaxAge(60 * 60);  // Configura la expiración de la cookie
            response.addCookie(jwtCookie);  // Añade la cookie a la respuesta
    
            return "redirect:/principal";  // Redirige a la página principal
        } else {
            model.addAttribute("error", "Credenciales inválidas");
            return "login";  // Vuelve a la página de login si hay error
        }
    }
}
