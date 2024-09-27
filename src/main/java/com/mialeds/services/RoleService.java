package com.mialeds.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mialeds.models.PermisosRoles;
import com.mialeds.models.Role;
import com.mialeds.repositories.PermisosRolesRepository;
import com.mialeds.repositories.RoleRepository;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermisosRolesRepository permisosRolesRepository;

    private final Logger logger = LoggerFactory.getLogger(RoleService.class);


    public List<Role> listarRoles() {
        try {
            return roleRepository.findAll();
        } catch (Exception e) {
            logger.error("Error al listar los roles: " + e.getMessage());
            return null;
        }
    }

    public List<PermisosRoles> listarPermisos() {
        return permisosRolesRepository.findAll();
    }
}
