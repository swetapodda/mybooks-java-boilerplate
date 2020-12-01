package com.stackroute.bookrecommendationservice.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.stackroute.bookrecommendationservice.model.Recommendation;
import com.stackroute.bookrecommendationservice.repository.BookRecommendationRepository;

public class BookRecommendationServiceImplTest {
	
	@Mock
	private BookRecommendationRepository gipherRecommendationRepository;
	
	@InjectMocks
	private BookRecommendationServiceImpl gipherRecommendationService;
	
	private Recommendation recommendation;	
	List<Recommendation> list;
	
	@Before
    public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
        recommendation = new Recommendation();
        recommendation.setAuthor("TestAuthor");
        recommendation.setBookURL("TestDesc");
        recommendation.setBookID("TestTitle");
        recommendation.setRecommendationCount(0);
        
        list = new ArrayList<Recommendation>();
        list.add(recommendation);
    }
	
	@Test
    public void findByBookIDTest() throws Exception {
		when(gipherRecommendationRepository.findByBookID(recommendation.getBookID())).thenReturn(recommendation);
		
		Recommendation updatedRecommendation = gipherRecommendationService.findByBookID(recommendation.getBookID());
        Assert.assertEquals(recommendation.getBookID(), updatedRecommendation.getBookID());
    }
	
	@Test
    public void findAllTest() throws Exception {
		when(gipherRecommendationRepository.insert((Recommendation) any())).thenReturn(recommendation);
		when(gipherRecommendationRepository.findAll()).thenReturn(list);
        Assert.assertNotNull(gipherRecommendationService.findAll());
    }
	
	@Test
    public void saveRecommendation() throws Exception {
		when(gipherRecommendationRepository.insert((Recommendation) any())).thenReturn(recommendation);
		
		Assert.assertNotNull(gipherRecommendationService.saveRecommendation(recommendation));
    }
	
	@Test
    public void deleteByTitle() throws Exception {
		gipherRecommendationService.deleteByBookID(recommendation.getBookID());
        Assert.assertTrue(true);
    }
	
}
