package com.stackroute.favouriteservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.favouriteservice.exception.BookAlreadyAddedException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.repository.FavouriteServiceRepository;

@Service
public class FavouriteServiceServiceImpl implements FavouriteServiceService {

	private FavouriteServiceRepository bookRepository;
	
	@Autowired
	public FavouriteServiceServiceImpl(FavouriteServiceRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<Book> findAllFavourateBooksByUserId(String username) throws BookNotFoundException {
		return bookRepository.findByUsername(username);
	}

	@Override
	public Book addBookToFavourateList(Book favourite) throws BookAlreadyAddedException {
		return bookRepository.insert(favourite);		
	}
	
	@Override
	public List<Book> deleteFavourateBook(String username, String title) {
		bookRepository.deleteByUsernameAndBookID(username, title);
		return bookRepository.findByUsername(username);
	}
}
