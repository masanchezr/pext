package com.gu.services.entrymoney;

import java.math.BigDecimal;
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
		if (entryMoneyForm.getOrigin().equals(Constants.CAJAFUERTE)) {
			SafeEntity safe = safeRepository.findFirstByOrderByIdsafeDesc();
			safe.setIdsafe(null);
			safe.setTotal(safe.getTotal().subtract(amount));
			safe.setAmount(amount.negate());
			safe.setCreationdate(new Date());
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
		entryMoneyRepository.save(entrymoney);
		machine.setAmount(amount);
		machine.setCreationdate(new Date());
		machinetotal.setCreationdate(new Date());
		machinetotal.setTotal(changemachinetotalrepository.findFirstByOrderByIdchangemachinetotalDesc().getTotal()
				.add(entryMoney.getAmount()));
		changemachinerepository.save(machine);
		changemachinetotalrepository.save(machinetotal);
		return dailyService.getDailyEmployee(new Date());
	}
}
