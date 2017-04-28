package com.bar.services.payments;

import java.util.List;

import com.bar.dbaccess.entities.PaymentEntity;

/**
 * The Interface PaymentService.
 */
public interface PaymentService {

	/**
	 * Find all active.
	 *
	 * @return the list
	 */
	public List<PaymentEntity> findAllActive();

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	List<PaymentEntity> findAll();

	public List<PaymentEntity> findAllActiveFalse();
}
