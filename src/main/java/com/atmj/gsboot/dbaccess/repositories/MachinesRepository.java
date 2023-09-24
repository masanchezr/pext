package com.atmj.gsboot.dbaccess.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.atmj.gsboot.dbaccess.entities.MachineEntity;

public interface MachinesRepository extends CrudRepository<MachineEntity, Long> {

	/**
	 * Todas las máquinas por el orden establecido
	 * 
	 * @return
	 */
	public List<MachineEntity> findByOrderByOrdermachine();

	/**
	 * Todas las máquinas activas por el orden establecido
	 * 
	 * @return
	 */
	public List<MachineEntity> findByOnoffTrueOrderByOrdermachine();

	public MachineEntity findByNameticket(String award);

	public MachineEntity findByOrdermachineAndOnoffTrue(Long ordermachine);
}
