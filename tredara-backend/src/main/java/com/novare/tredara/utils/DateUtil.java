package com.novare.tredara.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * This is Date helper class to convert the Date into different formats.
 * 
 * @author malli
 *
 */
public final class DateUtil {

	public final static String DATE_FORMAT_PATTERN_DDMMYYTT = "dd-MM-yyyy HH:mm:ss";
	public final static String DATE_FORMAT_PATTERN_YYMMDDTT = "yyyy-MM-dd HH:mm:ss";

	private DateUtil() {
	}

	public static LocalDate toLocalDate(Date date) {
		return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public static Date toDate(LocalDateTime date) {
		return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date toDate(LocalDate localDate) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		return Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
	}

	public static LocalDateTime toLocalDateTime(String dateAsString) throws ParseException {
		return LocalDateTime.parse(dateAsString, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_DDMMYYTT));
	}
	public static Date toDate(String dateAsString) throws ParseException {
		return toDate(toLocalDateTime(dateAsString));
	}

	public static String toString(LocalDateTime date) throws ParseException {
		return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_DDMMYYTT));
	}

	public static String getNow() throws ParseException {
		return toString(LocalDateTime.now());
	}

	public static String toString(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_PATTERN_DDMMYYTT).format(date);
	}

	public static String toStringYYMMDD(LocalDateTime date) {
		return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_YYMMDDTT));
	}

	public static String toStringYYMMDD(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_PATTERN_YYMMDDTT).format(date);
	}

	public static Date toDateYYMMDD(String dateAsString) {
		LocalDateTime parse = LocalDateTime.parse(dateAsString, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN_YYMMDDTT));
		return toDate(parse);
	}
}
