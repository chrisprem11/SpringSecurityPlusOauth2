package test.demo.dao;

import java.util.List;

import test.demo.model.User;

public interface UserDao {

	User findById(int id);
	
	User findBySSO(String sso);

	List<User> findAllUser();
	
}

