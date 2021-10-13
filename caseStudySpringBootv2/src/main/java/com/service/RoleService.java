package com.service;

import com.dto.RoleDTO;
import com.entitymodels.RoleEntity;

import java.util.List;

public interface RoleService {

    public RoleDTO findByName(String name);

    public List<RoleDTO> findAllRoles();

    public RoleDTO convertToRoleDTO(RoleEntity roleEntity);

    public void deleteById(Long id);
}
