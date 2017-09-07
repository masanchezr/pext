package com.gu.services.safe;

import java.math.BigDecimal;
import java.util.List;

import com.gu.dbaccess.entities.SafeEntity;

public interface SafeService {

	public BigDecimal saveAdd(SafeEntity safe);

	public BigDecimal searchTotal();

	public BigDecimal saveSub(SafeEntity safe);

	public List<SafeEntity> searchByDates(String month);

}
