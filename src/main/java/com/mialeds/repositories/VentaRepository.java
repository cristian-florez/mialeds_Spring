package com.mialeds.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mialeds.models.Venta;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

        List<Venta> findAllByOrderByFechaDesc();

}
