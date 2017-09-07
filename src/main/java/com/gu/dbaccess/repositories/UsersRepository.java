package com.gu.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.UserEntity;

public interface UsersRepository extends CrudRepository<UserEntity, String> {

}
