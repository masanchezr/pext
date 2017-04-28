package com.bar.services.payments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bar.dbaccess.entities.PaymentEntity;
import com.bar.dbaccess.repositories.PaymentRepository;

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
	public List<PaymentEntity> findAll() {
		return (List<PaymentEntity>) paymentRepository.findAll();
	}

	public List<PaymentEntity> findAllActive() {
		return (List<PaymentEntity>) paymentRepository.findByActive(true);
	}

	public List<PaymentEntity> findAllActiveFalse() {
		return (List<PaymentEntity>) paymentRepository.findByActiveOrderByName(false);
	}

}
