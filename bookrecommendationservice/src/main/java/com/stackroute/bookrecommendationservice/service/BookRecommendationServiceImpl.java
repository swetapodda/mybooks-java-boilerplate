package com.stackroute.bookrecommendationservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.bookrecommendationservice.model.Recommendation;
import com.stackroute.bookrecommendationservice.repository.BookRecommendationRepository;


@Service
public class BookRecommendationServiceImpl implements BookRecommendationService {

	private BookRecommendationRepository articleRecommendationRepository;
	
	@Autowired
	public BookRecommendationServiceImpl(BookRecommendationRepository articleRecommendationRepository) {
		this.articleRecommendationRepository = articleRecommendationRepository;
	}

	@Override
	public Recommendation findByBookID(String bookID) {
		return articleRecommendationRepository.findByBookID(bookID);
	}

	@Override
	public List<Recommendation> findAll() {
		return articleRecommendationRepository.findAll();
	}

	@Override
	public Recommendation saveRecommendation(Recommendation recommendation) {
		return articleRecommendationRepository.insert(recommendation);
	}

	@Override
	public void deleteByBookID(String bookID) {
		articleRecommendationRepository.deleteByBookID(bookID);
		
	}
}
