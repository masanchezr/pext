package com.sboot.golden.converters;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;

import com.sboot.golden.util.date.DateUtil;
import com.sboot.golden.util.string.Util;

/**
 * The Class StringToDateConverter.
 */
public class StringToDateConverter extends DozerConverter<String, Date> {

	/**
	 * Instantiates a new string to date converter.
	 */
	public StringToDateConverter() {
		super(String.class, Date.class);
	}

	@Override
	public Date convertTo(String source, Date destination) {
		if (!Util.isEmpty(source)) {
			destination = DateUtil.getDate(source);
		}
		return destination;
	}

	@Override
	public String convertFrom(Date source, String destination) {
		if (source != null) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			return df.format(source);
		} else {
			return null;
		}
	}

}
