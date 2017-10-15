package com.gu.services.safe;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.dbaccess.entities.SafeEntity;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.ChangeMachineTotalRepository;
import com.gu.dbaccess.repositories.SafeRepository;
import com.gu.util.date.DateUtil;

public class SafeServiceImpl implements SafeService {

	@Autowired
	private SafeRepository safeRepository;

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

	@Autowired
	private ChangeMachineTotalRepository changemachinetotalrepository;

	public BigDecimal saveAdd(SafeEntity safe) {
		BigDecimal total = safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
		safe.setCreationdate(new Date());
		safe.setTotal(total.add(safe.getAmount()));
		return safeRepository.save(safe).getTotal();
	}

	public BigDecimal searchTotal() {
		return safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
	}

	/**
	 * Entrada a máquina de cambio
	 */
	public BigDecimal saveSub(SafeEntity safe) {
		BigDecimal amount = safe.getAmount();
		BigDecimal totalsafe = safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
		BigDecimal changemachinetotal = changemachinetotalrepository.findFirstByOrderByIdchangemachinetotalDesc()
				.getTotal();
		ChangeMachineEntity entity = new ChangeMachineEntity();
		ChangeMachineTotalEntity totalcm = new ChangeMachineTotalEntity();
		Long id = changeMachineRepository.findFirstByAwardIsNullAndMachineIsNullOrderByIdchangemachineDesc()
				.getIdchangemachine();
		Date today = new Date();
		totalcm.setCreationdate(today);
		totalcm.setTotal(amount.add(changemachinetotal));
		entity.setCreationdate(today);
		entity.setAmount(amount);
		entity.setIdchangemachine(id + 1);
		safe.setCreationdate(today);
		safe.setTotal(totalsafe.subtract(amount));
		safe.setAmount(amount.negate());
		changeMachineRepository.save(entity);
		changemachinetotalrepository.save(totalcm);
		return safeRepository.save(safe).getTotal();
	}

	public List<SafeEntity> searchByDates(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		return safeRepository.findByCreationdateBetween(from, until);
	}
}
