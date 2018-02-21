package com.gu.services.dailies;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.BarDrinkEntity;
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.DailyEntity;
import com.gu.dbaccess.entities.EntryMoneyEntity;
import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.dbaccess.entities.IncomeLuckiaEntity;
import com.gu.dbaccess.entities.IncomeMachineEntity;
import com.gu.dbaccess.entities.OperationEntity;
import com.gu.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.gu.dbaccess.entities.TPVEntity;
import com.gu.dbaccess.repositories.BarDrinksRepository;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.DailyRepository;
import com.gu.dbaccess.repositories.EntryMoneyRepository;
import com.gu.dbaccess.repositories.GratificationsRepository;
import com.gu.dbaccess.repositories.IncomeLuckiaRepository;
import com.gu.dbaccess.repositories.IncomeMachinesRepository;
import com.gu.dbaccess.repositories.OperationsRepository;
import com.gu.dbaccess.repositories.ReturnMoneyEmployeesRepository;
import com.gu.dbaccess.repositories.TPVRepository;
import com.gu.services.changemachine.ChangeMachineService;
import com.gu.services.changemachine.TicketServer;
import com.gu.util.constants.Constants;

/**
 * The Class DailyServiceImpl.
 */
public class DailyServiceImpl implements DailyService {

	@Autowired
	private OperationsRepository operationsRepository;

	/** The daily repository. */
	@Autowired
	private DailyRepository dailyRepository;

	@Autowired
	private EntryMoneyRepository entryMoneyRepository;

	@Autowired
	private BarDrinksRepository incomeRepository;

	@Autowired
	private IncomeLuckiaRepository incomeLuckiaRepository;

	@Autowired
	private IncomeMachinesRepository incomemachinesRepository;

	@Autowired
	private ReturnMoneyEmployeesRepository returnMoneyEmployeesRepository;

	@Autowired
	private GratificationsRepository gratificationRepository;

	@Autowired
	private TPVRepository tpvrepository;

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

	@Autowired
	private ChangeMachineService changeMachineService;

	public Daily getDaily(Date date) {
		TicketServer ticketserver = new TicketServer(changeMachineService);
		Daily daily = new Daily();
		List<OperationEntity> operations = operationsRepository.findByCreationdate(date);
		List<EntryMoneyEntity> entriesMoney = entryMoneyRepository.findByCreationdate(date);
		List<BarDrinkEntity> income = incomeRepository.findByCreationdate(date);
		List<IncomeLuckiaEntity> incomeluckia = incomeLuckiaRepository.findByCreationdate(date);
		List<IncomeMachineEntity> incomemachines = incomemachinesRepository.findByCreationdate(date);
		List<GratificationEntity> gratifications = gratificationRepository.searchByPaydate(date);
		List<ReturnMoneyEmployeeEntity> returns = returnMoneyEmployeesRepository.findByReturndate(date);
		List<ReturnMoneyEmployeeEntity> moneyadvance = returnMoneyEmployeesRepository.findByCreationdate(date);
		List<TPVEntity> tpvs = tpvrepository.findByCreationdate(date);
		List<ChangeMachineEntity> changemachine = changeMachineRepository.searchByCreationdate(date);
		int numoperations = 0;
		BigDecimal finalamount = BigDecimal.ZERO;
		// busco el parte de hoy si ya está calculado
		DailyEntity dEntity = dailyRepository.findById(date).orElse(null);
		if (operations != null && !operations.isEmpty()) {
			Iterator<OperationEntity> ioperations = operations.iterator();
			OperationEntity operation;
			while (ioperations.hasNext()) {
				operation = ioperations.next();
				numoperations = numoperations + 1;
				if (!operation.getPay().getIdpayment().equals(Constants.PROVIDING)) {
					finalamount = finalamount.subtract(operation.getAmount());
				}
			}
			daily.setOperations(operations);
		}
		if (gratifications != null && !gratifications.isEmpty()) {
			BigDecimal ten = new BigDecimal(10);
			for (int i = 0; i < gratifications.size(); i++) {
				finalamount = finalamount.subtract(ten);
				numoperations = numoperations + 1;
			}
			daily.setGratifications(gratifications);
		}
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
			Iterator<BarDrinkEntity> iincome = income.iterator();
			BarDrinkEntity ie;
			while (iincome.hasNext()) {
				ie = iincome.next();
				finalamount = finalamount.add(ie.getAmount());
				numoperations = numoperations + 1;
			}
			daily.setIncome(income);
		}

		if (incomeluckia != null && !incomeluckia.isEmpty()) {
			Iterator<IncomeLuckiaEntity> iincome = incomeluckia.iterator();
			IncomeLuckiaEntity ie;
			while (iincome.hasNext()) {
				ie = iincome.next();
				finalamount = finalamount.add(ie.getAmount());
				numoperations = numoperations + 1;
			}
			daily.setIncomeluckia(incomeluckia);
		}
		if (incomemachines != null && !incomemachines.isEmpty()) {
			Iterator<IncomeMachineEntity> iincome = incomemachines.iterator();
			IncomeMachineEntity ie;
			while (iincome.hasNext()) {
				ie = iincome.next();
				finalamount = finalamount.add(ie.getAmount());
				numoperations = numoperations + 1;
			}
			daily.setIncomemachines(incomemachines);
		}
		if (returns != null && !returns.isEmpty()) {
			Iterator<ReturnMoneyEmployeeEntity> iincome = returns.iterator();
			ReturnMoneyEmployeeEntity ie;
			while (iincome.hasNext()) {
				ie = iincome.next();
				finalamount = finalamount.add(ie.getAmount());
				numoperations = numoperations + 1;
			}
			daily.setReturns(returns);
		}
		if (moneyadvance != null && !moneyadvance.isEmpty()) {
			Iterator<ReturnMoneyEmployeeEntity> iincome = moneyadvance.iterator();
			ReturnMoneyEmployeeEntity ie;
			while (iincome.hasNext()) {
				ie = iincome.next();
				finalamount = finalamount.subtract(ie.getAmount());
				numoperations = numoperations + 1;
			}
			daily.setMoneyAdvance(moneyadvance);
		}
		if (tpvs != null && !tpvs.isEmpty()) {
			Iterator<TPVEntity> itpvs = tpvs.iterator();
			TPVEntity tpv;
			while (itpvs.hasNext()) {
				tpv = itpvs.next();
				if (tpv.getPay().getIdpayment().equals(Constants.CAJACOMUN)) {
					finalamount = finalamount.subtract(tpv.getAmount());
				}
				numoperations = numoperations + 1;
			}
			daily.setTpvs(tpvs);
		}
		if (changemachine != null && !changemachine.isEmpty()) {
			numoperations = numoperations + changemachine.size();
			daily.setListchangemachine(changemachine);
		}
		BigDecimal previousamount;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		DailyEntity previousdaily = dailyRepository.findById(calendar.getTime()).orElse(null);
		if (dEntity == null && previousdaily != null) {
			// tengo que sacar el importe del día anterior para calcularlo
			previousamount = previousdaily.getFinalamount();
			dEntity = new DailyEntity();
			dEntity.setDailydate(date);
		} else if (previousdaily == null) {
			return daily;
		} else {
			previousamount = previousdaily.getFinalamount();
		}
		finalamount = previousamount.add(finalamount);
		dEntity.setFinalamount(finalamount);
		daily.setFinalamount(finalamount);
		daily.setNumoperations(numoperations);
		daily.setDate(date);
		dailyRepository.save(dEntity);
		ticketserver.start();
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
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 9);
		date = calendar.getTime();
		List<OperationEntity> operations = operationsRepository.findByCreationdateBetween(date, new Date());
		List<EntryMoneyEntity> entriesMoney = entryMoneyRepository.findByCreationdateBetween(date, new Date());
		List<BarDrinkEntity> income = incomeRepository.findByCreationdateBetween(date, new Date());
		List<IncomeLuckiaEntity> incomeluckia = incomeLuckiaRepository.findByCreationdateBetween(date, new Date());
		List<IncomeMachineEntity> incomemachines = incomemachinesRepository.findByCreationdateBetween(date, new Date());
		List<GratificationEntity> gratifications = gratificationRepository.findByPaydateBetween(date, new Date());
		List<ReturnMoneyEmployeeEntity> returns = returnMoneyEmployeesRepository.findByReturndateBetween(date,
				new Date());
		List<ReturnMoneyEmployeeEntity> moneyadvance = returnMoneyEmployeesRepository.findByCreationdateBetween(date,
				new Date());
		List<TPVEntity> tpvs = tpvrepository.findByCreationdateBetween(date, new Date());
		List<ChangeMachineEntity> changemachine = changeMachineRepository
				.findByCreationdateBetweenOrderByCreationdate(date, new Date());
		daily.setOperations(operations);
		daily.setEntriesMoney(entriesMoney);
		daily.setIncome(income);
		daily.setIncomeluckia(incomeluckia);
		daily.setIncomemachines(incomemachines);
		daily.setGratifications(gratifications);
		daily.setReturns(returns);
		daily.setTpvs(tpvs);
		daily.setListchangemachine(changemachine);
		daily.setMoneyadvance(moneyadvance);
		return daily;
	}
}
