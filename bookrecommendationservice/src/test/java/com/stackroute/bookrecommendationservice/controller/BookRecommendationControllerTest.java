package com.stackroute.bookrecommendationservice.controller;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stackroute.bookrecommendationservice.controller.BookRecommendationController;
import com.stackroute.bookrecommendationservice.model.Recommendation;
import com.stackroute.bookrecommendationservice.service.BookRecommendationService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class BookRecommendationControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private BookRecommendationService gipherRecommendationService;
	
	@InjectMocks
	private BookRecommendationController gipherRecommendationController;
	private Recommendation recommendation;
	private List<Recommendation> list;
	
	@Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(gipherRecommendationController).build();
        recommendation = new Recommendation();
        recommendation.setAuthor("TestAuthor");
        recommendation.setBookURL("TestDesc");
        recommendation.setBookID("TestTitle");
        recommendation.setRecommendationCount(4);

        list = new ArrayList<Recommendation>();
        list.add(recommendation);
    }
	
	@Test
    public void getRecommendationListSuccess() throws Exception {
        when(gipherRecommendationService.findAll()).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommendation")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recommendation)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void getRecommendationListNegative() throws Exception {
        when(gipherRecommendationService.findAll()).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/recommendation")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(recommendation)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void addArticleToFavouriteUpdate() throws Exception {
        when(gipherRecommendationService.findByBookID(any())).thenReturn(recommendation);
        gipherRecommendationController.addArticleToFavourite(recommendation);
    }
	
	@Test
    public void addArticleToFavouriteInsert() throws Exception {
		recommendation.setRecommendationCount(0);
        when(gipherRecommendationService.findByBookID(any())).thenReturn(recommendation);
        gipherRecommendationController.addArticleToFavourite(recommendation);
    }
	
	private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }	
}
