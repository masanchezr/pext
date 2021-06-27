package com.sboot.golden.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.AwardEntity;
import com.sboot.golden.dbaccess.entities.MachineEntity;
import com.sboot.golden.dbaccess.entities.OperationNotAllowedEntity;
import com.sboot.golden.dbaccess.entities.PaymentEntity;

public interface OperationsNotAllowedRepository extends CrudRepository<OperationNotAllowedEntity, Long> {

	public OperationNotAllowedEntity findFirstByMachineAndPayAndAward(MachineEntity machine, PaymentEntity pay,
			AwardEntity award);
}
