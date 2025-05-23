package com.atmj.gsboot.services.entrymoney;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.atmj.gsboot.admin.forms.EntryMoneyForm;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineTotalEntity;
import com.atmj.gsboot.dbaccess.entities.EntryMoneyEntity;
import com.atmj.gsboot.dbaccess.entities.SafeEntity;
import com.atmj.gsboot.dbaccess.repositories.ChangeMachineRepository;
import com.atmj.gsboot.dbaccess.repositories.ChangeMachineTotalRepository;
import com.atmj.gsboot.dbaccess.repositories.EntryMoneyRepository;
import com.atmj.gsboot.dbaccess.repositories.SafeRepository;
import com.atmj.gsboot.util.date.DateUtil;

@Service
public class EntryMoneyServiceImpl implements EntryMoneyService {

	private EntryMoneyRepository entryMoneyRepository;

	private SafeRepository safeRepository;

	private ChangeMachineRepository changemachinerepository;

	private ChangeMachineTotalRepository changemachinetotalrepository;

	public EntryMoneyServiceImpl(EntryMoneyRepository entryMoneyRepository, SafeRepository safeRepository,
			ChangeMachineRepository changemachinerepository,
			ChangeMachineTotalRepository changemachinetotalrepository) {
		this.entryMoneyRepository = entryMoneyRepository;
		this.safeRepository = safeRepository;
		this.changemachinerepository = changemachinerepository;
		this.changemachinetotalrepository = changemachinetotalrepository;
	}

	public void saveEntryMoney(EntryMoneyForm entryMoneyForm) {
		saveEntryMoneyEmployee(entryMoneyForm);
		SafeEntity safe = safeRepository.findFirstByOrderByIdsafeDesc();
		SafeEntity newsafe = new SafeEntity();
		BigDecimal amount = entryMoneyForm.getAmount();
		newsafe.setTotal(safe.getTotal().subtract(amount));
		newsafe.setAmount(amount.negate());
		newsafe.setCreationdate(new DateUtil().getNow());
		newsafe.setDescription("CAJA COMÚN ".concat(entryMoneyForm.getOrigin()));
		safeRepository.save(newsafe);
	}

	public void saveEntryMoneyEmployee(EntryMoneyForm entryMoneyForm) {
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		BigDecimal amount = entryMoneyForm.getAmount();
		entrymoney.setAmount(amount);
		entrymoney.setCreationdate(new DateUtil().getNow());
		entrymoney.setDescription("CAJA FUERTE ".concat(entryMoneyForm.getOrigin()));
		entryMoneyRepository.save(entrymoney);
	}

	public List<EntryMoneyEntity> findByDates(Date from, Date until) {
		return entryMoneyRepository.findByCreationdateBetween(from, until);
	}

	public BigDecimal saveAdd(SafeEntity safe) {
		BigDecimal total = safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
		safe.setCreationdate(new DateUtil().getNow());
		safe.setTotal(total.add(safe.getAmount()));
		return safeRepository.save(safe).getTotal();
	}

	public BigDecimal searchTotalSafe() {
		return safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
	}

	/**
	 * Entrada a depósito de máquina de cambio
	 */
	public BigDecimal saveAddChangeMachine(SafeEntity safe) {
		BigDecimal amount = safe.getAmount();
		BigDecimal totalsafe = safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
		ChangeMachineTotalEntity changemachinetotal = changemachinetotalrepository
				.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineEntity entity = new ChangeMachineEntity();
		ChangeMachineTotalEntity totalcm = new ChangeMachineTotalEntity();
		Long id = changemachinerepository.findFirstByAwardIsNullAndMachineIsNullOrderByIdchangemachineDesc()
				.getIdchangemachine();
		Date today = new DateUtil().getNow();
		totalcm.setDeposit(amount.add(changemachinetotal.getDeposit()));
		totalcm.setCreationdate(today);
		totalcm.setVisible(changemachinetotal.getVisible());
		entity.setCreationdate(today);
		entity.setAmount(amount);
		entity.setIdchangemachine(id + 1);
		safe.setCreationdate(today);
		safe.setTotal(totalsafe.subtract(amount));
		safe.setAmount(amount.negate());
		safe.setDescription("CHANGEMACHINE");
		changemachinerepository.save(entity);
		changemachinetotalrepository.save(totalcm);
		return safeRepository.save(safe).getTotal();
	}

	/**
	 * Entrada directa a máquina de cambio
	 */
	public BigDecimal saveSubDirect(SafeEntity safe) {
		BigDecimal amount = safe.getAmount();
		BigDecimal totalsafe = safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
		ChangeMachineTotalEntity changemachinetotal = changemachinetotalrepository
				.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineEntity entity = new ChangeMachineEntity();
		ChangeMachineTotalEntity totalcm = new ChangeMachineTotalEntity();
		Long id = changemachinerepository.findFirstByAwardIsNullAndMachineIsNullOrderByIdchangemachineDesc()
				.getIdchangemachine();
		Date today = new DateUtil().getNow();
		totalcm.setCreationdate(today);
		totalcm.setVisible(amount.add(changemachinetotal.getVisible()));
		totalcm.setDeposit(changemachinetotal.getDeposit());
		entity.setCreationdate(today);
		entity.setAmount(amount);
		entity.setIdchangemachine(id + 1);
		safe.setCreationdate(today);
		safe.setTotal(totalsafe.subtract(amount));
		safe.setAmount(amount.negate());
		safe.setDescription("CHANGEMACHINE");
		changemachinerepository.save(entity);
		changemachinetotalrepository.save(totalcm);
		return safeRepository.save(safe).getTotal();
	}

	public List<SafeEntity> searchByDates(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from;
		Date until;
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		until = calendar.getTime();
		return safeRepository.findByCreationdateBetween(from, until);
	}

	@Override
	public BigDecimal saveSub(SafeEntity safe) {
		BigDecimal amount = safe.getAmount();
		BigDecimal totalsafe = safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
		safe.setTotal(totalsafe.subtract(amount));
		safe.setAmount(amount.negate());
		safe.setCreationdate(new Date());
		return safeRepository.save(safe).getTotal();
	}

	@Override
	public List<SafeEntity> searchToday() {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		Date from;
		Date until;
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 1);
		from = calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		until = calendar.getTime();
		return safeRepository.findByCreationdateBetween(from, until);
	}
}
