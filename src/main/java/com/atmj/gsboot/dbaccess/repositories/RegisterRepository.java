package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.UserEntity;
import com.atmj.gsboot.dbaccess.entities.RegisterEntity;

public interface RegisterRepository extends CrudRepository<RegisterEntity, Long> {

	public List<RegisterEntity> findByCreationdateBetweenAndActiveTrue(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);

	public RegisterEntity findByCreationdateAndEmployee(@Temporal(TemporalType.DATE) Date dateFormated,
			UserEntity employee);
}
