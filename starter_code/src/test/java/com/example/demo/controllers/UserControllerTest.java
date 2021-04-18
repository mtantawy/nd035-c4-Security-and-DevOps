package com.example.demo.controllers;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Test
    public void userIsCreated() {
        when(bCryptPasswordEncoder.encode("MyPassword")).thenReturn("HashedPassword");
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("MyUsername");
        createUserRequest.setPassword("MyPassword");
        createUserRequest.setConfirmPassword("MyPassword");

        ResponseEntity<User> response = userController.createUser(createUserRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("MyUsername", response.getBody().getUsername());
        assertEquals("HashedPassword", response.getBody().getPassword());
    }

    @Test
    public void userIsNotCreatedForShortPassword() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("MyUsername");
        createUserRequest.setPassword("pass");
        createUserRequest.setConfirmPassword("pass");

        ResponseEntity<User> response = userController.createUser(createUserRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void findById() {
        User user = new User();
        user.setId(1L);
        user.setUsername("MyUsername");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.findById(1L);
        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("MyUsername", response.getBody().getUsername());
        assertEquals(1L, response.getBody().getId());
    }

    @Test
    public void findByUserName() {
        User user = new User();
        user.setUsername("MyUsername");
        when(userRepository.findOptionalByUsername("MyUsername")).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userController.findByUserName("MyUsername");
        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("MyUsername", response.getBody().getUsername());
    }
}
