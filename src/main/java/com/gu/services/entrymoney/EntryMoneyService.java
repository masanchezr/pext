package com.gu.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.dbaccess.entities.EntryMoneyEntity;
import com.gu.dbaccess.entities.SafeEntity;
import com.gu.services.dailies.Daily;

public interface EntryMoneyService {

	public Daily saveEntryMoney(EntryMoneyForm entryMoney);

	public List<EntryMoneyEntity> findByDates(Date from, Date until);

	public Daily saveEntryMoneyEmployee(EntryMoneyForm entryMoneyForm);

	public Daily saveEntryMachine(EntryMoneyForm entryMoney);

	public BigDecimal saveAdd(SafeEntity safe);

	public BigDecimal saveSub(SafeEntity safe);

	public BigDecimal searchTotalSafe();

	public List<SafeEntity> searchByDates(String datefrom);
}
