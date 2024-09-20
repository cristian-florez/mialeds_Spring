//esta clase es un controlador que se encarga de manejar las peticiones del usuario
package com.mialeds.services;

import java.util.List;
//importamos domain sort para ordenar los datos de la base de datos en una lista
//importamos la anotacion service para indicar que es un servicio
import org.springframework.stereotype.Service;

//importamos la clase Producto del paquete models para poder usar sus metodos y atributos
import com.mialeds.models.Producto;

//importamos la clase ProductoRepository del paquete repositories, aunque con la anotacion autowired no es necesario pero es buena practica
import com.mialeds.repositories.ProductoRepository;

@Service
public class ProductoService {

    /*se crea una instancia de ProductoRepository para poder usar sus metodos,
    se usa final para que no se pueda modificar*/
    private final ProductoRepository productoRepository;

    //constructor de la clase ProductoService que recibe un ProductoRepository como parametro
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    //este metodo retorna una lista de productos con cantidad existente menor a 4
    public List<Producto> productosEscasos() {
        return productoRepository.findByCantidadExistenteMenorQue(4);
    }

    //este metodo retorna una lista de productos ordenados por nombre
    public List<Producto> listar() {
        /*se llama el metodo findAllByOrderByNombreAsc de ProductoRepository 
        para obtener la lista de productos ordenada por nombre*/
        return productoRepository.findAllByOrderByNombreAsc();
    }

    /*este metodo retorna una lista de productos que contengan el nombre indicado, 
    util para buscar productos y mostrarlos en la tabla de inventario*/
    public List<Producto> listarPorNombre(String nombre) {
        /*containing es una palabra clave de spring data jpa 
        para buscar por un atributo que contenga una cadena*/
        return productoRepository.findByNombreContaining(nombre);
    }

    //este metodo retorna un producto por su id, util para utilizar en otros metodos de la clase
    private Producto buscarPorId(int id) {
        //orElse(null) es para retornar null si no se encuentra el producto
        return productoRepository.findById(id).orElse(null);
    }

    /*este metodo guarda un producto en la base de datos, se utiliza
     en otros metodos de la clase para guardar o actualizar productos, 
     se decide crear el metodo para encapsular la logica de guardar un producto*/
    private Producto guardar(Producto producto) {
        Producto p = productoRepository.save(producto);
        return p;
    }

    //este metodo actualiza un producto por su id y retorna el producto actualizado
    public Producto actualizar(int id, String nombre, String presentacion, int precioCompra, int precioVenta, int cantidad) {
       //usamos el metodo buscarPorId de esta misma para obtener el producto por su id
        Producto p = this.buscarPorId(id);
        //campos del producto que se van a actualizar
        p.setNombre(nombre);
        p.setPresentacion(presentacion);
        p.setPrecioCompra(precioCompra);
        p.setPrecioVenta(precioVenta);
        p.setCantidadExistente(cantidad);

        //usamos el metodo guardar de esta misma clase para guardar el producto actualizado
        this.guardar(p);
        //retornamos el producto actualizado
        return p;
    }

    //este metodo crea un producto y lo guarda en la base de datos
    public Producto crear(String nombre, String presentacion, int precioCompra, int precioVenta, int cantidad) {
        //creamos una instancia de Producto usando el constructor que no necesita id
        Producto p = new Producto(nombre, presentacion, cantidad, precioCompra, precioVenta);
        this.guardar(p);
        return p;
    }

    //este metodo elimina un producto por su id
    public void eliminar(int id) {
        /*usamos el metodo buscarPorId de esta misma para obtener el producto por su id,
         lo hacemos asi y no directamente en el deleteById para evitar errores*/
        Producto p = this.buscarPorId(id);
        productoRepository.deleteById(p.getIdProducto());
    }

    //este metodo realiza un movimiento de entrada o salida de un producto por su id y cantidad
    public Producto movimiento(int id, int cantidad, String movimiento) {
        //usamos el metodo buscarPorId de esta misma para obtener el producto por su id
        Producto p = this.buscarPorId(id);
        //segun el movimiento se resta o se suma la cantidad al producto
        if (movimiento.equals("salida")) {
            p.setCantidadExistente(p.getCantidadExistente() - cantidad);
        } else if (movimiento.equals("entrada")) {
            p.setCantidadExistente(p.getCantidadExistente() + cantidad);
        }
        //usamos el metodo guardar de esta misma clase para guardar el producto actualizado
        this.guardar(p);
        return p;
    }
}
