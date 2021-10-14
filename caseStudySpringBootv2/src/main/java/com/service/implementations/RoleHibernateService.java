package com.service.implementations;

import com.dto.RoleDTO;
import com.dto.UserDTO;
import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import com.repository.RoleRepository;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleHibernateService implements RoleService {

    private final RoleRepository roleRepository;
    private final UserService userService;

    @Autowired
    public RoleHibernateService(RoleRepository roleRepository, UserService userService){
        this.roleRepository = roleRepository;
        this.userService = userService;
    }

    public RoleDTO findByName(String name){
        RoleEntity role = roleRepository.findByName(name);
        return convertToRoleDTO(role);
    }

    public RoleEntity findEntityByName(String name){
        RoleEntity role = roleRepository.findByName(name);
        return role;
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
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());

        roleDTO.setUsers(roleEntity.getUsers()
                .stream()
                .map(user -> userService.convertToUserDTO(user))
                .collect(Collectors.toList()));


        return roleDTO;
    }

    public RoleEntity convertToRoleEntity(RoleDTO roleDTO){
        RoleEntity role = new RoleEntity();
        role.setId(roleDTO.getId());
        role.setName(roleDTO.getName());
        role.setUsers(roleDTO.getUsers()
                .stream()
                .map(user -> userService.convertToUserEntity(user))
                .collect(Collectors.toList()));

        return role;
    }


    public void deleteById(Long id){
        roleRepository.deleteById(id);
    }

}

