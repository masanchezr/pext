package com.bar.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bar.dbaccess.entities.PaymentEntity;

// TODO: Auto-generated Javadoc
/**
 * The Interface PaymentRepository.
 */
public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {

	/**
	 * Find by active.
	 *
	 * @param b
	 *            the b
	 * @return the iterable
	 */
	public List<PaymentEntity> findByActiveOrderByName(boolean b);

	/**
	 * Find by active.
	 *
	 * @param b the b
	 * @return the list
	 */
	public List<PaymentEntity> findByActive(boolean b);
}
