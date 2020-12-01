package com.stackroute.favouriteservice.service;

import java.util.List;

import com.stackroute.favouriteservice.exception.BookAlreadyAddedException;
import com.stackroute.favouriteservice.exception.BookNotFoundException;
import com.stackroute.favouriteservice.model.Book;

public interface FavouriteServiceService {

    public List<Book> findAllFavourateBooksByUserId(String username) throws BookNotFoundException;

    public Book addBookToFavourateList(Book favourite) throws BookAlreadyAddedException;
    
    public List<Book> deleteFavourateBook(String username, String title);
}
