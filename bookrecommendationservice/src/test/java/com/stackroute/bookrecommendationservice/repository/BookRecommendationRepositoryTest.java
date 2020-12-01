package com.stackroute.bookrecommendationservice.repository;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.stackroute.bookrecommendationservice.model.Recommendation;
import com.stackroute.bookrecommendationservice.repository.BookRecommendationRepository;

@RunWith(SpringRunner.class)
@DataMongoTest
public class BookRecommendationRepositoryTest {
	
	@Autowired
	private BookRecommendationRepository articleRecommendationRepository;
	private Recommendation recommendation;
	
	@Before
    public void setUp() throws Exception {
		recommendation = new Recommendation();
		recommendation.setAuthor("TestAuthor");
		recommendation.setBookURL("TestDesc");
		recommendation.setBookID("TestTitle");
		recommendation.setRecommendationCount(0);
    }

    @After
    public void tearDown() throws Exception {
    	articleRecommendationRepository.deleteAll();
    }
    
    @Test
    public void createRecommendationTest() {
    	articleRecommendationRepository.insert(recommendation);
    	Recommendation fetchedrecommendation = articleRecommendationRepository.findByBookID(recommendation.getBookID());
        Assert.assertEquals(recommendation.getBookID(), fetchedrecommendation.getBookID());
    }
    
    @Test
    public void findByBookIDTestSuccess() {
    	articleRecommendationRepository.insert(recommendation);
    	Recommendation fetchedrecommendation = articleRecommendationRepository.findByBookID(recommendation.getBookID());
        Assert.assertEquals(recommendation.getBookID(), fetchedrecommendation.getBookID());
    }
    
    @Test
    public void findAllTest() {
    	articleRecommendationRepository.insert(recommendation);
    	Assert.assertNotNull(articleRecommendationRepository.findAll());
    }
    
    @Test
    public void deleteByIDTest() {
    	articleRecommendationRepository.insert(recommendation);
    	articleRecommendationRepository.deleteByBookID(recommendation.getBookID());
    	Assert.assertNull(articleRecommendationRepository.findByBookID(recommendation.getBookID()));
    }
}
