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
import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.GratificationEntity;
import com.gu.dbaccess.entities.OperationEntity;
import com.gu.dbaccess.entities.OperationNotAllowedEntity;
import com.gu.dbaccess.entities.PaymentEntity;
import com.gu.dbaccess.entities.ProvidingEntity;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.GratificationsRepository;
import com.gu.dbaccess.repositories.OperationsNotAllowedRepository;
import com.gu.dbaccess.repositories.OperationsRepository;
import com.gu.dbaccess.repositories.ProvidingRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;
import com.gu.util.constants.Constants;
import com.gu.util.date.DateUtil;

public class OperationServiceImpl implements OperationService {

	/** The daily service. */
	@Autowired
	private DailyService dailyService;

	@Autowired
	private OperationsRepository operationRepository;

	@Autowired
	private ProvidingRepository providingRepository;

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

	@Autowired
	private OperationsNotAllowedRepository operationsnotallowedrepository;

	@Autowired
	private GratificationsRepository gratificationsrepository;

	public Daily save(OperationEntity operation) {
		Long idpayment = operation.getPay().getIdpayment();
		operation.setCreationdate(new Date());
		if (operation.getMachine().getIdmachine().equals(0L)) {
			operation.setMachine(null);
		}
		operationRepository.save(operation);
		if (idpayment.equals(Constants.PROVIDING)) {
			ProvidingEntity providing = providingRepository.findFirstByOrderByIdprovidingDesc();
			ProvidingEntity entity = new ProvidingEntity();
			BigDecimal amount = operation.getAmount();
			entity.setCreationdate(new Date());
			entity.setAmount(amount.negate());
			entity.setTotal(providing.getTotal().subtract(amount));
			providingRepository.save(entity);
		} else if (idpayment.equals(Constants.CHANGEMACHINE)) {
			ChangeMachineEntity entity = new ChangeMachineEntity();
			BigDecimal amount = operation.getAmount();
			entity.setCreationdate(new Date());
			entity.setAmount(amount.negate());
			entity.setOperation(operation);
			changeMachineRepository.save(entity);
		}
		return dailyService.getDailyEmployee(new Date());
	}

	public Daily update(OperationEntity operation) {
		OperationEntity entityoperation = operationRepository.findOne(operation.getIdoperation());
		PaymentEntity pay = operation.getPay();
		Long idpayment = pay.getIdpayment();
		Long idpaymentback = entityoperation.getPay().getIdpayment();
		BigDecimal amount = operation.getAmount();
		BigDecimal amountback = entityoperation.getAmount();
		if (operation.getMachine().getIdmachine().equals(0L)) {
			entityoperation.setMachine(null);
		} else {
			entityoperation.setMachine(operation.getMachine());
		}
		entityoperation.setAward(operation.getAward());
		entityoperation.setPay(pay);
		entityoperation.setAmount(operation.getAmount());
		operationRepository.save(entityoperation);
		if (Constants.CHANGEMACHINE.equals(idpaymentback) && !idpayment.equals(Constants.CHANGEMACHINE)) {
			/**
			 * si la operación era de máquina de cambio y la actualización no, hay que
			 * borrar el registro y actualizar toda la suma
			 */
			ChangeMachineEntity cm = changeMachineRepository.findByOperation(entityoperation);
			changeMachineRepository.delete(cm);
		} else if (idpayment.equals(Constants.CHANGEMACHINE) && !Constants.CHANGEMACHINE.equals(idpaymentback)) {
			/**
			 * si la operación no era de máquina de cambio y la actualización si
			 */
			ChangeMachineEntity entity = new ChangeMachineEntity();
			entity.setCreationdate(entityoperation.getCreationdate());
			entity.setAmount(amount.negate());
			entity.setOperation(entityoperation);
			changeMachineRepository.save(entity);
		} else if (idpayment.equals(Constants.CHANGEMACHINE) && Constants.CHANGEMACHINE.equals(idpaymentback)
				&& !amount.equals(amountback)) {
			/**
			 * si la operación era de máquina de cambio y la actualización también y lo
			 * único que hay que cambiar es el importe, tenemos que actualizar el importe y
			 * todas las operaciones posteriores
			 */
			ChangeMachineEntity cm = changeMachineRepository.findByOperation(entityoperation);
			cm.setAmount(amount.negate());
			// actualizo
			changeMachineRepository.save(cm);
		} else if (idpayment.equals(Constants.PROVIDING)) {
			ProvidingEntity providing = providingRepository.findFirstByOrderByIdprovidingDesc();
			ProvidingEntity entity = new ProvidingEntity();
			entity.setCreationdate(new Date());
			entity.setAmount(amount.negate());
			entity.setTotal(providing.getTotal().subtract(amount));
			providingRepository.save(entity);
		}
		return dailyService.getDaily(entityoperation.getCreationdate());
	}

	public Map<String, Object> findExpensesByMonth(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		Object[] object;
		List<Object[]> sum;
		Iterator<Object[]> isum;
		List<Expense> expenses = new ArrayList<Expense>();
		Expense expense;
		BigDecimal total = BigDecimal.ZERO;
		Map<String, Object> result = new HashMap<String, Object>();
		List<GratificationEntity> gratifications;
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
		if (gratifications != null) {
			double g = gratifications.size() * 10;
			result.put("gratifications", g);
			total = total.add(new BigDecimal(g));
		}
		result.put("expenses", expenses);
		result.put("total", total);
		return result;
	}

	public OperationEntity findById(long id) {
		return operationRepository.findOne(id);
	}

	public void delete(OperationEntity operation) {
		operationRepository.delete(operation);
	}

	public List<OperationEntity> recharges(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		AwardEntity pay = new AwardEntity();
		pay.setIdaward(Constants.RECHARGES);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		return operationRepository.findByAwardAndCreationdateBetween(pay, from, until);
	}

	public Map<String, ?> getOperationsTpv(String month) {
		Map<String, Object> map = null;
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from, until;
		AwardEntity pay = new AwardEntity();
		pay.setIdaward(Constants.TPV);
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		List<OperationEntity> operations = operationRepository.findByAwardAndCreationdateBetween(pay, from, until);
		if (operations != null) {
			map = new HashMap<String, Object>();
			map.put("operations", operations);
			map.put("amount", operationRepository.sumByAwardAndCreationdate(pay, from, until));
		}
		return map;
	}

	public Map<String, ?> ticketsByDay(Date date) {
		Map<String, Object> map = null;
		PaymentEntity pay = new PaymentEntity();
		BigDecimal amount = BigDecimal.ZERO;
		pay.setIdpayment(Constants.CHANGEMACHINE);
		List<OperationEntity> lcm = operationRepository.findByPayAndCreationdate(pay, date);
		if (lcm != null && !lcm.isEmpty()) {
			Iterator<OperationEntity> ilcm = lcm.iterator();
			while (ilcm.hasNext()) {
				amount = amount.add(ilcm.next().getAmount());
			}
			map = new HashMap<String, Object>();
			map.put("operations", lcm);
			map.put("amount", amount.plus());
		}
		return map;
	}

	public OperationNotAllowedEntity getOperationNotAllowed(OperationEntity operation) {
		return operationsnotallowedrepository.findFirstByMachineAndPayAndAward(operation.getMachine(),
				operation.getPay(), operation.getAward());
	}
}
