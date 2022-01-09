package com.sboot.golden.converters;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sboot.golden.boss.forms.TPV;
import com.sboot.golden.dbaccess.entities.TPVEntity;
import com.sboot.golden.util.date.DateUtil;

@Component
public class TPVConverter {

	/** The mapper. */
	@Autowired
	private ModelMapper mapper;

	public TPVEntity convertToEntity(TPV tpv) {
		TPVEntity entity = mapper.map(tpv, TPVEntity.class);
		entity.setCreationdate(DateUtil.getDate(tpv.getCreationdate()));
		return entity;
	}

}
