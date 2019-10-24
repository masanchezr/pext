package com.gu.services.incomeluckia;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.IncomeLuckiaEntity;

public interface IncomeLuckiaService {

	void save(IncomeLuckiaEntity iluckia);

	BigDecimal findIncomeByMonth(String datefrom);

}
