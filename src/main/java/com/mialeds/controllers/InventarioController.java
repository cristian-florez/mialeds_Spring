package com.mialeds.controllers;

//importar la clase de spring framework para inyeccion de dependencias y controladores 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mialeds.services.KardexService;
import java.time.LocalDate;
//importar la clase Model de spring framework para pasar datos a la vista
import com.mialeds.services.ProductoService;

//importar la clase Model de spring framework para pasar datos a la vista
import org.springframework.ui.Model;


@Controller//anotacion para indicar que es un controlador esta clase
@RequestMapping("/inventario")//anotacion para indicar la ruta de acceso a este controlador
public class InventarioController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private KardexService kardexService;

    private void productosEscasos(Model model) {
        model.addAttribute("productosEsc", productoService.productosEscasos());
    }

    private void kardexE(Model model) {
        model.addAttribute("kardexE", kardexService.listarPorMovimiento("entrada"));
        model.addAttribute("kardexS", kardexService.listarPorMovimiento("salida"));
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        try {
            productosEscasos(model);
            kardexE(model);
            
            model.addAttribute("productos", productoService.listar());
        } catch (Exception e) {
            model.addAttribute("error", "error a listar los productos: " + e.getMessage());
        }
        return "inventario";
    }

    @GetMapping("/buscar")
    public String buscarProducto(@RequestParam("producto") String nombre, Model model) {
        try {
            if (nombre == null || nombre.isEmpty()) {
                return "redirect:/inventario/listar";  
            } else {
                model.addAttribute("productos", productoService.listarPorNombre(nombre));
                productosEscasos(model);
            }
        } catch (Exception e) {
            model.addAttribute("error", "error al buscar el producto: " + e.getMessage());
        }

        return "inventario";
    }


    @PutMapping("/editar")
    public String editarProducto(@RequestParam("id_editar") int id, 
                                @RequestParam("nombre_editar") String nombre,
                                @RequestParam("presentacion_editar") String presentacion,
                                @RequestParam("precio_compra_editar") int precioCompra,
                                @RequestParam("precio_venta_editar") int precioVenta,
                                @RequestParam("cantidad_editar") int cantidad,
                                Model model) {
        try {
            productoService.actualizar(id, nombre, presentacion, precioCompra, precioVenta, cantidad);
        } catch (Exception e) {
            model.addAttribute("error", "error al editar el producto: " + e.getMessage());
        }
        return "redirect:/inventario/listar";
    }

    
    @PostMapping("/nuevo")
    public String crearProducto(
            @RequestParam("nombre_producto_nuevo") String nombre, 
            @RequestParam("presentacion_nuevo") String presentacion, 
            @RequestParam("precio_compra_nuevo") int precioCompra, 
            @RequestParam("precio_venta_nuevo") int precioVenta,
            @RequestParam("cantidad_nuevo") int cantidad,
            Model model) {
        try {
            productoService.crear(nombre, presentacion, precioCompra, precioVenta, cantidad);
        } catch (Exception e) {
            model.addAttribute("error", "error al crear el producto: " + e.getMessage());
        }
        return "redirect:/inventario/listar";
    }

    @DeleteMapping("/eliminar")
    public String eliminarProducto(@RequestParam("id_eliminar") int id, Model model) {
        try {
            productoService.eliminar(id);
        } catch (Exception e) {
            model.addAttribute("error", "error al eliminar el producto: " + e.getMessage());
        }
        return "redirect:/inventario/listar";
    }

    @PostMapping("/movimiento")
    public String movimientoProducto(
            @RequestParam("id_movimiento") int id, 
            @RequestParam("cantidad_movimiento") int cantidad, 
            @RequestParam("movimiento") String movimiento,
            @RequestParam("fecha_movimiento") String fecha,
            Model model) {
        try {
            productoService.movimiento(id, cantidad, movimiento);
            kardexService.crear(id, 2, movimiento,LocalDate.parse(fecha), cantidad);
        } catch (Exception e) {
            model.addAttribute("error", "error al hacer el movimiento del producto: " + e.getMessage());
        }
        return "redirect:/inventario/listar";
    }

}
