package com.sboot.golden.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sboot.golden.admin.forms.EntryMoneyForm;
import com.sboot.golden.dbaccess.entities.EntryMoneyEntity;
import com.sboot.golden.dbaccess.entities.SafeEntity;

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
