package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CartControllerTest {

    @InjectMocks
    private CartController cartController;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void addTocart() {
        User user = new User();
        Cart cart = new Cart();
        Item item = new Item();
        item.setId(1L);
        item.setPrice(BigDecimal.valueOf(10));
        cart.addItem(item);
        user.setCart(cart);
        when(userRepository.findByUsername("My Username")).thenReturn(user);
        when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(item));

        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(1L);
        modifyCartRequest.setUsername("My Username");

        ResponseEntity<Cart> response = cartController.addTocart(modifyCartRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void addTocartWhenUserNotFound() {
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        ResponseEntity<Cart> response = cartController.addTocart(modifyCartRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void addTocartWhenItemNotFound() {
        User user = new User();
        Cart cart = new Cart();
        Item item = new Item();
        item.setId(1L);
        item.setPrice(BigDecimal.valueOf(10));
        cart.addItem(item);
        user.setCart(cart);
        when(userRepository.findByUsername("My Username")).thenReturn(user);

        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(1L);
        modifyCartRequest.setUsername("My Username");

        ResponseEntity<Cart> response = cartController.addTocart(modifyCartRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void removeFromcart() {
        User user = new User();
        Cart cart = new Cart();
        Item item = new Item();
        item.setId(1L);
        item.setPrice(BigDecimal.valueOf(10));
        cart.addItem(item);
        user.setCart(cart);
        when(userRepository.findByUsername("My Username")).thenReturn(user);
        when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(item));

        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(1L);
        modifyCartRequest.setUsername("My Username");

        ResponseEntity<Cart> response = cartController.removeFromcart(modifyCartRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }

    @Test
    public void removeFromcartWhenUserNotFound() {
        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        ResponseEntity<Cart> response = cartController.removeFromcart(modifyCartRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }

    @Test
    public void removeFromcartWhenItemNotFound() {
        User user = new User();
        Cart cart = new Cart();
        Item item = new Item();
        item.setId(1L);
        item.setPrice(BigDecimal.valueOf(10));
        cart.addItem(item);
        user.setCart(cart);
        when(userRepository.findByUsername("My Username")).thenReturn(user);

        ModifyCartRequest modifyCartRequest = new ModifyCartRequest();
        modifyCartRequest.setItemId(1L);
        modifyCartRequest.setUsername("My Username");

        ResponseEntity<Cart> response = cartController.removeFromcart(modifyCartRequest);
        assertNotNull(response, "Response should not be null");
        assertEquals(404, response.getStatusCodeValue());
        assertNull(response.getBody());
    }
}
