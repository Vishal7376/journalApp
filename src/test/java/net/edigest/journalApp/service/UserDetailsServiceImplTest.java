package net.edigest.journalApp.service;

import net.edigest.journalApp.Service.UserDetailsServiceImpl;
import net.edigest.journalApp.entity.User;
import net.edigest.journalApp.repository.UserRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentMatchers;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;


public class UserDetailsServiceImplTest {

    @InjectMocks
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Mock
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsernameTest(){

        User mockUser = new User("ram", "pinprick");

        ArrayList<String> roles = new ArrayList<>();
        roles.add("USER");

        mockUser.setRoles(roles);

        when(userRepository.findByUserName(ArgumentMatchers.anyString()))
                .thenReturn(mockUser);

        UserDetails user =
                userDetailsServiceImpl.loadUserByUsername("ram");

        Assertions.assertNotNull(user);
    }
}