package com.gu.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gu.dbaccess.entities.AwardsChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineEntity;

public interface ChangeMachineRepository extends CrudRepository<ChangeMachineEntity, Long> {

	@Query("select sum(c.amount) from ChangeMachineEntity c where c.creationdate>=:from and c.creationdate<=:until and c.amount>0 and c.award is null and c.machine is null")
	public BigDecimal sumIncomeBetweenDates(@Param("from") Date from, @Param("until") Date until);

	@Query("select sum(c.amount) from ChangeMachineEntity c where c.creationdate>=:from and c.creationdate<=:until and c.award is not null and c.machine is not null")
	public BigDecimal sumByCreationdateBetween(@Param("from") Date from, @Param("until") Date until);

	public ChangeMachineEntity findFirstByAwardIsNullAndMachineIsNullOrderByIdchangemachineDesc();

	public List<ChangeMachineEntity> findByCreationdate(Date date);

	@Query("select o from ChangeMachineEntity o where DATE(o.creationdate)=:cdate and o.award is not null and o.machine is not null order by o.creationdate")
	public List<ChangeMachineEntity> searchByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date date);

	/**
	 * Recupera las operaciones de máquina de cambio para mostrarlas a los empleados
	 * 
	 * @param from
	 * @param until
	 * @return List<ChangeMachineEntity>
	 */
	public List<ChangeMachineEntity> findByAwardIsNotNullAndMachineIsNotNullAndCreationdateBetweenOrderByCreationdate(
			Date from, Date until);

	public ChangeMachineEntity findFirstByOrderByCreationdateDesc();

	public ChangeMachineEntity findFirstByCreationdateAfter(Date takedate);

	public List<ChangeMachineEntity> findByCreationdateBetween(Date takedate, Date date);

	public List<ChangeMachineEntity> findByAwardAndCreationdateBetween(AwardsChangeMachineEntity award,
			@Temporal(TemporalType.DATE) Date from, @Temporal(TemporalType.DATE) Date until);
}