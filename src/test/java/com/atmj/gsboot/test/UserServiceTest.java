package com.atmj.gsboot.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atmj.gsboot.services.users.User;
import com.atmj.gsboot.services.users.UserService;

@SpringBootTest
class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	void newUserTest() {
		User user = new User();
		user.setEnabled(true);
		user.setAlias("Prueba10");
		user.setName("Prueba10");
		user.setPassword("1981");
		user.setRole("USER");
		user.setUsername("prueba10");
		if (userService.findUser(user.getUsername()) == null) {
			userService.newUser(user);
		}
	}

	@Test
	void updateUserTest() {
		User user = new User();
		user.setEnabled(false);
		user.setPassword("1981");
		user.setUsername("prueba10");
		userService.newUser(user);
	}
}
