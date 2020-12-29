package com.common.util;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author 念着倒才子傻
 */
public class DateUtil {

    enum DateFormat {
        DATE_FORMAT_YYYY_MM_DD("yyyy-MM-dd", DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        DATE_FORMAT_YYYY_MM_DD_HH_MM_SS("yyyy-MM-dd HH:mm:ss", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
        DATE_FORMAT_YYYY_MM_DD_HH_MM_SS_SSS("yyyy-MM-dd HH:mm:ss.SSS", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS")),
        DATE_FORMAT_HH_MM_SS("HH:mm:ss", DateTimeFormatter.ofPattern("HH:mm:ss")),
        DATE_FORMAT_YYYYMMDD("yyyyMMdd", DateTimeFormatter.ofPattern("yyyyMMdd")),
        DATE_FORMAT_YYYYMMDDHHMMSS("yyyyMMddHHmmss", DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        private final String stringFormat;
        private final DateTimeFormatter formatter;

        DateFormat(String stringFormat, DateTimeFormatter formatter) {
            this.stringFormat = stringFormat;
            this.formatter = formatter;
        }

        public String getStringFormat() {
            return stringFormat;
        }

        public DateTimeFormatter getFormatter() {
            return formatter;
        }
    }

    /**
     * 两个日期相减得到的天数
     *
     * @param beginDate 开始时间
     * @param endDate   结束时间
     * @return 相隔的天数
     */
    public static int getDiffDays(Date beginDate, Date endDate) {
        if (beginDate == null || endDate == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        long diff = (endDate.getTime() - beginDate.getTime()) / (1000 * 60 * 60 * 24);
        int days = new Long(diff).intValue();
        return days;
    }

    /**
     * 返回某个日期相隔几天的日期
     *
     * @param date 指定的日期
     * @param i    相隔天数 指定日期之前传负数，指定日期之后传正数
     * @return 想要获取的日期
     */
    public static Date getFrontDay(@NotNull final Date date, int i) {
        if (date == null) {
            throw new IllegalArgumentException("getDiffDays param is null!");
        }
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.DATE, cal.get(Calendar.DATE) + i);
        return cal.getTime();
    }

    /**
     * 获取当前日期
     *
     * @return 当前Date时间
     */
    public static Date getDate() {
        return new Date();
    }

    /**
     * localDateTime 转 Date
     *
     * @param localDateTime localDateTime 时间
     * @return Date时间
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * get the beginning of current date
     */
    public static Date getStartCurrentDate() {
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return localDateTimeToDate(start);
    }

    /**
     * get the ending of current date
     */
    public static Date getEndCurrentDate() {
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return localDateTimeToDate(end);
    }

    /**
     * get the previous date
     */
    public static Date getPreviousDate(long days) {
        LocalDate localDate = LocalDate.now().minusDays(days);
        return localDateToDate(localDate);
    }

    /**
     * get the future date
     */
    public static Date getFutureDate(long days) {
        LocalDate localDate = LocalDate.now().plusDays(days);
        return localDateToDate(localDate);
    }

    /**
     * LocalDate  转 Date
     */
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }


    /**
     * Date  转 LocalDate
     */
    public static LocalDate dateToLocalDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date  转 LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * Date  转 String
     */
    public static String dateToString(Date date, String format) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter);
    }

    /**
     * dateString  转 Date
     */
    public static Date dateStringToDate(String dateStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDate localDate = LocalDate.parse(dateStr, dateTimeFormatter);
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * dateTimeString  转 Date
     */
    public static Date dateTimeStringToDate(String dateTimeStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime localDateTime = LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * dateString  转 LocalDate
     */
    public static LocalDate dateStringToLocalDate(String dateStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDate.parse(dateStr, dateTimeFormatter);
    }

    /**
     * dateTimeString  转 LocalDate
     */
    public static LocalDateTime dateTimeStringToLocalDateTime(String dateTimeStr, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(dateTimeStr, dateTimeFormatter);
    }

}
