package com.gu.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.gu.dbaccess.entities.MachineEntity;

public interface MachinesRepository extends CrudRepository<MachineEntity, Long> {

	public List<MachineEntity> findByOrderByOrdermachine();

	public List<MachineEntity> findByOnoffTrueOrderByOrdermachine();

	public MachineEntity findByNameticket(String award);
}
