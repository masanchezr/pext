package com.atmj.gsboot.services.operations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.boss.forms.Expense;
import com.atmj.gsboot.dbaccess.entities.AwardEntity;
import com.atmj.gsboot.dbaccess.entities.GratificationEntity;
import com.atmj.gsboot.dbaccess.entities.OperationEntity;
import com.atmj.gsboot.dbaccess.entities.OperationNotAllowedEntity;
import com.atmj.gsboot.dbaccess.entities.PaymentEntity;
import com.atmj.gsboot.dbaccess.repositories.GratificationsRepository;
import com.atmj.gsboot.dbaccess.repositories.OperationsNotAllowedRepository;
import com.atmj.gsboot.dbaccess.repositories.OperationsRepository;
import com.atmj.gsboot.services.dailies.Daily;
import com.atmj.gsboot.services.dailies.DailyService;
import com.atmj.gsboot.util.constants.Constants;
import com.atmj.gsboot.util.constants.ConstantsViews;
import com.atmj.gsboot.util.date.DateUtil;

@Service
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

	@Override
	public Map<String, ?> findExpenses(Date from, Date until) {
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
