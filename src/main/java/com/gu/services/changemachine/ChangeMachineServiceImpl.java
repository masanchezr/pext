package com.gu.services.changemachine;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.gu.dbaccess.entities.ChangeMachineEntity;
import com.gu.dbaccess.entities.ChangeMachineTotalEntity;
import com.gu.dbaccess.entities.TakeEntity;
import com.gu.dbaccess.repositories.ChangeMachineRepository;
import com.gu.dbaccess.repositories.ChangeMachineTotalRepository;
import com.gu.dbaccess.repositories.TakingsRepository;
import com.gu.services.dailies.Daily;
import com.gu.services.dailies.DailyService;

public class ChangeMachineServiceImpl implements ChangeMachineService {

	@Autowired
	private DailyService dailyservice;

	@Autowired
	private ChangeMachineRepository changeMachineRepository;

	@Autowired
	private ChangeMachineTotalRepository changeMachineTotalRepository;

	@Autowired
	private TakingsRepository takingsRepository;

	public void saveLuckiaAward(ChangeMachineEntity cmachine) {
		Long id = changeMachineRepository.findFirstByAwardIsNullAndMachineIsNullOrderByIdchangemachineDesc()
				.getIdchangemachine();
		cmachine.setIdchangemachine(id + 1);
		cmachine.setAmount(cmachine.getAmount().negate());
		cmachine.setCreationdate(new Date());
		changeMachineRepository.save(cmachine);
	}

	public BigDecimal getIncomeTotalMonth() {
		return changeMachineRepository
				.sumIncomeBetweenDates(takingsRepository.findFirstByOrderByIdtakeDesc().getTakedate(), new Date());
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
				.findFirstByAwardIsNullAndAmountLessThanOrderByIdchangemachineDesc(BigDecimal.ZERO);
	}

	public Map<String, ?> ticketsByDay(Date date) {
		Map<String, Object> map = null;
		BigDecimal amount = BigDecimal.ZERO;
		List<ChangeMachineEntity> lcm = changeMachineRepository.findByCreationdate(date);
		if (lcm != null && !lcm.isEmpty()) {
			Iterator<ChangeMachineEntity> ilcm = lcm.iterator();
			while (ilcm.hasNext()) {
				amount = amount.add(ilcm.next().getAmount());
			}
			map = new HashMap<String, Object>();
			map.put("operations", lcm);
			map.put("amount", amount.plus());
		}
		return map;
	}

	public ChangeMachineEntity findOne(Long idchangemachine) {
		return changeMachineRepository.findOne(idchangemachine);
	}

	public Daily save(ChangeMachineEntity cm) {
		Date today = new Date();
		cm.setCreationdate(today);
		changeMachineRepository.save(cm);
		return dailyservice.getDailyEmployee(today);
	}
}
