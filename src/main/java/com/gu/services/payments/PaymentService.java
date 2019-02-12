package com.gu.services.payments;

import java.util.List;

import com.gu.dbaccess.entities.PaymentEntity;

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
	Iterable<PaymentEntity> findAll();

	public List<PaymentEntity> findAllActiveFalse();
}
