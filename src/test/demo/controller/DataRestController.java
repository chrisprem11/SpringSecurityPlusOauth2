package test.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.demo.model.User;
import test.demo.service.UserService;

@RestController
public class DataRestController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/test", method = RequestMethod.GET, produces = MediaType.ALL_VALUE)
	public ResponseEntity<List<User>> getAllUsers() {
		List<User> allUser = userService.findAllUsers();
		if (allUser.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<User>>(allUser, HttpStatus.OK);
	}
}
