package com.stackroute.bookrecommendationservice.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.bookrecommendationservice.model.Recommendation;
import com.stackroute.bookrecommendationservice.service.BookRecommendationService;


@RestController
@CrossOrigin
@EnableBinding(Sink.class)
public class BookRecommendationController {
	
	private BookRecommendationService bookRecommendationService;

	@Autowired
    public BookRecommendationController(BookRecommendationService bookRecommendationService) {
    	this.bookRecommendationService = bookRecommendationService;
	}
	
	@RequestMapping(value = "/api/v1/recommendation", method = RequestMethod.GET)
	public ResponseEntity<List<Recommendation>>getRecommendationList() {
		HttpHeaders headers = new HttpHeaders();
		List<Recommendation> recommendatedList = new ArrayList<Recommendation>();
		try {
			recommendatedList = bookRecommendationService.findAll();			
			if(null != recommendatedList && recommendatedList.size() > 0) {
				headers.add("Total Recommendate Articles Found: ", String.valueOf(recommendatedList.size()));	
				return new ResponseEntity<List<Recommendation>>(recommendatedList, headers, HttpStatus.OK);
			} else {
				return new ResponseEntity<List<Recommendation>>(recommendatedList, HttpStatus.NOT_FOUND);				
			}
		} catch (Exception e) {
			return new ResponseEntity<List<Recommendation>>(recommendatedList, HttpStatus.NOT_FOUND);
		}
	}
	
	@StreamListener(target = Sink.INPUT)
	public void addArticleToFavourite(Recommendation recommendation) {
		Recommendation recommendationDB = this.bookRecommendationService.findByBookID(recommendation.getBookID());
		
		if (null != recommendationDB && recommendationDB.getRecommendationCount() > 0) {
			this.bookRecommendationService.deleteByBookID(recommendationDB.getBookID());
			recommendationDB.setRecommendationCount(recommendationDB.getRecommendationCount() + 1);			
			this.bookRecommendationService.saveRecommendation(recommendationDB);
		} else {
			recommendation.setRecommendationCount(1);
			this.bookRecommendationService.saveRecommendation(recommendation);						
		}
	}
	
}
