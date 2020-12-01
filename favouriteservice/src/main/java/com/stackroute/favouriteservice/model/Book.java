package com.stackroute.favouriteservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {
    
    private String username;
    private String bookTitle;    
    private String bookID;
    private String bookURL;    
	
    public Book() {
    	super();
    }

	public Book(String username, String bookTitle, String bookID, String bookURL) {
		super();
		this.username = username;
		this.bookTitle = bookTitle;
		this.bookID = bookID;
		this.bookURL = bookURL;
	}



	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookID() {
		return bookID;
	}

	public void setBookID(String bookID) {
		this.bookID = bookID;
	}

	public String getBookURL() {
		return bookURL;
	}

	public void setBookURL(String bookURL) {
		this.bookURL = bookURL;
	}

	@Override
	public String toString() {
		return "Favourite [username=" + username + ", bookTitle=" + bookTitle + ", bookID=" + bookID + ", bookURL="
				+ bookURL + "]";
	}
}
