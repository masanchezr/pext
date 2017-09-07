package com.gu.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.OperationEntity;

public interface ChangeMachineRepository extends CrudRepository<ChangeMachineEntity, Long> {

	public List<ChangeMachineEntity> findByCreationdateBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);

	@Query("select sum(c.amount) from ChangeMachineEntity c where c.creationdate>=:from and c.creationdate<=:until and c.operation is null and c.amount<0")
	public BigDecimal sumByCreationdateBetweenLuckia(@Param("from") Date from, @Param("until") Date until);

	@Query("select sum(c.amount) from ChangeMachineEntity c where c.creationdate>=:from and c.creationdate<=:until and c.amount>0")
	public BigDecimal sumBetweenDates(@Param("from") Date from, @Param("until") Date until);

	public ChangeMachineEntity findByOperation(OperationEntity entityoperation);

	@Query("select sum(c.amount) from ChangeMachineEntity c where c.creationdate>=:from and c.creationdate<=:until and c.operation is not null")
	public BigDecimal sumByCreationdateBetween(@Param("from") Date from, @Param("until") Date until);

	public ChangeMachineEntity findFirstByOperationIsNullAndAmountLessThanOrderByIdchangemachineDesc(BigDecimal amount);
}