package com.mialeds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {


    @GetMapping("/principal")
    public String principal() {
        return "principal";
    }

    @GetMapping("/inventario")
    public String inventario() {
        return "inventario";
    }

    @GetMapping("/ventas")
    public String ventas() {
        return "ventas";
    }

    @GetMapping("/proveedores")
    public String proveedores() {
        return "proveedores";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
