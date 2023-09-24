package com.atmj.gsboot.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atmj.gsboot.dbaccess.entities.OpenMachineEntity;

public interface OpenMachinesRepository extends CrudRepository<OpenMachineEntity, Long> {

	@Query("select o from OpenMachineEntity o where DATE(o.creationdate)=:cdate")
	public List<OpenMachineEntity> findByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date date);

}
