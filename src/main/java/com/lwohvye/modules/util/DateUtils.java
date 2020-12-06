package com.lwohvye.modules.util;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * <p>
 * 继承org.apache.commons.lang3.time.DateUtils类
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {

    public static String[] parsePatterns = {"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm"};

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd）
     */
    public static String getDate() {
        return getDate("yyyy-MM-dd");
    }

    /**
     * 得到当前日期字符串 格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String getDate(String pattern) {
        return DateFormatUtils.format(new Date(), pattern);
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd） pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E"
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return formatDate;
    }

    /**
     * 得到日期时间字符串，转换格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前时间字符串 格式（HH:mm:ss）
     */
    public static String getTime() {
        return formatDate(new Date(), "HH:mm:ss");
    }

    /**
     * 得到当前日期和时间字符串 格式（yyyy-MM-dd HH:mm:ss）
     */
    public static String getDateTime() {
        return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 得到当前年份字符串 格式（yyyy）
     */
    public static String getYear() {
        return formatDate(new Date(), "yyyy");
    }

    /**
     * 得到当前月份字符串 格式（MM）
     */
    public static String getMonth() {
        return formatDate(new Date(), "MM");
    }

    /**
     * 得到当天字符串 格式（dd）
     */
    public static String getDay() {
        return formatDate(new Date(), "dd");
    }

    /**
     * 得到当前星期字符串 格式（E）星期几
     */
    public static String getWeek() {
        return formatDate(new Date(), "E");
    }

    /**
     * 日期型字符串转化为日期 格式 { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
     * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
     */
    public static Date parseDate(Object str) {
        if (str == null) {
            return null;
        }
        try {
            return parseDate(str.toString(), parsePatterns);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 获取过去的天数
     *
     * @param date
     * @return
     */
    public static long pastDays(Date date) {
        long t = new Date().getTime() - date.getTime();
        return t / (24 * 60 * 60 * 1000);
    }

    /**
     * 得到几小时之后的时间
     *
     * @param d
     * @param hour
     * @return
     */
    public static Date getDateAfterHour(Date d, int hour) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.HOUR, now.get(Calendar.HOUR) + hour);
        return now.getTime();
    }

    /**
     * 得到几天前的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }
    /**
     * 得到几天后的时间
     *
     * @param d
     * @param day
     * @return
     */
    public static Date getDateAfterDay(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
        return now.getTime();
    }

    /**
     * 得到几月后的时间
     *
     * @param d
     * @param month
     * @return
     */
    public static Date getDateAfterMonth(Date d, int month) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.MONTH, now.get(Calendar.MONTH) + month);
        return now.getTime();
    }

    /**
     * 得到几年后的时间
     *
     * @param d
     * @param year
     * @return
     */
    public static Date getDateAfterYear(Date d, int year) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.YEAR, now.get(Calendar.YEAR) + year);
        return now.getTime();
    }

	/**
	 * 得到本月第一天的日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		cDay.set(Calendar.DAY_OF_MONTH, 1);
		cDay.set(Calendar.HOUR_OF_DAY,
				cDay.getActualMinimum(Calendar.HOUR_OF_DAY));
		cDay.set(Calendar.MINUTE, 0);
		cDay.set(Calendar.SECOND, 0);
		return cDay.getTime();
	}

	/**
	 * 得到本月最后一天的日期
	 *
	 * @param date
	 * @return
	 */
	public static Date getLastDayOfMonth(Date date) {
		Calendar cDay = Calendar.getInstance();
		cDay.setTime(date);
		cDay.set(Calendar.DAY_OF_MONTH,
				cDay.getActualMaximum(Calendar.DAY_OF_MONTH));
		cDay.set(Calendar.HOUR_OF_DAY,
				cDay.getActualMaximum(Calendar.HOUR_OF_DAY));
		cDay.set(Calendar.MINUTE, 59);
		cDay.set(Calendar.SECOND, 59);
		return cDay.getTime();
	}

    /**
     * 获取2099年最后的日期
     *
     * @return
     */
    public static Date getYear2399() {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, 2399);
        cYear.set(Calendar.MONTH, 11);
        cYear.set(Calendar.DATE, 31);
        cYear.set(Calendar.HOUR_OF_DAY, 23);
        cYear.set(Calendar.MINUTE, 59);
        cYear.set(Calendar.SECOND, 59);
        return cYear.getTime();
    }

    /**
     * 得到本月最后一天的日期
     *
     * @param date
     * @return
     */
    public static Date getDate(String pattern,String date) throws ParseException {
        return new SimpleDateFormat(pattern).parse(date);
    }

    /**
     * 获取一周的开始时间
     *
     * @return
     */
    public static Date getStartDateOfWeek(Integer week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        c.set (Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        Calendar cWeek = new GregorianCalendar();
        cWeek.setFirstDayOfWeek(Calendar.MONDAY);
        cWeek.setTime(cal.getTime());
        cWeek.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+1); // Monday
        cWeek.set(Calendar.HOUR_OF_DAY, 0);
        cWeek.set(Calendar.MINUTE, 0);
        cWeek.set(Calendar.SECOND, 0);

        return cWeek.getTime();
    }

    /**
     * 取得当前日期所在周的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfWeek(Date date) {
        Calendar c = new GregorianCalendar();
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.setTime(date);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
        return c.getTime ();
    }

    /**
     * 获取一周的结束时间
     *
     * @return
     */
    public static Date getEndDateOfWeek(Integer week) {
        Calendar c = new GregorianCalendar();
        c.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        c.set (Calendar.MONTH, Calendar.JANUARY);
        c.set(Calendar.DATE, 1);

        Calendar cal = (GregorianCalendar) c.clone();
        cal.add(Calendar.DATE, week * 7);

        Calendar cWeek = new GregorianCalendar();
        cWeek.setFirstDayOfWeek(Calendar.MONDAY);
        cWeek.setTime(cal.getTime());
        cWeek.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()+7); // Monday
        cWeek.set(Calendar.HOUR_OF_DAY, 23);
        cWeek.set(Calendar.MINUTE, 59);
        cWeek.set(Calendar.SECOND, 59);

        return cWeek.getTime();
    }

    /**
     * 获取一月的开始时间
     *
     * @return
     */
    public static Date getStartDateOfMonth(Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取一月的结束时间
     *
     * @return
     */
    public static Date getEndDateOfMonth(Integer month) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 获取季度的开始时间
     *
     * @return
     */
    public static Date getStartDateOfQuarter(Integer quarter) {
        int month = 1;
        if (quarter == 2) {
            month = 4;
        } else if (quarter == 3) {
            month = 7;
        }if (quarter == 4) {
            month = 10;
        }
        return getStartDateOfMonth(month);
    }

    /**
     * 获取季度的结束时间
     *
     * @return
     */
    public static Date getEndDateOfQuarter(Integer quarter) {
        int month = 3;
        if (quarter == 2) {
            month = 6;
        } else if (quarter == 3) {
            month = 9;
        }if (quarter == 4) {
            month = 12;
        }
        return getEndDateOfMonth(month);
    }

    /**
     * 获取下一小时的开始时间
     *
     * @return
     */
    public static Date getStartDateOfNextHour() {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cYear.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY) +1);
        cYear.set(Calendar.MINUTE, 0);
        cYear.set(Calendar.SECOND, 0);
        return cYear.getTime();
    }

    /**
     * 获取下一小时的结束时间
     *
     * @return
     */
    public static Date getEndDateOfNextHour(int hour) {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cYear.set(Calendar.HOUR_OF_DAY, Calendar.getInstance().get(Calendar.HOUR_OF_DAY) +hour);
        cYear.set(Calendar.MINUTE, 59);
        cYear.set(Calendar.SECOND, 59);
        return cYear.getTime();
    }

    /**
     * 获取下一天的开始时间
     *
     * @return
     */
    public static Date getStartDateOfNextDay() {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cYear.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH) +1);
        cYear.set(Calendar.HOUR_OF_DAY, 0);
        cYear.set(Calendar.MINUTE, 0);
        cYear.set(Calendar.SECOND, 0);
        return cYear.getTime();
    }

    /**
     * 获取下一天的结束时间
     *
     * @return
     */
    public static Date getEndDateOfNextDay(int day) {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cYear.set(Calendar.DAY_OF_MONTH, Calendar.getInstance().get(Calendar.DAY_OF_MONTH) + day);
        cYear.set(Calendar.HOUR_OF_DAY, 23);
        cYear.set(Calendar.MINUTE, 59);
        cYear.set(Calendar.SECOND, 59);
        return cYear.getTime();
    }

    /**
     * 获取下一周的开始时间
     *
     * @return
     */
    public static Date getStartDateOfNextWeek() {
        Calendar cWeek = new GregorianCalendar();
        cWeek.setFirstDayOfWeek(Calendar.MONDAY);
        cWeek.set(Calendar.WEEK_OF_YEAR,  Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) +1);
        cWeek.set(Calendar.DAY_OF_WEEK, cWeek.getFirstDayOfWeek()); // Monday
        cWeek.set(Calendar.HOUR_OF_DAY, 0);
        cWeek.set(Calendar.MINUTE, 0);
        cWeek.set(Calendar.SECOND, 0);
        return cWeek.getTime();
    }

    /**
     * 获取下一周的结束时间
     *
     * @return
     */
    public static Date getEndDateOfNextWeek(int week) {
        Calendar cWeek = new GregorianCalendar();
        cWeek.setFirstDayOfWeek(Calendar.MONDAY);
        cWeek.set(Calendar.WEEK_OF_YEAR,  Calendar.getInstance().get(Calendar.WEEK_OF_YEAR) + week);
        cWeek.set(Calendar.DAY_OF_WEEK, cWeek.getFirstDayOfWeek()+6); // Monday
        cWeek.set(Calendar.HOUR_OF_DAY, 23);
        cWeek.set(Calendar.MINUTE, 59);
        cWeek.set(Calendar.SECOND, 59);
        return cWeek.getTime();
    }

    /**
     * 获取下一月的开始时间
     *
     * @return
     */
    public static Date getStartDateOfNextMonth() {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cYear.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) +1);
        cYear.set(Calendar.DAY_OF_MONTH, cYear.getActualMinimum(Calendar.DATE));
        cYear.set(Calendar.HOUR_OF_DAY, 0);
        cYear.set(Calendar.MINUTE, 0);
        cYear.set(Calendar.SECOND, 0);
        return cYear.getTime();
    }

    /**
     * 获取下一月的结束时间
     *
     * @return
     */
    public static Date getEndDateOfNextMonth(int month) {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cYear.set(Calendar.MONTH, Calendar.getInstance().get(Calendar.MONTH) + month);
        cYear.set(Calendar.DAY_OF_MONTH, cYear.getActualMaximum(Calendar.DATE));
        cYear.set(Calendar.HOUR_OF_DAY, 23);
        cYear.set(Calendar.MINUTE, 59);
        cYear.set(Calendar.SECOND, 59);
        return cYear.getTime();
    }

    /**
     * 获取下一季度的开始时间
     *
     * @return
     */
    public static Date getStartDateOfNextQuarter() {
        Calendar cMonth = Calendar.getInstance();
        int m = cMonth.get(Calendar.MONTH) + 1;
        int month = 1;
        if (m >= 1 && m <= 3) {
            month = 1;
        }
        if (m >= 4 && m <= 6) {
            month = 4;
        }
        if (m >= 7 && m <= 9) {
            month = 7;
        }
        if (m >= 10 && m <= 12) {
            month = 10;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.MONTH, month - 1 + 3);
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取下一季度的结束时间
     *
     * @return
     */
    public static Date getEndDateOfNextQuarter(int quarter) {
        Calendar cMonth = Calendar.getInstance();
        int m = cMonth.get(Calendar.MONTH) + 1;
        int month = 1;
        if (m >= 1 && m <= 3) {
            month = 3;
        }
        if (m >= 4 && m <= 6) {
            month = 6;
        }
        if (m >= 7 && m <= 9) {
            month = 9;
        }
        if (m >= 10 && m <= 12) {
            month = 12;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR));
        cal.set(Calendar.MONTH, month - 1 + (quarter * 3));
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DATE));
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }


    /**
     * 获取下一年的开始时间
     *
     * @return
     */
    public static Date getStartDateOfNextYear() {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) + 1);
        cYear.set(Calendar.MONTH, 0);
        cYear.set(Calendar.DATE, 1);
        cYear.set(Calendar.HOUR_OF_DAY, 0);
        cYear.set(Calendar.MINUTE, 0);
        cYear.set(Calendar.SECOND, 0);
        return cYear.getTime();
    }

    /**
     * 获取下一年的结束时间
     *
     * @return
     */
    public static Date getEndDateOfNextYear(int year) {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, Calendar.getInstance().get(Calendar.YEAR) + year);
        cYear.set(Calendar.MONTH, 11);
        cYear.set(Calendar.DATE, 31);
        cYear.set(Calendar.HOUR_OF_DAY, 23);
        cYear.set(Calendar.MINUTE, 59);
        cYear.set(Calendar.SECOND, 59);
        return cYear.getTime();
    }

    /**
     * 获取具体一年的开始时间
     *
     * @return
     */
    public static Date getStartDateOfYear(Integer year) {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, year);
        cYear.set(Calendar.MONTH, 0);
        cYear.set(Calendar.DATE, 1);
        cYear.set(Calendar.HOUR_OF_DAY, 0);
        cYear.set(Calendar.MINUTE, 0);
        cYear.set(Calendar.SECOND, 0);
        return cYear.getTime();
    }

    /**
     * 获取具体一年的结束时间
     *
     * @return
     */
    public static Date getEndDateOfYear(Integer year) {
        Calendar cYear = Calendar.getInstance();
        cYear.set(Calendar.YEAR, year);
        cYear.set(Calendar.MONTH, 11);
        cYear.set(Calendar.DATE, 31);
        cYear.set(Calendar.HOUR_OF_DAY, 23);
        cYear.set(Calendar.MINUTE, 59);
        cYear.set(Calendar.SECOND, 59);
        return cYear.getTime();
    }


    public static void main(String[] args) {
		Date currentDate = new Date();
		System.out.println(formatDate(DateUtils.getStartDateOfNextQuarter(),"yyyy-MM-dd HH:mm:ss"));
		System.out.println(formatDate(DateUtils.getEndDateOfNextQuarter(4),"yyyy-MM-dd HH:mm:ss"));
	}
}
