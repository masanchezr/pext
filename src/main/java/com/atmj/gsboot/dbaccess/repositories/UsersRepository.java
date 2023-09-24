package com.atmj.gsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, Long> {
	public UserEntity findByUsername(String user);

	public Iterable<UserEntity> findByEnabledTrue();
}
