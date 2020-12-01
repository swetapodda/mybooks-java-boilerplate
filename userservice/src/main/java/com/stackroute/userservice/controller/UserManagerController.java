package com.stackroute.userservice.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.userservice.model.User;
import com.stackroute.userservice.service.UserManagerService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
public class UserManagerController {

	private UserManagerService accountManagerService;

	@Autowired
	public UserManagerController(UserManagerService accountManagerService) {
		this.accountManagerService = accountManagerService;
	}

	@RequestMapping(value = "/api/v1/auth/register", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Boolean> createUser(@RequestBody User user) {

		if (user == null) {
			return new ResponseEntity<Boolean>(HttpStatus.BAD_REQUEST);
		}

		try {
			if (isAllValuesPresent(user)) {
				if (accountManagerService.saveUser(user)) {
					return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
				}
			}
		} catch (Exception e) {
			return new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.NOT_ACCEPTABLE);
	}

	private boolean isAllValuesPresent(User user) {
		if (StringUtils.isEmpty(user.getFirstName()) || StringUtils.isEmpty(user.getLastName())
				|| StringUtils.isEmpty(user.getUsername()) || StringUtils.isEmpty(user.getPassword())) {
			return false;
		}
		return true;
	}

	@RequestMapping(value = "/api/v1/auth/validate")
	public ResponseEntity<String> validateUser(@RequestBody User user) {
		if (user == null) {
			return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		String token = null;
		try {
			token = getToken(user.getUsername(), user.getPassword());
			if (!StringUtils.isEmpty(token)) {
				return new ResponseEntity<String>(token, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>(token, HttpStatus.NOT_FOUND);
			}
		} catch (Exception e) {
			return new ResponseEntity<String>(token, HttpStatus.NOT_FOUND);
		}
	}

	public String getToken(String username, String password) throws Exception {
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			throw new ServletException("Username or Password not filled in");
		}

		if (null != accountManagerService.findByUsernameAndPassword(username, password)) {
			String jwtToken = Jwts.builder().setSubject(username).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 300000))
					.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
			return jwtToken;
		}
		throw new ServletException("Username or Password is Wrong");
	}
}