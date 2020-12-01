package com.stackroute.bookrecommendationservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.bookrecommendationservice.model.Recommendation;


@Repository
public interface BookRecommendationRepository extends MongoRepository<Recommendation, String>  {
	    
	Recommendation findByBookID(String bookID);
    
    List<Recommendation> findAll();
    
    void deleteByBookID(String bookID);
}
