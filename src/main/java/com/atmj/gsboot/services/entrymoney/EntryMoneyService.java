package com.atmj.gsboot.services.entrymoney;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.atmj.gsboot.admin.forms.EntryMoneyForm;
import com.atmj.gsboot.dbaccess.entities.EntryMoneyEntity;
import com.atmj.gsboot.dbaccess.entities.SafeEntity;

public interface EntryMoneyService {

	public void saveEntryMoney(EntryMoneyForm entryMoney);

	public List<EntryMoneyEntity> findByDates(Date from, Date until);

	public void saveEntryMoneyEmployee(EntryMoneyForm entryMoneyForm);

	public void saveEntryMachine(EntryMoneyForm entryMoney);

	public BigDecimal saveAdd(SafeEntity safe);

	public BigDecimal saveAddChangeMachine(SafeEntity safe);

	public BigDecimal searchTotalSafe();

	public List<SafeEntity> searchByDates(String datefrom);

	public BigDecimal saveSubDirect(SafeEntity safe);

	public BigDecimal saveSub(SafeEntity safe);

	public List<SafeEntity> searchToday();
}
