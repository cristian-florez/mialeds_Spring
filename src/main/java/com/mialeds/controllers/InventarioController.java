package com.mialeds.controllers;

//importar la clase de spring framework para inyeccion de dependencias y controladores 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//importar la clase Model de spring framework para pasar datos a la vista
import com.mialeds.services.ProductoService;

//importar la clase Model de spring framework para pasar datos a la vista
import org.springframework.ui.Model;

//importar la clase Producto
import com.mialeds.models.Producto;


@Controller//anotacion para indicar que es un controlador esta clase
@RequestMapping("/inventario")//anotacion para indicar la ruta de acceso a este controlador
public class InventarioController {

    @Autowired
    private ProductoService productoService;

    private void productosEscasos(Model model) {
        model.addAttribute("productosEsc", productoService.productosEscasos());
    }

    @GetMapping("/listar")
    public String listar(Model model) {
        productosEscasos(model);
        model.addAttribute("productos", productoService.listar());
        return "inventario";
    }

    @GetMapping("/buscar")
    public String buscarProducto(@RequestParam("producto") String nombre, Model model) {
        if (nombre == null || nombre.isEmpty()) {
            return "redirect:/inventario/listar";  
        } else {
            model.addAttribute("productos", productoService.listarPorNombre(nombre));
            productosEscasos(model);
        }
        return "inventario";
    }


@PutMapping("/editar")
public String editarProducto(@RequestParam("id_editar") int id, 
                             @RequestParam("nombre_editar") String nombre,
                             @RequestParam("presentacion_editar") String presentacion,
                             @RequestParam("precio_compra_editar") int precioCompra,
                             @RequestParam("precio_venta_editar") int precioVenta,
                             @RequestParam("cantidad_editar") int cantidad) {

    productoService.actualizar(id, nombre, presentacion, precioCompra, precioVenta, cantidad);
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

            productoService.crear(nombre, presentacion, precioCompra, precioVenta, cantidad);
            return "redirect:/inventario/listar";
    }

    @DeleteMapping("/eliminar")
    public String eliminarProducto(@RequestParam("id_eliminar") int id, Model model) {
        productoService.eliminar(id);
        return "redirect:/inventario/listar";
    }

    @PutMapping("/movimiento")
    public String movimientoProducto(
            @RequestParam("id_movimiento") int id, 
            @RequestParam("cantidad_movimiento") int cantidad, 
            @RequestParam("movimiento") String movimiento,
            Model model) {

            productoService.movimiento(id, cantidad, movimiento);
            return "redirect:/inventario/listar";
    }

    
}
