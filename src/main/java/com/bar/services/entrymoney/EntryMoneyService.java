package com.bar.services.entrymoney;

import java.util.Date;
import java.util.List;

import com.bar.admin.forms.EntryMoneyForm;
import com.bar.dbaccess.entities.EntryMoneyEntity;
import com.bar.services.dailies.Daily;

public interface EntryMoneyService {

	public Daily saveEntryMoney(EntryMoneyForm entryMoney);

	public List<EntryMoneyEntity> findByDates(Date from, Date until);

	public Daily saveEntryMoneyEmployee(EntryMoneyForm entryMoneyForm);
}
