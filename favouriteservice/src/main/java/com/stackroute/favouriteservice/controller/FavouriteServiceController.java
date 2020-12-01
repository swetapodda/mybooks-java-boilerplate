package com.stackroute.favouriteservice.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.service.FavouriteServiceService;
import com.stackroute.favouriteservice.source.FavouriteServiceServiceSource;


@RestController
//@CrossOrigin
@EnableBinding(FavouriteServiceServiceSource.class)
public class FavouriteServiceController {
	
	private FavouriteServiceService BookService;

	@Autowired
    public FavouriteServiceController(FavouriteServiceService BookService) {
    	this.BookService = BookService;
	}
	
	@Autowired
	FavouriteServiceServiceSource BookServiceSource;
	
	
	/**
	 * 
	 * @param favourite
	 * @return
	 */
	@RequestMapping(value = "/api/v1/favourate", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Book> addBookToFavourateList(@RequestBody Book favourite) {
		
		if (favourite == null || StringUtils.isEmpty(favourite.getUsername()) || StringUtils.isEmpty(favourite.getBookTitle())
				|| StringUtils.isEmpty(favourite.getBookID()) || StringUtils.isEmpty(favourite.getBookURL()) ) {
			return new ResponseEntity<Book>(HttpStatus.BAD_REQUEST);
		}
		
		Book insettedFavourite = null;
		List<Book> favouriteList = null;
		try {			
			favouriteList = this.BookService.findAllFavourateBooksByUserId(favourite.getUsername());
			if(null != favouriteList && favouriteList.size() > 0) {
				for (Book favourite2 : favouriteList) {
					if (favourite2.getBookID().equalsIgnoreCase(favourite.getBookID())) {
						return new ResponseEntity<Book>(insettedFavourite, HttpStatus.CONFLICT);												
					}					
				}				
			}			
			insettedFavourite = BookService.addBookToFavourateList(favourite);			
			BookServiceSource.addToFavourite().send(MessageBuilder.withPayload(favourite).build());
			
			return new ResponseEntity<Book>(insettedFavourite, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<Book>(insettedFavourite, HttpStatus.CONFLICT);
		}
	}
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	@RequestMapping(value = "/api/v1/favourate/{username}", method = RequestMethod.GET)
	public ResponseEntity<List<Book>> findAllFavourateBooksByUserId(@PathVariable("username") String username) {		
		HttpHeaders headers = new HttpHeaders();
		List<Book> favouriteList = new ArrayList<Book>();
		try {
			favouriteList = this.BookService.findAllFavourateBooksByUserId(username);
			if(null != favouriteList && favouriteList.size() > 0) {
				headers.add("Total Favourate Articles Found: ", String.valueOf(favouriteList.size()));	
				return new ResponseEntity<List<Book>>(favouriteList, headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Book>>(favouriteList, headers, HttpStatus.NOT_FOUND);				
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Book>>(favouriteList, headers, HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * 
	 * @param username
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "/api/v1/favourate/{username}/{title}", method = RequestMethod.DELETE)
	public ResponseEntity<List<Book>> deleteFavourateBook(@PathVariable("username") String username, @PathVariable("title") String title) {		
		HttpHeaders headers = new HttpHeaders();
		List<Book> favouriteList = new ArrayList<Book>();
		try {			
			favouriteList = this.BookService.deleteFavourateBook(username, title);
			return new ResponseEntity<List<Book>>(favouriteList, headers, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<List<Book>>(favouriteList, headers, HttpStatus.OK);
		}
	}
}
