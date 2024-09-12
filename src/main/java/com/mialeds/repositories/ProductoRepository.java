package com.mialeds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.mialeds.models.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p FROM Producto p WHERE p.cantidadExistente < :cantidad")
    List<Producto> findByCantidadExistenteMenorQue(@Param("cantidad") int cantidad);

    List<Producto> findByNombreContaining(String nombre);

    List<Producto> findAllByOrderByNombreAsc();
}
