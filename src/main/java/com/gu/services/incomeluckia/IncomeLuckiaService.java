package com.gu.services.incomeluckia;

import java.math.BigDecimal;

import com.gu.dbaccess.entities.IncomeLuckiaEntity;
import com.gu.services.dailies.Daily;

public interface IncomeLuckiaService {

	Daily save(IncomeLuckiaEntity iluckia);

	BigDecimal findIncomeByMonth(String datefrom);

}
