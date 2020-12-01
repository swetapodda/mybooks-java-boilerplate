package com.stackroute.favouriteservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.favouriteservice.exception.BookAlreadyAddedException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.repository.FavouriteServiceRepository;

public class FavouriteServiceImplTest {
	
	@Mock
	private FavouriteServiceRepository BookManagerRepository;
	
	@InjectMocks
	private FavouriteServiceServiceImpl BookServiceImpl;
	
	private Book Book;	
	List<Book> list;
	
	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        Book = new Book();
        Book.setBookTitle("TestAuthor");
        Book.setBookURL("TestDesc");
        Book.setBookID("TestTitle");
        Book.setUsername("TestUser");
        
        list = new ArrayList<Book>();
        list.add(Book);
    }
	
	@Test
    public void addBookToFavourateListTest() throws BookAlreadyAddedException {
		when(BookManagerRepository.insert((Book) any())).thenReturn(Book);
		
		Book updatedBook = BookServiceImpl.addBookToFavourateList(Book);
        Assert.assertEquals(Book, updatedBook);
    }
	
	@Test
    public void addBookToFavourateListTestFailure() throws BookAlreadyAddedException {
		when(BookManagerRepository.insert((Book) any())).thenReturn(null);	
		Book updatedBook = BookServiceImpl.addBookToFavourateList(Book);
        Assert.assertNotEquals(Book, updatedBook);
    }
	
	@Test
    public void findAllFavourateBooksByUserIdTest() throws BookNotFoundException {
		when(BookManagerRepository.findByUsername(Book.getUsername())).thenReturn(list);
		List<Book> returnedList = BookServiceImpl.findAllFavourateBooksByUserId(Book.getUsername());
        Assert.assertEquals(list, returnedList);
    }
	
	@Test
    public void findAllFavourateBooksByUserIdTestFailure() throws BookNotFoundException {
		when(BookManagerRepository.findByUsername(Book.getUsername())).thenReturn(null);
		List<Book> returnedList = BookServiceImpl.findAllFavourateBooksByUserId(Book.getUsername());
        Assert.assertNotEquals(list, returnedList);
    }
	
	@Test
    public void deleteFavourateBookSuccess() {
		when(BookManagerRepository.findByUsername(Book.getUsername())).thenReturn(list);
		List<Book> returnedList = BookServiceImpl.deleteFavourateBook(Book.getUsername(), Book.getBookID());
        Assert.assertEquals(list, returnedList);
    }
	
}
