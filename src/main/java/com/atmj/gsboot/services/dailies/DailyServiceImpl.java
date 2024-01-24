package com.atmj.gsboot.services.dailies;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.BarDrinkEntity;
import com.atmj.gsboot.dbaccess.entities.ChangeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.DailyEntity;
import com.atmj.gsboot.dbaccess.entities.EntryMoneyEntity;
import com.atmj.gsboot.dbaccess.entities.GratificationEntity;
import com.atmj.gsboot.dbaccess.entities.IncomeSportsBetsEntity;
import com.atmj.gsboot.dbaccess.entities.IncomeMachineEntity;
import com.atmj.gsboot.dbaccess.entities.OperationEntity;
import com.atmj.gsboot.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.atmj.gsboot.dbaccess.entities.TPVEntity;
import com.atmj.gsboot.dbaccess.repositories.BarDrinksRepository;
import com.atmj.gsboot.dbaccess.repositories.ChangeMachineRepository;
import com.atmj.gsboot.dbaccess.repositories.DailyRepository;
import com.atmj.gsboot.dbaccess.repositories.EntryMoneyRepository;
import com.atmj.gsboot.dbaccess.repositories.GratificationsRepository;
import com.atmj.gsboot.dbaccess.repositories.IncomeSportsBetsRepository;
import com.atmj.gsboot.dbaccess.repositories.IncomeMachinesRepository;
import com.atmj.gsboot.dbaccess.repositories.OperationsRepository;
import com.atmj.gsboot.dbaccess.repositories.ReturnMoneyEmployeesRepository;
import com.atmj.gsboot.dbaccess.repositories.TPVRepository;
import com.atmj.gsboot.services.changemachine.ChangeMachineService;
import com.atmj.gsboot.services.changemachine.TicketServer;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.date.DateUtil;

/**
 * The Class DailyServiceImpl.
 */
@Service
public class DailyServiceImpl implements DailyService {

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

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
	private IncomeSportsBetsRepository incomeSportsBetsRepository;

	@Autowired
	private IncomeMachinesRepository incomemachinesRepository;

	@Autowired
	private ReturnMoneyEmployeesRepository returnMoneyUsersRepository;

	@Autowired
	private GratificationsRepository gratificationRepository;

	@Autowired
	private TPVRepository tpvrepository;

	@Autowired
	private ChangeMachineService changeMachineService;

	public Daily getDaily(Date date) {
		TicketServer ticketserver = new TicketServer(changeMachineService);
		Daily daily = new Daily();
		List<ChangeMachineEntity> changemachine = changeMachineService.getOperationsTicketServer(date);
		// busco el parte de hoy si ya está calculado
		DailyEntity dEntity = dailyRepository.findById(date).orElse(null);
		BigDecimal finalamount;
		daily.setFinalamount(BigDecimal.ZERO);
		setOperations(daily, date);
		setGratifications(daily, date);
		setEntriesMoney(daily, date);
		setIncome(daily, date);
		setIncomeSportsBets(daily, date);
		setIncomeMachines(daily, date);
		setReturns(daily, date);
		setMoneyAdvance(daily, date);
		setTpvs(daily, date);
		if (changemachine != null && !changemachine.isEmpty()) {
			daily.setNumoperations(daily.getNumoperations() + changemachine.size());
			daily.setListchangemachine(changemachine);
		}
		DailyEntity previousdaily = null;
		Date previousday = date;
		while (previousdaily == null) {
			previousday = DateUtil.addDays(previousday, -1);
			previousdaily = dailyRepository.findById(previousday).orElse(null);
		}
		// tengo que sacar el importe del día anterior para calcularlo
		BigDecimal previousamount = previousdaily.getFinalamount();
		if (dEntity == null) {
			dEntity = new DailyEntity();
			dEntity.setDailydate(date);
		}
		finalamount = previousamount.add(daily.getFinalamount());
		dEntity.setFinalamount(finalamount);
		daily.setFinalamount(finalamount);
		daily.setDate(date);
		dailyRepository.save(dEntity);
		ticketserver.start();
		return daily;
	}

	private void setTpvs(Daily daily, Date date) {
		BigDecimal tpvsamount = BigDecimal.ZERO;
		List<TPVEntity> tpvs = tpvrepository.findByCreationdate(date);
		if (tpvs != null && !tpvs.isEmpty()) {
			Iterator<TPVEntity> itpvs = tpvs.iterator();
			TPVEntity tpv;
			while (itpvs.hasNext()) {
				tpv = itpvs.next();
				if (tpv.getPay().getIdpayment().equals(Constants.CAJACOMUN)) {
					tpvsamount = tpvsamount.add(tpv.getAmount());
				}
			}
			daily.setFinalamount(daily.getFinalamount().subtract(tpvsamount));
			daily.setNumoperations(tpvs.size() + daily.getNumoperations());
			daily.setTpvs(tpvs);
		}
	}

	private void setMoneyAdvance(Daily daily, Date date) {
		BigDecimal moneyadvanceamount = BigDecimal.ZERO;
		List<ReturnMoneyEmployeeEntity> moneyadvance = returnMoneyUsersRepository.findByCreationdate(date);
		if (moneyadvance != null && !moneyadvance.isEmpty()) {
			Iterator<ReturnMoneyEmployeeEntity> iincome = moneyadvance.iterator();
			while (iincome.hasNext()) {
				moneyadvanceamount = moneyadvanceamount.add(iincome.next().getAmount());
			}
			daily.setFinalamount(daily.getFinalamount().subtract(moneyadvanceamount));
			daily.setNumoperations(moneyadvance.size() + daily.getNumoperations());
			daily.setMoneyadvance(moneyadvance);
		}
	}

	private void setReturns(Daily daily, Date date) {
		BigDecimal returnmoneyamount = BigDecimal.ZERO;
		List<ReturnMoneyEmployeeEntity> returns = returnMoneyUsersRepository.findByReturndate(date);
		if (returns != null && !returns.isEmpty()) {
			Iterator<ReturnMoneyEmployeeEntity> iincome = returns.iterator();
			while (iincome.hasNext()) {
				returnmoneyamount = returnmoneyamount.add(iincome.next().getAmount());
			}
			daily.setFinalamount(daily.getFinalamount().add(returnmoneyamount));
			daily.setNumoperations(returns.size() + daily.getNumoperations());
			daily.setReturns(returns);
		}
	}

	private void setIncomeMachines(Daily daily, Date date) {
		BigDecimal incomemachinesamount = BigDecimal.ZERO;
		List<IncomeMachineEntity> incomemachines = incomemachinesRepository.findByCreationdate(date);
		if (incomemachines != null) {
			Iterator<IncomeMachineEntity> iincome = incomemachines.iterator();
			while (iincome.hasNext()) {
				incomemachinesamount = incomemachinesamount.add(iincome.next().getAmount());
			}
			daily.setFinalamount(daily.getFinalamount().add(incomemachinesamount));
			daily.setNumoperations(incomemachines.size() + daily.getNumoperations());
			daily.setIncomemachines(incomemachines);
		}
	}

	private void setIncomeSportsBets(Daily daily, Date date) {
		BigDecimal amountincomesportsbets = BigDecimal.ZERO;
		List<IncomeSportsBetsEntity> incomesportsbets = incomeSportsBetsRepository.findByCreationdate(date);
		if (incomesportsbets != null) {
			Iterator<IncomeSportsBetsEntity> iincome = incomesportsbets.iterator();
			while (iincome.hasNext()) {
				amountincomesportsbets = amountincomesportsbets.add(iincome.next().getAmount());
			}
			daily.setFinalamount(daily.getFinalamount().add(amountincomesportsbets));
			daily.setNumoperations(incomesportsbets.size() + daily.getNumoperations());
			daily.setIncomesportsbets(incomesportsbets);
		}
	}

	private void setIncome(Daily daily, Date date) {
		BigDecimal incomeAmount = BigDecimal.ZERO;
		List<BarDrinkEntity> income = incomeRepository.findByCreationdate(date);
		if (income != null && !income.isEmpty()) {
			Iterator<BarDrinkEntity> iincome = income.iterator();
			while (iincome.hasNext()) {
				incomeAmount = incomeAmount.add(iincome.next().getAmount());
			}
			daily.setFinalamount(daily.getFinalamount().add(incomeAmount));
			daily.setNumoperations(income.size() + daily.getNumoperations());
			daily.setIncome(income);
		}
	}

	private void setEntriesMoney(Daily daily, Date date) {
		BigDecimal entriesMoneyAmount = BigDecimal.ZERO;
		List<EntryMoneyEntity> entriesMoney = entryMoneyRepository.findByCreationdate(date);
		if (entriesMoney != null && !entriesMoney.isEmpty()) {
			Iterator<EntryMoneyEntity> ientriesmoney = entriesMoney.iterator();
			while (ientriesmoney.hasNext()) {
				entriesMoneyAmount = entriesMoneyAmount.add(ientriesmoney.next().getAmount());
			}
			daily.setFinalamount(daily.getFinalamount().add(entriesMoneyAmount));
			daily.setNumoperations(entriesMoney.size() + daily.getNumoperations());
			daily.setEntriesMoney(entriesMoney);
		}
	}

	private void setGratifications(Daily daily, Date date) {
		Integer gratificationsAmount = 0;
		List<GratificationEntity> gratifications = gratificationRepository.searchByPaydate(date);
		if (gratifications != null && !gratifications.isEmpty()) {
			Iterator<GratificationEntity> igratifications = gratifications.iterator();
			while (igratifications.hasNext()) {
				gratificationsAmount = gratificationsAmount + igratifications.next().getAmount();
			}
			daily.setFinalamount(daily.getFinalamount().subtract(new BigDecimal(gratificationsAmount)));
			daily.setNumoperations(gratifications.size() + daily.getNumoperations());
			daily.setGratifications(gratifications);
		}
	}

	private void setOperations(Daily daily, Date date) {
		BigDecimal operationsAmount = BigDecimal.ZERO;
		List<OperationEntity> operations = operationsRepository.findByCreationdate(date);
		if (operations != null) {
			Iterator<OperationEntity> ioperations = operations.iterator();
			OperationEntity operation;
			while (ioperations.hasNext()) {
				operation = ioperations.next();
				if (!operation.getPay().getIdpayment().equals(Constants.PROVIDING)) {
					operationsAmount = operationsAmount.add(operation.getAmount());
				}
			}
			daily.setFinalamount(daily.getFinalamount().subtract(operationsAmount));
			daily.setNumoperations(operations.size() + daily.getNumoperations());
			daily.setOperations(operations);
		}
	}

	public void calculateDailies(Date date) {
		Calendar calendar = Calendar.getInstance();
		while (date.compareTo(new DateUtil().getNow()) < 0) {
			getDaily(date);
			calendar.setTime(date);
			calendar.add(Calendar.DAY_OF_MONTH, 1);
			date = calendar.getTime();
		}
	}

	public Daily getDailyEmployee() {
		Date date = new DateUtil().getNow();
		Daily daily = getDaily(DateUtil.getDateFormatddMMyyyy(date));
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 9);
		date = calendar.getTime();
		List<OperationEntity> operations = operationsRepository.findByCreationdateBetween(date,
				new DateUtil().getNow());
		List<EntryMoneyEntity> entriesMoney = entryMoneyRepository.findByCreationdateBetween(date,
				new DateUtil().getNow());
		List<BarDrinkEntity> income = incomeRepository.findByCreationdateBetween(date, new DateUtil().getNow());
		List<IncomeSportsBetsEntity> incomesportsbets = incomeSportsBetsRepository.findByCreationdateBetween(date,
				new DateUtil().getNow());
		List<IncomeMachineEntity> incomemachines = incomemachinesRepository.findByCreationdateBetween(date,
				new DateUtil().getNow());
		List<GratificationEntity> gratifications = gratificationRepository.findByPaydateBetween(date,
				new DateUtil().getNow());
		List<ReturnMoneyEmployeeEntity> returns = returnMoneyUsersRepository.findByReturndateBetween(date,
				new DateUtil().getNow());
		List<ReturnMoneyEmployeeEntity> moneyadvance = returnMoneyUsersRepository.findByCreationdateBetween(date,
				new DateUtil().getNow());
		List<TPVEntity> tpvs = tpvrepository.findByCreationdateBetween(date, new DateUtil().getNow());
		List<ChangeMachineEntity> changemachine = changeMachineService.getOperationsTicketServerBetweenDates(date,
				new DateUtil().getNow());
		daily.setOperations(operations);
		daily.setEntriesMoney(entriesMoney);
		daily.setIncome(income);
		daily.setIncomesportsbets(incomesportsbets);
		daily.setIncomemachines(incomemachines);
		daily.setGratifications(gratifications);
		daily.setReturns(returns);
		daily.setTpvs(tpvs);
		daily.setListchangemachine(changemachine);
		daily.setMoneyadvance(moneyadvance);
		return daily;
	}

	@Override
	public Daily save(ChangeMachineEntity cm) {
		Date today = new DateUtil().getNow();
		cm.setCreationdate(today);
		changeMachineRepository.save(cm);
		return getDailyEmployee();
	}
}
