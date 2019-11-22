package com.gu.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.EmployeeEntity;
import com.gu.dbaccess.entities.FicticionalRegisterEntity;

public interface FicticionalRegisterRepository extends CrudRepository<FicticionalRegisterEntity, Long> {

	public List<FicticionalRegisterEntity> findByCreationdateBetweenOrderByCreationdate(
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until);

	public FicticionalRegisterEntity findByCreationdateAndEmployee(@Temporal(TemporalType.DATE) Date date,
			EmployeeEntity employee);

}
