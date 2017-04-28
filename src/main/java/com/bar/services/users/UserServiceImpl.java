package com.bar.services.users;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.bar.dbaccess.entities.UserEntity;
import com.bar.dbaccess.repositories.UsersRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private Mapper mapper;

	public User disableEnableUser(String username) {
		User user = null;
		UserEntity entity = usersRepository.findOne(username);
		if (entity != null) {
			Boolean state = entity.getEnabled();
			if (state.equals(Boolean.TRUE)) {
				entity.setEnabled(Boolean.FALSE);
			} else {
				entity.setEnabled(Boolean.TRUE);
			}
			user = mapper.map(usersRepository.save(entity), User.class);
		}
		return user;
	}

}
