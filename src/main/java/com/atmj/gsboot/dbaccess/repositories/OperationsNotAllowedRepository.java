package com.atmj.gsboot.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.AwardEntity;
import com.atmj.gsboot.dbaccess.entities.MachineEntity;
import com.atmj.gsboot.dbaccess.entities.OperationNotAllowedEntity;
import com.atmj.gsboot.dbaccess.entities.PaymentEntity;

public interface OperationsNotAllowedRepository extends CrudRepository<OperationNotAllowedEntity, Long> {

	public OperationNotAllowedEntity findFirstByMachineAndPayAndAward(MachineEntity machine, PaymentEntity pay,
			AwardEntity award);
}
