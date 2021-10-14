package com.service;

import com.dto.RoleDTO;
import com.entitymodels.RoleEntity;

import java.util.List;

public interface RoleService {

    RoleDTO findByName(String name);

    RoleEntity findEntityByName(String name);

    List<RoleDTO> findAllRoles();

    RoleDTO convertToRoleDTO(RoleEntity roleEntity);

    RoleEntity convertToRoleEntity(RoleDTO roleDTO);

    public void deleteById(Long id);
}
