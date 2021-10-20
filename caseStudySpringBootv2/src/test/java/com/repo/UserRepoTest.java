package com.repo;

import com.entitymodels.UserEntity;
import com.repository.UserRepository;
import org.apache.catalina.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;


import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value=false)
    public void saveUserTest(){
        UserEntity user = new UserEntity();
        user.setFullName("Test Guy");
        user.setEmail("testemail1@aol.com");
        user.setPassword("Password12345");
        user.setAddress1("743 Evergreen Terrace");
        user.setCity("Springfield");
        user.setState("OR");
        user.setCountry("Mexico");
        user.setZip("60000");
        user.setPhone("555-0192");

        userRepository.save(user);

        UserEntity testSave = userRepository.findById(1);
        assertEquals(user.getFullName(), testSave.getFullName());
        assertNotEquals(testSave.getId(), 2);
        assertEquals(user.getId(), 1);
    }

    @Test
    @Order(2)
    public void getUserTest(){
        UserEntity user = userRepository.findById(1);
        assertEquals(user.getFullName(), "Test Guy");
        assertEquals(user.getId(), 1);
    }

    @Test
    @Order(3)
    public void getAllUsersTest(){
        List<UserEntity> users = userRepository.findAll();
        assertThat(users.size()).isGreaterThan(0);
        users.forEach(user -> assertThat(user.getId()).isGreaterThan(0));
    }

    @Test
    @Order(4)
    public void updateUserTest(){
        UserEntity user = userRepository.findById(1);
        assertEquals(userRepository.findById(1).getFullName(), "Test Guy");

        user.setFullName("Homer Simpson");
        userRepository.save(user);
        assertEquals(userRepository.findById(1).getFullName(), "Homer Simpson");
    }

    @Test
    @Order(5)
    public void deleteUserTest(){
        userRepository.deleteById((long) 1);
        Optional<UserEntity> optionalUser = Optional.ofNullable(userRepository.findById(1));
        assertEquals(optionalUser, Optional.empty());
    }

}
