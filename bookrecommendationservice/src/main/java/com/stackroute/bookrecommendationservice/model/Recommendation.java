package com.stackroute.bookrecommendationservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Recommendation {
	
    private String author;    
    private String bookID;
    private String bookURL;
    private String bookTitle;
    private int recommendationCount;
	
    public Recommendation() {
    	super();
    }

	public Recommendation(String author, String bookID, String bookURL, int recommendationCount) {
		super();
		this.author = author;
		this.bookID = bookID;
		this.bookID = bookURL;
		this.recommendationCount = recommendationCount;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	@Override
	public String toString() {
		return "Favourite [author=" + author + ", bookID=" + bookID + ", bookURL="
				+ bookURL + ", recommendationCount=" + recommendationCount + ", bookTitle=" + bookTitle + " ]";
	}

	public int getRecommendationCount() {
		return recommendationCount;
	}

	public void setRecommendationCount(int recommendationCount) {
		this.recommendationCount = recommendationCount;
	}
}
