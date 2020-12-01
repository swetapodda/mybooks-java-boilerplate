package com.stackroute.userservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.userservice.exception.UserAlreadyExistsException;
import com.stackroute.userservice.exception.UserNotFoundException;
import com.stackroute.userservice.model.User;
import com.stackroute.userservice.repository.UserManagerRepository;

@Service
public class UserManagerServiceImpl implements UserManagerService {

	private UserManagerRepository accountManagerRepository;
	
	@Autowired
	public UserManagerServiceImpl(UserManagerRepository accountManagerRepository) {
		this.accountManagerRepository = accountManagerRepository;
	}
	
    @Override
    public User findByUsernameAndPassword(String username, String password) throws UserNotFoundException {
    	return accountManagerRepository.findByUsernameAndPassword(username, password);
    }

    @Override
    public boolean saveUser(User user) throws UserAlreadyExistsException {
    	try {
    		List<User> usersList = accountManagerRepository.findByUsername(user.getUsername());
    		if (null == usersList || usersList.size() == 0) {
    			accountManagerRepository.save(user);
    			return true;
			}
    		throw new UserAlreadyExistsException("User Alreay Registered");		
		} catch (Exception e) {
			throw new UserAlreadyExistsException("User Alreay Registered");
		}
    }
}
