package com.stackroute.userservice.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.userservice.model.User;


@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserManagerRepositoryTest {

    @Autowired
    private UserManagerRepository accountManagerRepository;

    private User user;


    @Before
    public void setUp() throws Exception {
    	user = new User();
        user.setUsername("Jhon123");
        user.setFirstName("Jhon123");
        user.setLastName("Smith");
        user.setPassword("123456");
    }

    @After
    public void tearDown() throws Exception {
        accountManagerRepository.deleteAll();
    }

    @Test
    public void testRegisterUserSuccess() {
    	Assert.assertNotNull(accountManagerRepository.save(user));        
    }
    
    @Test
    public void testFindByUsernameAndPasswordSuccess() {
        accountManagerRepository.save(user);
        Assert.assertNotNull(accountManagerRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword()));
    }
    
    @Test
    public void testFindByUsernameAndPasswordNegative() {
        accountManagerRepository.save(user);
        Assert.assertNull(accountManagerRepository.findByUsernameAndPassword("Test", "Test"));
    }

    @Test
    public void testFindByUsername() {
        accountManagerRepository.save(user);
        Assert.assertNotNull(accountManagerRepository.findByUsername(user.getUsername()));
    }
    
    @Test
    public void testFindByUsernameNegative() {
        accountManagerRepository.save(user);
        Assert.assertEquals(0, accountManagerRepository.findByUsername("test").size());
    }

}
