package com.mialeds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mialeds.services.VentaService;

import org.springframework.ui.Model;



@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @GetMapping("/listar")
    public String listar(Model model) {
        try {
            model.addAttribute("ventas", ventaService.listar());
        } catch (Exception e) {
            model.addAttribute("error", "error a listar las ventas: " + e.getMessage());
        }
        return "venta";
    }
    
 }
    


