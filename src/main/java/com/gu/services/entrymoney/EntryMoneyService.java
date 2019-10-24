package com.gu.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.dbaccess.entities.EntryMoneyEntity;
import com.gu.dbaccess.entities.SafeEntity;

public interface EntryMoneyService {

	public void saveEntryMoney(EntryMoneyForm entryMoney);

	public List<EntryMoneyEntity> findByDates(Date from, Date until);

	public void saveEntryMoneyEmployee(EntryMoneyForm entryMoneyForm);

	public void saveEntryMachine(EntryMoneyForm entryMoney);

	public BigDecimal saveAdd(SafeEntity safe);

	public BigDecimal saveSub(SafeEntity safe);

	public BigDecimal searchTotalSafe();

	public List<SafeEntity> searchByDates(String datefrom);

	public BigDecimal saveSubDirect(SafeEntity safe);
}
