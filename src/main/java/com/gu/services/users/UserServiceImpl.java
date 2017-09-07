package com.gu.services.users;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

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
		usersRepository.save(mapper.map(user, UserEntity.class));
		employeesRepository.save(mapper.map(user, EmployeeEntity.class));
		rolesRepository.save(mapper.map(user, RoleEntity.class));
	}

}
