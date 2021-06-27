package com.sboot.golden.services.incomeluckia;

import java.math.BigDecimal;

import com.sboot.golden.dbaccess.entities.IncomeLuckiaEntity;

public interface IncomeLuckiaService {

	void save(IncomeLuckiaEntity iluckia);

	BigDecimal findIncomeByMonth(String datefrom);

}
