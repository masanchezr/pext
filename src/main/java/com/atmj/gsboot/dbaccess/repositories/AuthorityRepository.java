package com.atmj.gsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.dbaccess.entities.AuthorityEntity;

public interface AuthorityRepository extends CrudRepository<AuthorityEntity, Long> {

	AuthorityEntity findByAuthority(String authority);

}
