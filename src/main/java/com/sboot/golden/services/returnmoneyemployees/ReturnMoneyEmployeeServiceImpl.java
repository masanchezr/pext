package com.sboot.golden.services.returnmoneyemployees;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sboot.golden.dbaccess.entities.UserEntity;
import com.sboot.golden.dbaccess.entities.ReturnMoneyEmployeeEntity;
import com.sboot.golden.dbaccess.repositories.ReturnMoneyEmployeesRepository;
import com.sboot.golden.util.date.DateUtil;

@Service
public class ReturnMoneyEmployeeServiceImpl implements ReturnMoneyEmployeeService {

	@Autowired
	private ReturnMoneyEmployeesRepository returnMoneyUsersRepository;

	public void savereturn(ReturnMoneyEmployeeEntity returnme) {
		Optional<ReturnMoneyEmployeeEntity> opreturnme = returnMoneyUsersRepository
				.findById(returnme.getIdreturnmoneyemployee());
		if (opreturnme.isPresent()) {
			returnme = opreturnme.get();
			returnme.setReturndate(new DateUtil().getNow());
			returnMoneyUsersRepository.save(returnme);
		}
	}

	public BigDecimal findIncomeByMonth(String month) {
		Date date = DateUtil.getDate(month);
		Calendar calendar = Calendar.getInstance();
		Date from;
		Date until;
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		return returnMoneyUsersRepository.searchSumReturnByMonth(from, until);
	}

	public List<ReturnMoneyEmployeeEntity> findAdvanceByEmployee(UserEntity employee) {
		return returnMoneyUsersRepository.findByEmployeeAndReturndateIsNull(employee);
	}

	public void savemoneyadvance(ReturnMoneyEmployeeEntity returnme) {
		returnme.setCreationdate(new DateUtil().getNow());
		returnMoneyUsersRepository.save(returnme);
	}

	@Override
	public boolean isAllowedAdvances(ReturnMoneyEmployeeEntity returnme) {
		Calendar calendar = Calendar.getInstance();
		Date from;
		Date until;
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		from = calendar.getTime();
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		until = calendar.getTime();
		BigDecimal threedhundredfifty = new BigDecimal(350);
		BigDecimal advance = returnMoneyUsersRepository.searchSumAdvanceByMonth(from, until,
				returnme.getEmployee());
		if (advance == null) {
			advance = BigDecimal.ZERO;
		}
		return threedhundredfifty.compareTo(advance.add(returnme.getAmount())) > 0;
	}
}
