package com.gu.services.test;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gu.admin.forms.EntryMoneyForm;
import com.gu.services.entrymoney.EntryMoneyService;
import com.gu.util.constants.Constants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:application-context-test.xml" })
public class EntryMoneyServiceTest {

	@Autowired
	private EntryMoneyService entryMoneyService;

	@Test
	public void saveEntryMoneyTest() {
		EntryMoneyForm form = new EntryMoneyForm();
		form.setOrigin(Constants.getOrigin()[0]);
		form.setAmount(BigDecimal.valueOf(300));
		entryMoneyService.saveEntryMoney(form);
	}

}
