package com.stackroute.favouriteservice.controller;


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
import com.stackroute.favouriteservice.model.Book;
import com.stackroute.favouriteservice.service.FavouriteServiceService;


@RunWith(SpringRunner.class)
@WebMvcTest
public class FavouriteControllerTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private FavouriteServiceService BookService;
	
	@InjectMocks
	private FavouriteServiceController BookController;
	private Book favourite;
	private List<Book> list;
	
	@Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(BookController).build();
        favourite = new Book();
        favourite.setBookTitle("TestAuthor");
        favourite.setBookURL("TestDesc");
        favourite.setBookID("TestTitle");
        favourite.setUsername("TestUser");

        list = new ArrayList<Book>();
        list.add(favourite);
    }
	
	@Test
    public void addBookToFavourateListSuccess() throws Exception {
        when(BookService.findAllFavourateBooksByUserId(any())).thenReturn(null);
        when(BookService.addBookToFavourateList((Book) any())).thenReturn(favourite);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favourate")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void addBookToFavourateListFailure() throws Exception {
        when(BookService.findAllFavourateBooksByUserId(any())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/favourate")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void findAllFavourateBooksByUserIdSuccess() throws Exception {
        when(BookService.findAllFavourateBooksByUserId(any())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favourate/TestUser")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void findAllFavourateBookByUserIdFailure() throws Exception {
        when(BookService.findAllFavourateBooksByUserId(any())).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/favourate/TestUser")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }
	
	@Test
    public void deleteFavourateBookSuccess() throws Exception {
        when(BookService.deleteFavourateBook(favourite.getUsername(), favourite.getBookID())).thenReturn(list);
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/favourate/TestUser/TestTitle")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(favourite)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
	
	
	private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }	
}
