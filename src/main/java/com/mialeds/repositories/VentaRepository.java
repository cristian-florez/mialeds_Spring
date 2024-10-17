package com.mialeds.repositories;


import java.util.List;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mialeds.models.Producto;
import com.mialeds.models.Usuario;
import com.mialeds.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

        //metodo para buscar ventas por producto Y ordenarlas por fecha
        List<Venta> findAllByOrderByFechaDesc();

        /*metodo utilizado para que en el servicio verifique si la venta existe con los parametros que indicamos, ya que si es asi no queremos que nos guarde una nueva venta
        sino que actualice la existente */
        Venta findByProductoAndFechaAndUsuario(Producto Producto, LocalDate fecha,Usuario Usuario);

        //metodo utilizado cuando brindamos informacion en la vista del nombre del producto pero no la fecha
        List<Venta> findByProducto_NombreContaining(String nombre);

        //metodo utilizado cuando brindamos informacion en la vista de la fecha pero no el nombre del producto
        List<Venta> findByFecha(LocalDate fecha);

        //metodo utilizado cuando brindamos informacion en la vista del producto y la fecha
        List<Venta> findByProducto_NombreContainingAndFecha(String nombre, LocalDate fecha);        

}
