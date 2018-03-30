package com.gu.services.users;

import java.util.List;

public interface UserService {

	public User disableEnableUser(String username);

	public void newUser(User user);

	public void updatePassword(User user);

	public User findUser(String username);

	public List<User> allUsers();

	public void update(User user);

}
