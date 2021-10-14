package com.service.implementations;

import com.dto.RoleDTO;
import com.dto.UserDTO;
import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import com.repository.UserRepository;
import com.service.RoleService;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHibernateService implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserHibernateService(UserRepository userRepository, @Lazy RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }


    public UserDTO findUserById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.valueOf(id)));
        return convertToUserDTO(user);
    }

    public List<UserDTO> findAllUsers() {
        return ((List<UserEntity>) userRepository
                .findAll())
                .stream()
                .map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO convertToUserDTO(UserEntity user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress1(user.getAddress1());
        userDTO.setAddress2(user.getAddress2());
        userDTO.setCity(user.getCity());
        userDTO.setState(user.getState());
        userDTO.setZip(user.getZip());
        userDTO.setPhone(user.getPhone());
        userDTO.setRoles(user.getRoles()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toList()));

        return userDTO;
    }


    public UserEntity convertToUserEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
        user.setId(userDTO.getId());
        user.setFullName(userDTO.getFullName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress1(userDTO.getAddress1());
        user.setAddress2(userDTO.getAddress2());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        user.setCountry(userDTO.getCountry());
        user.setZip(userDTO.getZip());
        user.setPhone(userDTO.getPhone());
        user.setEnabled(userDTO.isEnabled());
        user.setTokenExpired(userDTO.isTokenExpired());
        user.setRoles(userDTO.getRoles()
                .stream()
                .map(roles -> roleService.findEntityByName(roles))
                .collect(Collectors.toList()));
        return user;

    }


    public UserDTO save(UserDTO userDTO) {
        UserEntity user = convertToUserEntity(userDTO);
        UserEntity savedUser = userRepository.save(user);
        UserDTO savedDTO = convertToUserDTO(savedUser);

        return savedDTO;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
