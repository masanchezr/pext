package com.sboot.golden.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	public UserEntity findByUsername(String user);

	public List<UserEntity> findByEnabledTrue();
}
