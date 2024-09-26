package com.mialeds.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {




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
}
