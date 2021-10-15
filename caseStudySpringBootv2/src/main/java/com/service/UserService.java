package com.service;

import com.dto.RoleDTO;
import com.dto.UserDTO;
import com.entitymodels.RoleEntity;
import com.entitymodels.UserEntity;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


public interface UserService {


    UserDTO findUserById(Long id);

    List<UserDTO> findAllUsers();

    UserDTO convertToUserDTO(UserEntity user);

    UserEntity convertToUserEntity(UserDTO userDTO);

    UserEntity findUserEntityById(Long id);

    UserDTO save(UserDTO userDTO);

    void deleteById(Long id);
}
