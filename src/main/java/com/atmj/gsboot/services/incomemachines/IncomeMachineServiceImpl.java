package com.atmj.gsboot.services.incomemachines;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atmj.gsboot.dbaccess.entities.IncomeMachineEntity;
import com.atmj.gsboot.dbaccess.repositories.IncomeMachinesRepository;
import com.atmj.gsboot.util.date.DateUtil;

@Service
public class IncomeMachineServiceImpl implements IncomeMachineService {

	@Autowired
	private IncomeMachinesRepository incomemachinesrepository;

	public void save(IncomeMachineEntity imachine) {
		imachine.setCreationdate(new DateUtil().getNow());
		incomemachinesrepository.save(imachine);
	}

	public BigDecimal findIncomeByMonth(Date from, Date until) {
		return incomemachinesrepository.searchSumByMonth(from, until);
	}
}
