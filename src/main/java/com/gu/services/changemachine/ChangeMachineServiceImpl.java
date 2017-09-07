package com.gu.services.changemachine;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.dbaccess.entities.TakeEntity;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.ChangeMachineTotalRepository;
import com.gu.dbaccess.repositories.TakingsRepository;

public class ChangeMachineServiceImpl implements ChangeMachineService {

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

	@Autowired
	private ChangeMachineTotalRepository changeMachineTotalRepository;

	@Autowired
	private TakingsRepository takingsRepository;

	public void saveLuckiaAward(ChangeMachineEntity cmachine) {
		cmachine.setAmount(cmachine.getAmount().negate());
		cmachine.setCreationdate(new Date());
		changeMachineRepository.save(cmachine);
	}

	public BigDecimal getIncomeTotalMonth() {
		return changeMachineRepository.sumBetweenDates(takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(),
				new Date());
	}

	public void reset() {
		Date from = takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate();
		TakeEntity take = new TakeEntity();
		ChangeMachineTotalEntity cmt = changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc();
		ChangeMachineTotalEntity cmtn = new ChangeMachineTotalEntity();
		take.setTakedate(new Date());
		cmtn.setCreationdate(new Date());
		cmtn.setTotal(cmt.getTotal().add(changeMachineRepository.sumByCreationdateBetweenLuckia(from, new Date()))
				.add(changeMachineRepository.sumByCreationdateBetween(from, new Date())));
		changeMachineTotalRepository.save(cmtn);
		takingsRepository.save(take);
	}

	public BigDecimal getTotal() {
		return changeMachineTotalRepository.findFirstByOrderByIdchangemachinetotalDesc().getTotal();
	}

	public BigDecimal getAwardsLuckia() {
		return changeMachineRepository.sumByCreationdateBetweenLuckia(
				takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(), new Date());
	}

	public BigDecimal getAwards() {
		return changeMachineRepository
				.sumByCreationdateBetween(takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(), new Date());
	}

	public ChangeMachineEntity getLastLuckia() {
		return changeMachineRepository
				.findFirstByOperationIsNullAndAmountLessThanOrderByIdchangemachineDesc(BigDecimal.ZERO);
	}
}
