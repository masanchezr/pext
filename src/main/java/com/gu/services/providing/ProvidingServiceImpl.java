package com.gu.services.providing;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.ProvidingEntity;
import com.gu.dbaccess.entities.SafeEntity;
import com.gu.dbaccess.repositories.ProvidingRepository;
import com.gu.dbaccess.repositories.SafeRepository;

public class ProvidingServiceImpl implements ProvidingService {

	@Autowired
	private ProvidingRepository providingRepository;

	@Autowired
	private SafeRepository saferepository;

	/**
	 * El dinero procederá de la caja fuerte
	 * 
	 * @param providing
	 */
	public void save(ProvidingEntity providing) {
		ProvidingEntity entity = providingRepository.findFirstByOrderByIdprovidingDesc();
		SafeEntity safe = saferepository.findFirstByOrderByIdsafeDesc(), newsafe = new SafeEntity();
		BigDecimal amount = providing.getAmount();
		providing.setCreationdate(new Date());
		providing.setTotal(entity.getTotal().add(providing.getAmount()));
		newsafe.setAmount(amount.negate());
		newsafe.setTotal(safe.getTotal().subtract(amount));
		newsafe.setCreationdate(new Date());
		providingRepository.save(providing);
		saferepository.save(newsafe);
	}

	public BigDecimal searchTotal() {
		return providingRepository.findFirstByOrderByIdprovidingDesc().getTotal();
	}

}
