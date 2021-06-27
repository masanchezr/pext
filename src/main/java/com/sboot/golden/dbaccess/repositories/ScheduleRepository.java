package com.sboot.golden.dbaccess.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.TemporalType;

import org.springframework.data.jpa.repository.Temporal;
import org.springframework.data.repository.CrudRepository;

import com.sboot.golden.dbaccess.entities.ScheduleEntity;
import com.sboot.golden.dbaccess.entities.TimeEntity;

public interface ScheduleRepository extends CrudRepository<ScheduleEntity, Long> {

	public List<ScheduleEntity> findByDatescheduleBetween(@Temporal(TemporalType.DATE) Date from,
			@Temporal(TemporalType.DATE) Date until);

	public List<ScheduleEntity> findByDatescheduleAndTime(@Temporal(TemporalType.DATE) Date dateschedule,
			TimeEntity time);
}
