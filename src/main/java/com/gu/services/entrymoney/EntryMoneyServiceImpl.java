package com.gu.services.entrymoney;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.dbaccess.entities.EntryMoneyEntity;
import com.gu.dbaccess.entities.ProvidingEntity;
import com.gu.dbaccess.entities.SafeEntity;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.ChangeMachineTotalRepository;
import com.gu.dbaccess.repositories.EntryMoneyRepository;
import com.gu.dbaccess.repositories.ProvidingRepository;
import com.gu.dbaccess.repositories.SafeRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.Constants;
import com.gu.util.date.DateUtil;

public class EntryMoneyServiceImpl implements EntryMoneyService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private EntryMoneyRepository entryMoneyRepository;

	@Autowired
	private SafeRepository safeRepository;

	@Autowired
	private ProvidingRepository providingRepository;

	@Autowired
	private ChangeMachineRepository changemachinerepository;

	@Autowired
	private ChangeMachineTotalRepository changemachinetotalrepository;

	public Daily saveEntryMoney(EntryMoneyForm entryMoneyForm) {
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		BigDecimal amount = entryMoneyForm.getAmount();
		entrymoney.setAmount(amount);
		entrymoney.setCreationdate(new Date());
		entryMoneyRepository.save(entrymoney);
		if (entryMoneyForm.getOrigin().equals(Constants.getOrigin()[0])) {
			SafeEntity safe = safeRepository.findFirstByOrderByIdsafeDesc();
			safe.setIdsafe(null);
			safe.setTotal(safe.getTotal().subtract(amount));
			safe.setAmount(amount.negate());
			safe.setCreationdate(new Date());
			safe.setDescription("CAJA COMÚN");
			safeRepository.save(safe);
		}
		return dailyService.getDaily(new Date());
	}

	public Daily saveEntryMoneyEmployee(EntryMoneyForm entryMoneyForm) {
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		BigDecimal amount = entryMoneyForm.getAmount();
		entrymoney.setAmount(amount);
		entrymoney.setCreationdate(new Date());
		entryMoneyRepository.save(entrymoney);
		ProvidingEntity providing = providingRepository.findFirstByOrderByIdprovidingDesc();
		providing.setIdproviding(null);
		providing.setTotal(providing.getTotal().subtract(amount));
		providing.setAmount(amount.negate());
		providing.setCreationdate(new Date());
		providingRepository.save(providing);
		return dailyService.getDailyEmployee(new Date());
	}

	public List<EntryMoneyEntity> findByDates(Date from, Date until) {
		return entryMoneyRepository.findByCreationdateBetween(from, until);
	}

	public Daily saveEntryMachine(EntryMoneyForm entryMoney) {
		ChangeMachineTotalEntity machinetotal = new ChangeMachineTotalEntity();
		ChangeMachineEntity machine = new ChangeMachineEntity();
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		BigDecimal amount = entryMoney.getAmount();
		entrymoney.setAmount(amount.negate());
		entrymoney.setCreationdate(new Date());
		entrymoney.setDescription("Máquina de cambio");
		entryMoneyRepository.save(entrymoney);
		machine.setAmount(amount);
		machine.setCreationdate(new Date());
		machine.setIdchangemachine(changemachinerepository
				.findFirstByAwardIsNullAndMachineIsNullOrderByIdchangemachineDesc().getIdchangemachine() + 1);
		machinetotal.setCreationdate(new Date());
		machinetotal.setTotal(changemachinetotalrepository.findFirstByOrderByIdchangemachinetotalDesc().getTotal()
				.add(entryMoney.getAmount()));
		changemachinerepository.save(machine);
		changemachinetotalrepository.save(machinetotal);
		return dailyService.getDailyEmployee(new Date());
	}

	/**
	 * El dinero procederá de la caja fuerte
	 * 
	 * @param providing
	 */
	public void save(ProvidingEntity providing) {
		ProvidingEntity entity = providingRepository.findFirstByOrderByIdprovidingDesc();
		SafeEntity safe = safeRepository.findFirstByOrderByIdsafeDesc();
		SafeEntity newsafe = new SafeEntity();
		BigDecimal amount = providing.getAmount();
		providing.setCreationdate(new Date());
		providing.setTotal(entity.getTotal().add(providing.getAmount()));
		newsafe.setAmount(amount.negate());
		newsafe.setTotal(safe.getTotal().subtract(amount));
		newsafe.setCreationdate(new Date());
		newsafe.setDescription(Constants.STRINGPROVIDING);
		providingRepository.save(providing);
		safeRepository.save(newsafe);
	}

	public BigDecimal searchTotalProviding() {
		return providingRepository.findFirstByOrderByIdprovidingDesc().getTotal();
	}

	public BigDecimal saveAdd(SafeEntity safe) {
		BigDecimal total = safeRepository.findFirstByOrderByIdsafeDesc().getTotal();
		safe.setCreationdate(new Date());
		safe.setTotal(total.add(safe.getAmount()));
		return safeRepository.save(safe).getTotal();
	}

	public BigDecimal searchTotalSafe() {
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
		Long id = changemachinerepository.findFirstByAwardIsNullAndMachineIsNullOrderByIdchangemachineDesc()
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
}
