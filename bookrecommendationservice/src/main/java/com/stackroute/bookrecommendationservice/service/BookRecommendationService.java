package com.stackroute.bookrecommendationservice.service;

import java.util.List;

import com.stackroute.bookrecommendationservice.model.Recommendation;

public interface BookRecommendationService {

	Recommendation findByBookID(String bookID);
    
    List<Recommendation> findAll();
    
    Recommendation saveRecommendation(Recommendation recommendation);
    	
	void deleteByBookID(String bookID);
}
