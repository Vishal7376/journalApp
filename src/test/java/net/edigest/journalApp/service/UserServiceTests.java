package net.edigest.journalApp.service;

import net.edigest.journalApp.Service.UserService;
import net.edigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    UserRepository userRepository;
    
    @Autowired UserService userService;

    @Test
    public void testFindByUsername() {
        assertNotNull(userRepository.findByUserName("ram"));
    }

}
