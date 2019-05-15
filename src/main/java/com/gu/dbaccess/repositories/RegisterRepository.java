package com.gu.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.RegisterEntity;

public interface RegisterRepository extends CrudRepository<RegisterEntity, Long> {

	public List<RegisterEntity> findByDateBetweenAndActive(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until, Boolean active);

	public RegisterEntity findByDateAndEmployee(@Temporal(TemporalType.DATE) Date dateFormated,
			EmployeeEntity employee);
}
