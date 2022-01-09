package com.sboot.golden.services.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.repositories.UsersRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private PasswordEncoder pbkdf2Encoder;

	@Autowired
	private ModelMapper mapper;

	public User disableEnableUser(Long id) {
		User user = null;
		UserEntity entity = usersRepository.findById(id).orElse(null);
		if (entity != null) {
			Boolean state = entity.getEnabled();
			if (state.equals(Boolean.TRUE)) {
				entity.setEnabled(Boolean.FALSE);
			} else {
				entity.setEnabled(Boolean.TRUE);
			}
			user = mapper.map(usersRepository.save(entity), User.class);
			UserEntity employee = usersRepository.findByUsername(entity.getUsername());
			if (employee != null) {
				Boolean stateEmployee = employee.getEnabled();
				if (stateEmployee.equals(Boolean.TRUE)) {
					employee.setEnabled(Boolean.FALSE);
				} else {
					employee.setEnabled(Boolean.TRUE);
				}
				mapper.map(usersRepository.save(employee), user);
			}
		}
		return user;
	}

	public void updatePassword(User user) {
		UserEntity entity = usersRepository.findById(user.getId()).orElse(null);
		if (entity != null) {
			entity.setPassword(pbkdf2Encoder.encode(user.getPassword()));
			usersRepository.save(entity);
		}
	}

	@Override
	public User findUser(String username) {
		UserEntity entity = usersRepository.findByUsername(username);
		User user = null;
		if (entity != null) {
			user = mapper.map(entity, User.class);
		}
		return user;
	}

	@Override
	public List<User> allUsers() {
		return mapper(usersRepository.findAll());
	}

	private List<User> mapper(Iterable<UserEntity> findAll) {
		List<User> users = null;
		if (findAll != null) {
			users = new ArrayList<>();
			Iterator<UserEntity> iusers = findAll.iterator();
			while (iusers.hasNext()) {
				users.add(mapper.map(iusers.next(), User.class));
			}
		}
		return users;
	}

	public void newUser(User user) {
		user.setPassword(pbkdf2Encoder.encode(user.getPassword()));
		usersRepository.save(mapper.map(user, UserEntity.class));
	}

	@Override
	public List<User> allUsersEnabled() {
		return mapper(usersRepository.findByEnabledTrue());
	}
}