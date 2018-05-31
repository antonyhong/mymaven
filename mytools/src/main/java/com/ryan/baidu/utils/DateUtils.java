package com.ryan.baidu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by hongyongming on 2015/9/22.
 */
public class DateUtils {
    public static final String[] DatePatterns = new String[]{"yyyy", "yyyy-MM", "yyyy-MM-dd", "yyyy-MM-dd HH", "yyyy-MM-dd HH:mm", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm:ss.SSS"};
    public static final String DefaultDatePattern = "yyyy-MM-dd HH:mm:ss.SSS";
    private static final ThreadLocal<SimpleDateFormat> Ymdhms = new ThreadLocal() {
        public SimpleDateFormat get() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };
    private static final ThreadLocal<SimpleDateFormat> Ymd = new ThreadLocal() {
        public SimpleDateFormat get() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public DateUtils() {
    }

    public static String formatYmdhms(Date date) {
        return formatDate(date, Ymdhms);
    }

    private static String formatDate(Date date, ThreadLocal<SimpleDateFormat> format) {
        return date == null ? "" : ((SimpleDateFormat) format.get()).format(date);
    }

    public static Date parseYmdhms(String formatedDate) {
        try {
            return ((SimpleDateFormat) Ymdhms.get()).parse(formatedDate);
        } catch (ParseException var2) {
            throw new IllegalArgumentException(var2);
        }
    }

    public static Date parseAllFormat(String str) {
        try {
            return org.apache.commons.lang3.time.DateUtils.parseDate(str, DatePatterns);
        } catch (ParseException var2) {
            throw new IllegalArgumentException(var2);
        }
    }

    public static Date parseYmd(String formatedDate) {
        try {
            return ((SimpleDateFormat) Ymd.get()).parse(formatedDate);
        } catch (ParseException var2) {
            throw new IllegalArgumentException(var2);
        }
    }

    public static String formatYmd(Date date) {
        return formatDate(date, Ymd);
    }

    public static long betweenDay(Date start, Date end) {
        return betweenDate(TimeUnit.DAYS, getBeginingOfDate(start), getBeginingOfDate(end));
    }

    public static long betweenHour(Date start, Date end) {
        SimpleDateFormat ymdHm = new SimpleDateFormat("yyyy-MM-dd HH");
        start = parse(ymdHm, ymdHm.format(start));
        end = parse(ymdHm, ymdHm.format(end));
        return betweenDate(TimeUnit.HOURS, start, end);
    }

    public static long betweenSecond(Date start, Date end) {
        return betweenDate(TimeUnit.SECONDS, start, end);
    }

    public static Date getNDayBefore(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(6, -n);
        setBeginingOfDate(calendar);
        return calendar.getTime();
    }

    public static String getYmdhmsStr(int n) {
        return ((SimpleDateFormat) Ymdhms.get()).format(getDateTime(n));
    }

    public static Date getDateTime(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, n);
        return calendar.getTime();
    }

    private static long betweenDate(TimeUnit unit, Date start, Date end) {
        return Math.abs(end.getTime() / unit.toMillis(1L) - start.getTime() / unit.toMillis(1L));
    }

    private static Date parse(SimpleDateFormat sdf, String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException var3) {
            throw new IllegalArgumentException(var3);
        }
    }

    private static Date getBeginingOfDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        setBeginingOfDate(calendar);
        return calendar.getTime();
    }

    private static void setBeginingOfDate(Calendar calendar) {
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
    }
}
