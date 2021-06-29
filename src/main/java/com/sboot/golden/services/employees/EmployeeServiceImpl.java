package com.sboot.golden.services.employees;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.repositories.UsersRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private UsersRepository employeesRepository;

	public List<UserEntity> allEmployeesActives() {
		return employeesRepository.findByEnabledTrue();
	}

	public UserEntity getEmployeeByUserName(String user) {
		return employeesRepository.findByUsername(user);
	}
}
