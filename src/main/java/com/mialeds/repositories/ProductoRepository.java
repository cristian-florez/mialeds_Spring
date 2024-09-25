//esta clase es un repositorio de datos que se encarga de interactuar con la base de datos
package com.mialeds.repositories;

//esta clase importa jpa repository para extender de esta clase y heredar metodos basicos
import org.springframework.data.jpa.repository.JpaRepository;
//esta clase importa query para poder hacer consultas personalizadas
import org.springframework.data.jpa.repository.Query;
//esta clase importa param para poder pasar parametros a las consultas personalizadas
import org.springframework.data.repository.query.Param;
//esta clase importa repository para indicar que es un repositorio de datos
import org.springframework.stereotype.Repository;
import java.util.List;

import com.mialeds.models.Producto;

/*al extender de JpaRepository se heredan los metodos basicos para interactuar con la base de datos
se debe indicar el tipo de entidad y el tipo de dato del id de la entidad*/
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    /* este metodo es un query personalizado para buscar productos con cantidad 
    existente menor a la cantidad indicada */
    @Query("SELECT p FROM Producto p WHERE p.cantidadExistente < :cantidad")
    List<Producto> findByCantidadExistenteMenorQue(@Param("cantidad") int cantidad);

    //metodo para buscar productos por nombre del producto
    List<Producto> findByNombreContaining(String nombre);

    /*metodo para buscar todos los productos ordenados por nombre, Order By 
    es una palabra clave de SQL para ordenar los datos por un atributo y en que direccion*/
    List<Producto> findAllByOrderByNombreAsc();

    /*metodo para buscar productos por nombre y presentacion utilizado para el AJAX de la vista 
    ventas para buscar productos para registrar las ventas*/
    @Query("SELECT p.nombre, p.presentacion, p.precioVenta FROM Producto p")
    List<Object[]> findNombreAndPresentacion();
}
