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

	public final static String DATE_FORMAT_PATTERN = "dd-MM-yyyy HH:mm:ss";

	private DateUtil() {
	}

	public static LocalDate toLocalDate(Date date) {
		return LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault());
	}

	public static Date toDate(LocalDateTime date) {
		return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static LocalDateTime toDate(String dateAsString) throws ParseException {
		return LocalDateTime.parse(dateAsString, DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
	}

	public static String toString(LocalDateTime date) throws ParseException {
		return date.format(DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN));
	}

	public static String getNow() throws ParseException {
		return toString(LocalDateTime.now());
	}

	public static String toString(Date date) {
		return new SimpleDateFormat(DATE_FORMAT_PATTERN).format(date);
	}
}
