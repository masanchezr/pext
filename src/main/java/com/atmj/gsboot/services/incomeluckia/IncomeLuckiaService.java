package com.atmj.gsboot.services.incomeluckia;

import java.math.BigDecimal;

import com.atmj.gsboot.dbaccess.entities.IncomeLuckiaEntity;

public interface IncomeLuckiaService {

	void save(IncomeLuckiaEntity iluckia);

	BigDecimal findIncomeByMonth(String datefrom);

}
