package com.service;

import com.dto.UserDTO;
import com.entitymodels.UserEntity;
import com.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;


public interface UserService {


    public UserDTO findUserById(Long id);

    public List<UserDTO> findAllUsers();

    public UserDTO convertToUserDTO(UserEntity user);

    public UserEntity convertToEntity(UserDTO userDTO);

    public UserDTO save(UserDTO userDTO);

    void deleteById(Long id);
}
