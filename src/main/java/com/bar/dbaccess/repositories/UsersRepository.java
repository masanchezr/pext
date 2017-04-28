package com.bar.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.bar.dbaccess.entities.UserEntity;

/**
 * The Interface UsersRepository.
 */
public interface UsersRepository extends CrudRepository<UserEntity, String> {

}
