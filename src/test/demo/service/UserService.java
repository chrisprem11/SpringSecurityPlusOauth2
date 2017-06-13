package test.demo.service;

import java.util.List;

import test.demo.model.User;

public interface UserService {

	User findById(int id);
	
	User findBySso(String sso);

	List<User> findAllUsers();
	
}