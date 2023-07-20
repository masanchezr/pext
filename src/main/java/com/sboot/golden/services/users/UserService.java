package com.sboot.golden.services.users;

import java.util.List;

public interface UserService {

	public void newUser(User user);

	public User findUser(String username);

	public List<User> allUsers();

	public List<User> allUsersEnabled();

}
