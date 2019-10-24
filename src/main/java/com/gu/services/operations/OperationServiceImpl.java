package com.gu.services.operations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.boss.forms.Expense;
import com.gu.dbaccess.entities.AwardEntity;
import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.dbaccess.entities.OperationEntity;
import com.gu.dbaccess.entities.OperationNotAllowedEntity;
import com.gu.dbaccess.entities.PaymentEntity;
import com.gu.dbaccess.repositories.GratificationsRepository;
import com.gu.dbaccess.repositories.OperationsNotAllowedRepository;
import com.gu.dbaccess.repositories.OperationsRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.Constants;
import com.gu.util.constants.ConstantsViews;
import com.gu.util.date.DateUtil;

public class OperationServiceImpl implements OperationService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private OperationsRepository operationRepository;

	@Autowired
	private OperationsNotAllowedRepository operationsnotallowedrepository;

	@Autowired
	private GratificationsRepository gratificationsrepository;

	public Daily save(OperationEntity operation) {
		operation.setCreationdate(new DateUtil().getNow());
		if (operation.getMachine().getIdmachine().equals(0L)) {
			operation.setMachine(null);
		}
		operationRepository.save(operation);
		return dailyService.getDailyEmployee();
	}

	public Daily update(OperationEntity operation) {
		OperationEntity entityoperation = operationRepository.findById(operation.getIdoperation()).orElse(null);
		PaymentEntity pay = operation.getPay();
		if (entityoperation != null) {
			if (operation.getMachine().getIdmachine().equals(0L)) {
				entityoperation.setMachine(null);
			} else {
				entityoperation.setMachine(operation.getMachine());
			}
			entityoperation.setAward(operation.getAward());
			entityoperation.setPay(pay);
			entityoperation.setAmount(operation.getAmount());
			operationRepository.save(entityoperation);
			return dailyService.getDaily(entityoperation.getCreationdate());
		} else {
			return dailyService.getDaily(new DateUtil().getNow());
		}
	}

	public Map<String, Object> findExpensesByMonth(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from;
		Date until;
		Object[] object;
		List<Object[]> sum;
		Iterator<Object[]> isum;
		List<Expense> expenses = new ArrayList<>();
		Expense expense;
		BigDecimal total = BigDecimal.ZERO;
		Map<String, Object> result = new HashMap<>();
		List<GratificationEntity> gratifications;
		Iterator<GratificationEntity> igratifications;
		Integer g = 0;
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		gratifications = gratificationsrepository.findByPaydateBetween(from, until);
		sum = operationRepository.searchSumByMonth(from, until);
		isum = sum.iterator();
		while (isum.hasNext()) {
			expense = new Expense();
			object = isum.next();
			expense.setAmount((BigDecimal) object[0]);
			expense.setAward((AwardEntity) object[1]);
			total = total.add(expense.getAmount());
			expenses.add(expense);
		}
		igratifications = gratifications.iterator();
		while (igratifications.hasNext()) {
			g = g + igratifications.next().getAmount();
		}
		total = total.add(BigDecimal.valueOf(g));
		result.put(Constants.GRATIFICATIONS, g);
		result.put("expenses", expenses);
		result.put(ConstantsViews.TOTAL, total);
		return result;
	}

	public OperationEntity findById(long id) {
		return operationRepository.findById(id).orElse(null);
	}

	public void delete(OperationEntity operation) {
		operationRepository.delete(operation);
	}

	public OperationNotAllowedEntity getOperationNotAllowed(OperationEntity operation) {
		return operationsnotallowedrepository.findFirstByMachineAndPayAndAward(operation.getMachine(),
				operation.getPay(), operation.getAward());
	}
}
