package com.service.implementations;

import com.dto.UserDTO;
import com.entitymodels.UserEntity;
import com.repository.UserRepository;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserHibernateService implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserHibernateService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        userDTO.setFullName(user.getFullName());
        userDTO.setEmail(user.getEmail());
        userDTO.setAddress1(user.getAddress1());
        userDTO.setAddress2(user.getAddress2());
        userDTO.setCity(user.getCity());
        userDTO.setState(user.getState());
        userDTO.setZip(user.getZip());
        userDTO.setPhone(user.getPhone());
        userDTO.setRoles(user.getRoles());

        return userDTO;
    }

    public UserEntity convertToEntity(UserDTO userDTO) {
        UserEntity user = new UserEntity();
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
        user.setRoles(userDTO.getRoles());

        return user;

    }

    public UserDTO save(UserDTO userDTO) {
        UserEntity user = convertToEntity(userDTO);
        UserEntity savedUser = userRepository.save(user);
        UserDTO savedDTO = convertToUserDTO(savedUser);

        return savedDTO;
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
}
