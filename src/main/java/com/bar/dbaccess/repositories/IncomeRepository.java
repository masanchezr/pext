package com.bar.dbaccess.repositories;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bar.dbaccess.entities.IncomeEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface IncomeRepository.
 */
public interface IncomeRepository extends CrudRepository<IncomeEntity, Long> {

	/**
	 * Select by creationdate.
	 *
	 * @param creationdate the creationdate
	 * @return the list
	 */
	@Query("select o from IncomeEntity o where DATE(o.creationdate)=:cdate")
	public List<IncomeEntity> selectByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	/**
	 * Search sum by month.
	 *
	 * @param from the from
	 * @param until the until
	 * @return the big decimal
	 */
	@Query("select sum(o.amount) from IncomeEntity o where o.creationdate>=:from and o.creationdate<=:until")
	public BigDecimal searchSumByMonth(@Param("from") @Temporal(TemporalType.DATE) Date from,
			@Param("until") @Temporal(TemporalType.DATE) Date until);
}
