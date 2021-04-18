package com.example.demo.secret;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.security.UserDetailsServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void loadUserByUsername() {
        User user = new User();
        user.setUsername("My Username");
        user.setPassword("My Password");
        when(userRepository.findByUsername("My Username")).thenReturn(user);
        UserDetails userDetails = userDetailsService.loadUserByUsername("My Username");
        Assertions.assertNotNull(userDetails);
    }

    @Test
    public void loadUserByUsernameWhenUserNotFoundThrows() {
        Assertions.assertThrows(
                UsernameNotFoundException.class,
                () -> userDetailsService.loadUserByUsername("My Username"));
    }
}
