package com.gu.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/**
 * The Class DateUtil.
 */
public class DateUtil {

	private DateUtil() {

	}

	/**
	 * Gets the date.
	 *
	 * @param sdate the sdate
	 * @return the date
	 */
	public static Date getDate(String sdate) {
		Date date = null;
		String pattern = null;
		String p;
		SimpleDateFormat sdf = null;
		Map<String, String> patterns = new HashMap<>();
		patterns.put("\\d{2}/\\d{2}/\\d{4}", "dd/MM/yyyy");
		patterns.put("(\\d{2})-(\\d{2})-(\\d{4})", "dd-MM-yyyy");
		patterns.put("(\\d{4})-(\\d{2})-(\\d{2})", "yyyy-MM-dd");
		patterns.put("\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2}", "yyyy-MM-dd HH:mm:ss");
		patterns.put("(\\d{4})(\\d{2})(\\d{2})", "yyyyMMdd");
		patterns.put("\\d{2}/\\d{4}", "MM/yyyy");
		patterns.put("\\w{3}\\s\\w{3}\\s\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\sCET\\s\\d{4}", "EEE MMM dd HH:mm:ss zzz yyyy");
		patterns.put("\\w{3}\\s\\w{3}\\s\\d{2}\\s\\d{2}:\\d{2}:\\d{2}\\sCEST\\s\\d{4}",
				"EEE MMM dd HH:mm:ss zzzz yyyy");
		patterns.put("\\d{2}:\\d{2}\\s\\w{2}", "hh:mm aa");
		patterns.put("\\d{2}:\\d{2}:\\d{2}", "hh:mm:ss");
		Set<String> spatterns = patterns.keySet();
		Iterator<String> ipatterns = spatterns.iterator();
		while (ipatterns.hasNext() && sdf == null) {
			pattern = ipatterns.next();
			if (sdate.matches(pattern)) {
				p = patterns.get(pattern);
				sdf = new SimpleDateFormat(p, Locale.ENGLISH);
			}
		}
		if (sdf != null) {
			try {
				date = sdf.parse(sdate);
			} catch (ParseException e) {
				date = null;
			}
		}
		return date;
	}

	/**
	 * Checks if is date.
	 *
	 * @param sdate the sdate
	 * @return true, if is date
	 */
	public static boolean isDate(String sdate) {
		boolean isdate = false;
		List<String> patterns = new ArrayList<>();
		// dd/MM/yyyy
		patterns.add("\\d\\d/\\d\\d/\\d\\d\\d\\d");
		// dd.MM.yyyy
		patterns.add("\\d\\d.\\d\\d.\\d\\d\\d\\d");
		// dd-MM-yyyy
		patterns.add("\\d\\d-\\d\\d-\\d\\d\\d\\d");
		// yyyy-MM-dd
		patterns.add("\\d\\d\\d\\d-\\d\\d-\\d\\d");
		// MM/yyyy
		patterns.add("\\d\\d/\\d\\d\\d\\d");
		Iterator<String> ipatterns = patterns.iterator();
		while (ipatterns.hasNext() && !isdate) {
			if (sdate.matches(ipatterns.next())) {
				isdate = true;
			}
		}
		return isdate;
	}

	public static Date addDays(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		return calendar.getTime();
	}

	public static String getStringDateFormatddMMyyyy(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		return sdf.format(date);
	}

	public static String getStringDateFormatddMMyyyyHHmm(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return sdf.format(date);
	}

	public static String getStringDateFormatHHmm(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		return sdf.format(date);
	}

	public static List<Date> getDates(String sweek) {
		List<Date> dates = new ArrayList<>(7);
		Calendar calendar = Calendar.getInstance();
		int iweek = Integer.parseInt(sweek.substring(sweek.indexOf('W') + 1));
		int year = Integer.parseInt(sweek.substring(0, sweek.indexOf('-')));
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.WEEK_OF_YEAR, iweek);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		for (int i = 0; i < 7; i++) {
			dates.add(calendar.getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dates;
	}

	public static List<Date> getDates(Date from, Date until) {
		List<Date> dates = new ArrayList<>();
		Calendar cfrom = Calendar.getInstance();
		Calendar cuntil = Calendar.getInstance();
		cfrom.setTime(from);
		cuntil.setTime(until);
		while (cfrom.compareTo(cuntil) != 0) {
			dates.add(cfrom.getTime());
			cfrom.add(Calendar.DAY_OF_MONTH, 1);
		}
		return dates;
	}

	public static String getStringDateFormatyyyyMMdd(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	public static String getStringDateFormatddMMyyyyHHmmss(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return sdf.format(date);
	}

	public static Date getDayMonthMinimum(String monthyear) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(monthyear));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getDayMonthMaximum(String monthyear) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(getDate(monthyear));
		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return calendar.getTime();
	}

	public static Date getDateFormated(Date date) {
		return getDate(getStringDateFormatddMMyyyy(date));
	}
}
