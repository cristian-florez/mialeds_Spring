package com.mialeds.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort; // Add this import statement
import org.springframework.stereotype.Service;

import com.mialeds.models.Producto;

import com.mialeds.repositories.ProductoRepository;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> productosEscasos() {
        return productoRepository.findByCantidadExistenteMenorQue(4);
    }

    public List<Producto> listar() {
        return productoRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public List<Producto> listarPorNombre(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }

    public Producto buscarPorId(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    public Producto guardar(Producto producto) {
        Producto p = productoRepository.save(producto);
        return p;
    }

    public Producto actualizar(int id, String nombre, String presentacion, int precioCompra, int precioVenta, int cantidad) {
        Producto p = this.buscarPorId(id);
        p.setNombre(nombre);
        p.setPresentacion(presentacion);
        p.setPrecioCompra(precioCompra);
        p.setPrecioVenta(precioVenta);
        p.setCantidadExistente(cantidad);

        this.guardar(p);
        return p;
    }

    public Producto crear(String nombre, String presentacion, int precioCompra, int precioVenta, int cantidad) {
        Producto p = new Producto();
        p.setNombre(nombre);
        p.setPresentacion(presentacion);
        p.setPrecioCompra(precioCompra);
        p.setPrecioVenta(precioVenta);
        p.setCantidadExistente(cantidad);
        
        this.guardar(p);
        return p;
    }

    public void eliminar(int id) {
        productoRepository.deleteById(id);
    }

    public Producto movimiento(int id, int cantidad, String movimiento) {
        Producto p = this.buscarPorId(id);
        if (movimiento.equals("salida")) {
            p.setCantidadExistente(p.getCantidadExistente() - cantidad);
        } else if (movimiento.equals("entrada")) {
            p.setCantidadExistente(p.getCantidadExistente() + cantidad);
        }
        this.guardar(p);
        return p;
    }
}
