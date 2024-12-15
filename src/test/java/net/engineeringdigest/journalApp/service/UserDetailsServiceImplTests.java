package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;
//@SpringBootTest not used here for mock because it loads everything from mongo and we don't want to use spring context as it is a mock
//we want to work only on the UserDetailsServiceImpl service and no components should be loaded except it
// no database connections should be made as it is a mock
public class UserDetailsServiceImplTests {

    //so we will use @InjectMocks annotation for the above purpose
    //this means that we are telling it to inject mock on the UserDetailsServiceImpl dependency
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;

    //this is used to initialize all the mocks for this class and also inject them
    // this means that UserRepository will be initialized and injected
    @BeforeEach
    void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void loadByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Ram").password("Ram").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Ram");
        Assertions.assertNotNull(user);
    }
}
