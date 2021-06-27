package com.sboot.golden.services.payments;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.PaymentEntity;
import com.sboot.golden.dbaccess.repositories.PaymentRepository;

/**
 * The Class PaymentServiceImpl.
 */
@Service
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
