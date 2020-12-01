package com.stackroute.favouriteservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.favouriteservice.model.Book;

@Repository
public interface FavouriteServiceRepository extends MongoRepository<Book, String>  {
	List<Book> findByUsername(String username);
	
	void deleteByUsernameAndBookID(String username, String bookID);
}
