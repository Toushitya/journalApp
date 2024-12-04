package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

//    @Disabled
    @Test
    public void testFindByUserName0(){
        assertEquals(4,2+2);
        assertNotNull(userRepository.findByUserName("Shyam"));
        assertTrue(5>3);

        User user = userRepository.findByUserName("Shyamo");
        assertEquals(true, user.getJournalEntries().isEmpty());

    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,5",
            "3,3,6"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected,a+b);
    }

    @ParameterizedTest
    @CsvSource({
            "Shyama",
            "Shyam",
            "Shyamo",
            "Shyamu"
    })
    public void testFindByUserName1(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for : "+name);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Shyama",
            "Shyam",
            "Shyams",
            "Shyamu"
    })
    public void testFindByUserName2(String name){
        assertNotNull(userRepository.findByUserName(name),"failed for : "+name);
    }

    @ParameterizedTest
    @ArgumentsSource(UserArgumentsProvider.class)
    public void testSaveNewUser(User user){
        assertTrue(userService.saveNewUser(user));
    }

}
