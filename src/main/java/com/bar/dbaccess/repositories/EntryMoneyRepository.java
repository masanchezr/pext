package com.bar.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.bar.dbaccess.entities.EntryMoneyEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface EntryMoneyRepository.
 */
public interface EntryMoneyRepository extends CrudRepository<EntryMoneyEntity, Long> {

	/**
	 * Select by creationdate.
	 *
	 * @param creationdate
	 *            the creationdate
	 * @return the list
	 */
	@Query("select o from EntryMoneyEntity o where DATE(o.creationdate)=:cdate")
	public List<EntryMoneyEntity> selectByCreationdate(@Param("cdate") @Temporal(TemporalType.DATE) Date creationdate);

	/**
	 * Find by creationdate between.
	 *
	 * @param from
	 *            the from
	 * @param until
	 *            the until
	 * @return the list
	 */
	public List<EntryMoneyEntity> findByCreationdateBetween(@Temporal(TemporalType.TIME) Date from,
			@Temporal(TemporalType.TIME) Date until);
}
