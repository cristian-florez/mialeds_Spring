package com.mialeds.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mialeds.models.Role;
import com.mialeds.models.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository <Role, Integer> {
    
    Role findByRoleEnum(RoleEnum roleEnum);

}
