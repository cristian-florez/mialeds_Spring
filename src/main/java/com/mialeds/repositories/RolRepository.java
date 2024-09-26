package com.mialeds.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.mialeds.models.Rol; 

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

}
