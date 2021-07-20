package com.sboot.golden.services.users;

import java.util.List;

public interface UserService {

	public User disableEnableUser(Long id);

	public void newUser(User user);

	public void updatePassword(User user);

	public User findUser(String username);

	public List<User> allUsers();

	public List<User> allUsersEnabled();

}
