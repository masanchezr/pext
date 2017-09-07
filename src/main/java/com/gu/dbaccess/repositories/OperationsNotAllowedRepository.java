package com.gu.dbaccess.repositories;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.AwardEntity;
import com.gu.dbaccess.entities.MachineEntity;
import com.gu.dbaccess.entities.OperationNotAllowedEntity;
import com.gu.dbaccess.entities.PaymentEntity;

public interface OperationsNotAllowedRepository extends CrudRepository<OperationNotAllowedEntity, Long> {

	public OperationNotAllowedEntity findFirstByMachineAndPayAndAward(MachineEntity machine, PaymentEntity pay,
			AwardEntity award);
}
