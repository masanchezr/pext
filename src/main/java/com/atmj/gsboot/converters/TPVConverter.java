
package com.atmj.gsboot.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atmj.gsboot.boss.forms.TPV;
import com.atmj.gsboot.dbaccess.entities.TPVEntity;
import com.atmj.gsboot.util.date.DateUtil;

@Component
public class TPVConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public TPVEntity convertToEntity(TPV tpv) {
		TPVEntity entity = mapper.map(tpv, TPVEntity.class);
		entity.setCreationdate(DateUtil.getDate(tpv.getSdate()));
		return entity;
	}

}