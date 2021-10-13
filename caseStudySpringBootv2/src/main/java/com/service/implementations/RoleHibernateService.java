package com.service.implementations;

import com.dto.RoleDTO;
import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import com.repository.RoleRepository;
import com.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleHibernateService implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleHibernateService(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    public RoleDTO findByName(String name){
        RoleEntity role = roleRepository.findByName(name);
        return convertToRoleDTO(role);
    }

    public List<RoleDTO> findAllRoles(){
        return ((List<RoleEntity>) roleRepository
                .findAll())
                .stream()
                .map(this::convertToRoleDTO)
                .collect(Collectors.toList());
    }

    public RoleDTO convertToRoleDTO(RoleEntity roleEntity){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setName(roleEntity.getName());
        roleDTO.setUsers(roleEntity.getUsers());

        return roleDTO;
    }

    public void deleteById(Long id){
        roleRepository.deleteById(id);
    }

}

