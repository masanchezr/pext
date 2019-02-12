package com.gu.services.payments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.PaymentEntity;
import com.gu.dbaccess.repositories.PaymentRepository;

/**
 * The Class PaymentServiceImpl.
 */
public class PaymentServiceImpl implements PaymentService {

	/** The payment repository. */
	@Autowired
	private PaymentRepository paymentRepository;

	/**
	 * Find all.
	 * 
	 * @return the list
	 */
	public Iterable<PaymentEntity> findAll() {
		return paymentRepository.findAll();
	}

	public List<PaymentEntity> findAllActive() {
		return paymentRepository.findByActive(true);
	}

	public List<PaymentEntity> findAllActiveFalse() {
		return paymentRepository.findByActiveOrderByName(false);
	}

}
