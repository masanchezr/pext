package com.gu.services.users;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.RoleEntity;
import com.gu.dbaccess.entities.UserEntity;
import com.gu.dbaccess.repositories.EmployeesRepository;
import com.gu.dbaccess.repositories.RolesRepository;
import com.gu.dbaccess.repositories.UsersRepository;

public class UserServiceImpl implements UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private EmployeesRepository employeesRepository;

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private PasswordEncoder pbkdf2Encoder;

	@Autowired
	private Mapper mapper;

	public User disableEnableUser(String username) {
		User user = null;
		UserEntity entity = usersRepository.findById(username).orElse(null);
		if (entity != null) {
			Boolean state = entity.getEnabled();
			if (state.equals(Boolean.TRUE)) {
				entity.setEnabled(Boolean.FALSE);
			} else {
				entity.setEnabled(Boolean.TRUE);
			}
			user = mapper.map(usersRepository.save(entity), User.class);
		}
		EmployeeEntity employee = employeesRepository.findByUsername(username);
		if (employee != null) {
			Boolean state = employee.getEnabled();
			if (state.equals(Boolean.TRUE)) {
				employee.setEnabled(Boolean.FALSE);
			} else {
				employee.setEnabled(Boolean.TRUE);
			}
			mapper.map(employeesRepository.save(employee), user);
		}
		return user;
	}

	public void newUser(User user) {
		user.setPassword(pbkdf2Encoder.encode(user.getPassword()));
		usersRepository.save(mapper.map(user, UserEntity.class));
		employeesRepository.save(mapper.map(user, EmployeeEntity.class));
		rolesRepository.save(mapper.map(user, RoleEntity.class));
	}

	public void updatePassword(User user) {
		UserEntity entity = usersRepository.findById(user.getUsername()).orElse(null);
		if (entity != null) {
			entity.setPassword(pbkdf2Encoder.encode(user.getPassword()));
			usersRepository.save(entity);
		}
	}

	@Override
	public User findUser(String username) {
		UserEntity entity = usersRepository.findById(username).orElse(null);
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

	@Override
	public void update(User user) {
		user.setPassword(pbkdf2Encoder.encode(user.getPassword()));
		usersRepository.save(mapper.map(user, UserEntity.class));
		EmployeeEntity employee = employeesRepository.findByUsername(user.getUsername());
		if (employee != null) {
			Boolean state = employee.getEnabled();
			if (state.equals(Boolean.TRUE)) {
				employee.setEnabled(Boolean.FALSE);
			} else {
				employee.setEnabled(Boolean.TRUE);
			}
			mapper.map(employeesRepository.save(employee), user);
		}
	}
}