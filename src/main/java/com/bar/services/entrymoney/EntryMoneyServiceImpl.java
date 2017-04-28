package com.bar.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bar.admin.forms.EntryMoneyForm;
import com.bar.dbaccess.entities.EntryMoneyEntity;
import com.bar.dbaccess.repositories.EntryMoneyRepository;
import com.bar.services.dailies.Daily;
import com.bar.services.dailies.DailyService;

public class EntryMoneyServiceImpl implements EntryMoneyService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private EntryMoneyRepository entryMoneyRepository;

	public Daily saveEntryMoney(EntryMoneyForm entryMoneyForm) {
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		BigDecimal amount = entryMoneyForm.getAmount();
		entrymoney.setAmount(amount);
		entrymoney.setCreationdate(new Date());
		entryMoneyRepository.save(entrymoney);
		return dailyService.getDaily(new Date());
	}

	public Daily saveEntryMoneyEmployee(EntryMoneyForm entryMoneyForm) {
		EntryMoneyEntity entrymoney = new EntryMoneyEntity();
		BigDecimal amount = entryMoneyForm.getAmount();
		entrymoney.setAmount(amount);
		entrymoney.setCreationdate(new Date());
		entryMoneyRepository.save(entrymoney);
		return dailyService.getDailyEmployee(new Date());
	}

	public List<EntryMoneyEntity> findByDates(Date from, Date until) {
		return entryMoneyRepository.findByCreationdateBetween(from, until);
	}
}
