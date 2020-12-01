package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.favouriteservice.model.Book;

@RunWith(SpringRunner.class)
@DataMongoTest
public class FavouriteRepositoryTest {
	
	@Autowired
	private FavouriteServiceRepository BookRepository;
	private Book Book;
	
	@Before
    public void setUp() throws Exception {
        Book = new Book();
        Book.setBookTitle("TestAuthor");
        Book.setBookURL("TestDesc");
        Book.setBookID("TestTitle");
        Book.setUsername("TestUser");
    }

    @After
    public void tearDown() throws Exception {
    	BookRepository.deleteAll();
    }
    
    @Test
    public void createBookTest() {
    	BookRepository.insert(Book);
    	Book fetchedBook = BookRepository.findByUsername(Book.getUsername()).get(0);
        Assert.assertEquals("TestUser", fetchedBook.getUsername());
    }
    
    @Test
    public void findByUsernameTest() {
    	BookRepository.insert(Book);
    	Book fetchedBook = BookRepository.findByUsername(Book.getUsername()).get(0);
        Assert.assertEquals("TestUser", fetchedBook.getUsername());
    }
    
    @Test
    public void findByUsernameTestFailure() {
    	BookRepository.insert(Book);
    	Book fetchedBook = BookRepository.findByUsername(Book.getUsername()).get(0);
        Assert.assertNotEquals("TestUser1", fetchedBook.getUsername());
    }
    
    @Test
    public void deleteFavourateBookSuccess() {
    	BookRepository.insert(Book);
    	BookRepository.deleteByUsernameAndBookID(Book.getUsername(), Book.getBookID());
    	List<Book> list = BookRepository.findByUsername(Book.getUsername());
        Assert.assertEquals(0, list.size());
    }
}
