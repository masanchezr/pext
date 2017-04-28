package com.bar.services.dailies;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bar.dbaccess.entities.DailyEntity;
import com.bar.dbaccess.entities.EntryMoneyEntity;
import com.bar.dbaccess.entities.IncomeEntity;
import com.bar.dbaccess.repositories.DailyRepository;
import com.bar.dbaccess.repositories.EntryMoneyRepository;
import com.bar.dbaccess.repositories.IncomeRepository;

/**
 * The Class DailyServiceImpl.
 */
public class DailyServiceImpl implements DailyService {

	/** The daily repository. */
	@Autowired
	private DailyRepository dailyRepository;

	@Autowired
	private EntryMoneyRepository entryMoneyRepository;

	@Autowired
	private IncomeRepository incomeRepository;

	/**
	 * The otherconceptsrepository.
	 * 
	 * @Autowired private OtherConceptsRepository otherconceptsrepository;
	 */

	public Daily getDaily(Date date) {
		Daily daily = new Daily();
		List<EntryMoneyEntity> entriesMoney = entryMoneyRepository.selectByCreationdate(date);
		List<IncomeEntity> income = incomeRepository.selectByCreationdate(date);
		// List<OtherConceptEntity> otherconcepts =
		// otherconceptsrepository.findByCreationdate(date);
		int numoperations = 0;
		BigDecimal finalamount = BigDecimal.ZERO;
		// busco el parte de hoy si ya está calculado
		DailyEntity dEntity = dailyRepository.findByDailydate(date);
		if (entriesMoney != null && !entriesMoney.isEmpty()) {
			Iterator<EntryMoneyEntity> ientriesmoney = entriesMoney.iterator();
			EntryMoneyEntity eme;
			while (ientriesmoney.hasNext()) {
				eme = ientriesmoney.next();
				finalamount = finalamount.add(eme.getAmount());
				numoperations = numoperations + 1;
			}
			daily.setEntriesMoney(entriesMoney);
		}
		if (income != null && !income.isEmpty()) {
			Iterator<IncomeEntity> iincome = income.iterator();
			IncomeEntity ie;
			while (iincome.hasNext()) {
				ie = iincome.next();
				finalamount = finalamount.add(ie.getAmount());
				numoperations = numoperations + 1;
			}
			daily.setIncome(income);
		}
		/**
		 * if (otherconcepts != null && !otherconcepts.isEmpty()) {
		 * Iterator<OtherConceptEntity> iotherconcepts =
		 * otherconcepts.iterator(); List<OtherConceptEntity> lotherconcepts =
		 * new ArrayList<OtherConceptEntity>(); OtherConceptEntity otherconcept;
		 * while (iotherconcepts.hasNext()) { otherconcept =
		 * iotherconcepts.next(); lotherconcepts.add(otherconcept);
		 * otherconceptsamount =
		 * otherconceptsamount.add(otherconcept.getAmount()); numoperations =
		 * numoperations + 1; } daily.setOthersconcepts(lotherconcepts); }
		 */
		BigDecimal previousamount = BigDecimal.ZERO;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		if (dEntity == null) {
			// tengo que sacar el importe del día anterior para calcularlo
			DailyEntity previousdaily = dailyRepository.findByDailydate(calendar.getTime());
			previousamount = previousdaily.getFinalamount();
			dEntity = new DailyEntity();
			dEntity.setDailydate(date);
		} else {
			DailyEntity dailyprevious = dailyRepository.findByDailydate(calendar.getTime());
			if (dailyprevious == null) {
				return daily;
			} else {
				previousamount = dailyprevious.getFinalamount();
			}
		}
		finalamount = previousamount.add(finalamount);
		dEntity.setFinalamount(finalamount);
		daily.setFinalamount(finalamount);
		daily.setNumoperations(numoperations);
		daily.setDate(date);
		dailyRepository.save(dEntity);
		return daily;
	}

	public void calculateDailies(Date date) {
		Calendar calendar = Calendar.getInstance();
		while (date.compareTo(new Date()) < 0) {
			getDaily(date);
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			date = calendar.getTime();
		}
	}

	public Daily getDailyEmployee(Date date) {
		Daily daily = getDaily(date);
		List<EntryMoneyEntity> entriesMoney = daily.getEntriesMoney();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 9);
		date = calendar.getTime();
		if (entriesMoney != null && !entriesMoney.isEmpty()) {
			Iterator<EntryMoneyEntity> ientriesMoney = entriesMoney.iterator();
			EntryMoneyEntity entrymoney;
			entriesMoney = new ArrayList<EntryMoneyEntity>();
			while (ientriesMoney.hasNext()) {
				entrymoney = ientriesMoney.next();
				if (entrymoney.getCreationdate().after(date)) {
					entriesMoney.add(entrymoney);
				}
			}
			daily.setEntriesMoney(entriesMoney);
		}
		return daily;
	}
}
