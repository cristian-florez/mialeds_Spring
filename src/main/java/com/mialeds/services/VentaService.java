package com.mialeds.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mialeds.models.Venta;
import com.mialeds.repositories.VentaRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    private final Logger logger = LoggerFactory.getLogger(VentaService.class);

    public List<Venta> listar() {
        try {
            return ventaRepository.findAllByOrderByFechaDesc();
        } catch (Exception e) {
            logger.error("Error al listar las ventas: " + e.getMessage());
            return null;
        }
    }
}
