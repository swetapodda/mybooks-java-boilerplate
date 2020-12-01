package com.stackroute.userservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserManagerRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserManagerServiceTest {
	
	@Mock
    private UserManagerRepository accountManagerRepository;

    private User user;
    @InjectMocks
    private UserManagerServiceImpl accountManagerService;

    Optional<User> optional;
    List<User> list;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        user = new User();
        user.setUsername("Jhon123");
        user.setFirstName("Jhon123");
        user.setLastName("Smith");
        user.setPassword("123456");
        
        list = new ArrayList<User>();
        list.add(user);
        
        optional = Optional.of(user);
    }

    @Test
    public void testSaveUserSuccess() throws Exception {
    	Mockito.when(accountManagerRepository.findByUsername(user.getUsername())).thenReturn(null);
        boolean flag = accountManagerService.saveUser(user);
        Assert.assertTrue(flag);
    }
    
    @Test(expected = UserAlreadyExistsException.class)
    public void testSaveUseNegative() throws Exception {
    	Mockito.when(accountManagerRepository.findByUsername(user.getUsername())).thenReturn(list);
        boolean flag = accountManagerService.saveUser(user);
        Assert.assertEquals("User Alreay Registered", true, flag);
        Assert.assertTrue(flag);
    }

    @Test
    public void testfindByUsernameAndPassword() throws Exception {
        Mockito.when(accountManagerRepository.findByUsernameAndPassword("Jhon123", "123456")).thenReturn(user);
        Assert.assertNotNull(accountManagerService.findByUsernameAndPassword("Jhon123", "123456"));
    }
    
    @Test
    public void testfindByUsernameAndNegative() throws Exception {
        Mockito.when(accountManagerRepository.findByUsernameAndPassword("Jhon123", "123456")).thenReturn(null);
        Assert.assertNull(accountManagerService.findByUsernameAndPassword("Jhon123", "123456"));
    }
}