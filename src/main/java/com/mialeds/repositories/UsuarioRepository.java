package com.mialeds.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mialeds.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Integer> {

    Optional<Usuario> findByCedula(String cedula);
}
