package com.mialeds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class PrincipalController {

    @GetMapping("/principal")
    public String principal(HttpServletRequest request, Model model) {
        Cookie[] cookies = request.getCookies();
        String token = null;
    
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("jwt_token".equals(cookie.getName())) {
                    token = cookie.getValue();
                    break;
                }
            }
        }
    
        if (token != null) {
            // El token está presente en la cookie, puedes usarlo si es necesario
            model.addAttribute("token", token);
            return "principal";  // Retorna la vista principal
        } else {
            return "redirect:/login";  // Redirige al login si no hay token
        }
    }
    
}
