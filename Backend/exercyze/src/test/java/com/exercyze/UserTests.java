package com.exercyze;

import com.exercyze.api.UserController;
import com.exercyze.dao.UserDao;
import com.exercyze.model.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

public class UserTests {

	@InjectMocks
	UserController userController;

	@Mock
	UserDao repo;

	@Before
	public void init(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findByUserNameTest(){
		when(repo.findByUserName("testuser")).thenReturn(new User(
				1,
				"testfirst",
				"testlast",
				"testuser",
				"testpass",
				500,
				100));

		User user = userController.getUserByUserName("testuser");

		assertEquals("testfirst", user.getFirstName());
		assertEquals("testlast", user.getLastName());
		assertEquals("testuser", user.getUserName());
		assertEquals("testpass", user.getPassword());
		assertEquals(500.0, user.getWeight(), 0);
		assertEquals(100.0, user.getHeight(), 0);
	}

	@Test
	public void findByIdTest(){
		when(repo.findUserById(1)).thenReturn(new User(
				1,
				"testfirst",
				"testlast",
				"testuser",
				"testpass",
				500,
				100));

		User user = userController.getUserById(1);

		assertEquals("testfirst", user.getFirstName());
		assertEquals("testlast", user.getLastName());
		assertEquals("testuser", user.getUserName());
		assertEquals("testpass", user.getPassword());
		assertEquals(500.0, user.getWeight(), 0);
		assertEquals(100.0, user.getHeight(), 0);
	}

	@Test
	public void findAllTest(){
		List<User> list = new ArrayList<User>();
		User u1 = new User(1,"testfirst", "testlast", "testuser", "testpass", 500, 100);
		User u2 = new User(1,"testfirst", "testlast", "test1user", "testpass", 500, 100);

		list.add(u1);
		list.add(u2);

		when(repo.findAll()).thenReturn(list);

		List<User> userList = userController.getAllUser();

		assertEquals(2, list.size());
	}

}
