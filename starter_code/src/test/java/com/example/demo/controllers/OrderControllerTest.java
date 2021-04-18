package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Test
    public void submit() {
        User user = new User();
        user.setCart(new Cart());
        when(userRepository.findByUsername("My Username")).thenReturn(user);

        ResponseEntity<UserOrder> response = orderController.submit("My Username");
        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void submitWhenUserNotFound() {
        ResponseEntity<UserOrder> response = orderController.submit("My Username");
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void getOrdersForUser() {
        User user = new User();
        user.setCart(new Cart());
        when(userRepository.findByUsername("My Username")).thenReturn(user);

        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("My Username");
        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void getOrdersForUserWhenUserNotFound() {
        ResponseEntity<List<UserOrder>> response = orderController.getOrdersForUser("My Username");
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
