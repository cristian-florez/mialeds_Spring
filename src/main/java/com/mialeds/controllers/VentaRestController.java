package com.mialeds.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mialeds.services.ProductoService;
import java.util.List;

//clase para generar una api para listar los productos en la vista de ventas
@RestController
@RequestMapping("/api/venta")
public class VentaRestController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/listar")
    public List<Object[]> listarProductos() {
        return productoService.listarNombrePresentacion();
    }
}
