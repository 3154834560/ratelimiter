package com.example.demo.infrastructure.tool;

import lombok.Getter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author 王景阳
 * @date 2023/3/29 0:17
 */
public class DateTimeTool {
    /**
     * 字符串转化为日期
     *
     * @param str 传入字符串
     * @return 返回日期
     */
    public static LocalDateTime strToLocalDateTime(String str, DateFormat dateFormat) {
        LocalDateTime date = null;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat.getFormat());
        date = LocalDateTime.parse(str, dateTimeFormatter);
        return date;
    }

    /**
     * 日期转化为字符串
     *
     * @param date 传入日期
     * @return 返回字符串
     */
    public static String dateToString(LocalDateTime date, DateFormat dateFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat.getFormat());
        return dateTimeFormatter.format(date);
    }

    /**
     * 字符串转化为日期
     *
     * @param str 传入字符串
     * @return 返回日期
     */
    public static Date strToDate(String str, DateFormat dateFormat) {
        Date date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat.getFormat());
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转化为字符串
     *
     * @param date 传入日期
     * @return 返回字符串
     */
    public static String dateToString(Date date, DateFormat dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat.getFormat());
        return simpleDateFormat.format(date);
    }

    /**
     * 将时间戳转换为LocalDateTime，秒级
     */
    public static LocalDateTime toLocalDateTime(long seconds) {
        return LocalDateTime.ofEpochSecond(seconds, 0, ZoneOffset.ofHours(8));
    }

    /**
     * 将时间戳转换为LocalDateTime，秒级
     */
    public static LocalDateTime toLocalDateTime(String seconds) {
        return LocalDateTime.ofEpochSecond(Long.parseLong(seconds), 0, ZoneOffset.ofHours(8));
    }

    /**
     * 将LocalDateTime转换为时间戳，秒级
     */
    public static long toLong(LocalDateTime localDateTime) {
        return localDateTime.toEpochSecond(ZoneOffset.ofHours(8));
    }

    public static Date getCurrentDate() {
        return new Date();
    }

    public static Date getDateBy(long expiration) {
        return new Date(System.currentTimeMillis() + expiration);
    }

    @SuppressWarnings("all")
    @Getter
    public enum DateFormat {
        ONE(1, "yyyy-MM-dd HH:mm:ss"),
        TOW(2, "yyyy-MM-dd"),
        THREE(3, "HH:mm:ss"),
        FOUR(4, "yyyy/MM/dd HH:mm:ss"),
        FIVE(5, "yyyy年MM月dd日 HH时mm分ss秒"),
        SIX(6, "yyyy年MM月dd日"),
        SEVEN(7, "yyyy.MM.dd");

        DateFormat(int index, String format) {
            this.index = index;
            this.format = format;
        }

        private int index;
        private String format;
    }
}
