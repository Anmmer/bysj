package com.yinhai.bysj.basicdata.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间日期工具类
 *
 */
public class DateUtil {
    /**
     * 默认日期格式 //Timestamp format must be yyyy-MM-dd hh:mm:ss[.fffffffff]
     */
    private static final String DEFAULT_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 默认构造函数
     */
    private DateUtil() {
    }

    /**
     * 字符串转换成日期 如果转换格式为空，则利用默认格式进行转换操作
     *
     * @param str    字符串
     * @param format 日期格式
     * @return 日期
     * @throws ParseException
     */
    public static Date str2Date(String str, String format) {
        if (null == str || "".equals(str)) {
            return null;
        }
        // 如果没有指定字符串转换的格式，则用默认格式进行转换
        if (null == format || "".equals(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * java.util.Date转java.sql.Date
     *
     * @param date
     * @return
     */
    public static java.sql.Date date2Sql(Date date) {
        if (date != null) {
            if (date instanceof java.sql.Date) {
                return (java.sql.Date) date;
            }
            return new java.sql.Date(date.getTime());
        }
        return null;
    }

    /**
     * 日期转时间戳
     *
     * @param date
     * @return
     */
    public static Timestamp date2Timestamp(Date date) {
        if (date != null) {
            return new Timestamp(date.getTime());
        }
        return null;
    }

    /**
     * 日期转换为字符串
     *
     * @param date   日期
     * @param format 日期格式
     * @return 字符串
     */
    public static String date2Str(Date date, String format) {
        if (null == date) {
            return null;
        }
        if (ObjectUtil.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    public static String date2Str(Date date) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_FORMAT);
        return sdf.format(date);
    }

    /**
     * 时间戳转换为字符串
     *
     * @param time
     * @return
     */
    public static String timestamp2Str(Timestamp time) {
        if (ObjectUtil.isEmpty(time)) {
            return null;
        }
        Date date = new Date(time.getTime());
        return date2Str(date, DEFAULT_FORMAT);
    }

    public static String timestamp2Str(Timestamp time, String format) {
        if (ObjectUtil.isEmpty(time)) {
            return null;
        }
        Date date = new Date(time.getTime());
        return date2Str(date, format);
    }

    /**
     * 时间戳转日期
     *
     * @param time
     * @return
     */
    public static Date timestamp2Date(Timestamp time) {
        Date date = null;
        if (null != time) {
            date = new Date(time.getTime());
        }
        return date;
    }

    /**
     * 字符串转换时间戳
     *
     * @param str
     * @return
     */
    public static Timestamp str2Timestamp(String str) {
        Date date = str2Date(str, DEFAULT_FORMAT);
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        return new Timestamp(date.getTime());//NOSONAR  已判断null
    }

    /**
     * 字符串转换时间戳
     *
     * @param str
     * @param format
     * @return
     */
    public static Timestamp str2Timestamp(String str, String format) {
        Date date = str2Date(str, format);
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        return new Timestamp(date.getTime());//NOSONAR  已判断null
    }

    /**
     * 打印日期字符格式（yyyy-MM-dd hh:mm:ss）
     * 毫秒数转日期字符串
     *
     * @param longValue
     * @return
     */
    public static String long2Str(Long longValue) {
        SimpleDateFormat format = new SimpleDateFormat(DEFAULT_FORMAT);
        return format.format(parseDate(longValue));
    }

    public static String long2Str(Long longValue, String format) {
        SimpleDateFormat sFormat = new SimpleDateFormat(format);
        return sFormat.format(parseDate(longValue));
    }

    /**
     * 毫秒数转时间戳
     *
     * @param longValue
     * @return
     */
    public static Timestamp long2Timestamp(Long longValue) {
        return new Timestamp(longValue);
    }

    /**
     * long解析为java.util.Date
     *
     * @param longValue
     * @return
     */
    public static Date parseDate(long longValue) {
        return new Date(longValue);
    }

    /**
     * 判断两个java.util.Date是否为同一天
     *
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isSameDay(Date date1, Date date2) {
        return date1.getDate() == date2.getDate();
    }

    /**
     * 计算两个long（秒）之间相差的天数
     *
     * @param longValue1
     * @param longValue2
     * @return
     */
    public static int getBetweenDays(long longValue1, long longValue2) {
        long betweenDays = ((longValue1 - longValue2) / 1000) / (60 * 60 * 24);
        return Math.abs((int) betweenDays);
    }

    /**
     * 计算2个时间戳之间的天数（忽略时分秒）
     *
     * @param t1
     * @param t2
     * @return
     */
    public static int getBetweenDays(Timestamp t1, Timestamp t2) {
        if (ObjectUtil.isEmpty(t1) || ObjectUtil.isEmpty(t2)) {
            return 0;
        }
        Timestamp smaller = null;
        Timestamp bigger = null;
        if (t1.before(t2)) {
            smaller = t1;
            bigger = t2;
        } else {
            smaller = t2;
            bigger = t1;
        }
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(timestamp2Date(smaller));
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(timestamp2Date(bigger));
        int day1 = cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if (year1 != year2) {
            int timeDistance = 0;
            for (int i = year1; i < year2; i++) {
                if (i % 4 == 0 && i % 100 != 0 || i % 400 == 0)    //闰年
                {
                    timeDistance += 366;
                } else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            return timeDistance + (day2 - day1);
        } else {
            return day2 - day1;
        }
    }

    /**
     * @param time1 yyyy-MM-dd
     * @param time2 yyyy-MM-dd
     * @return
     * @Description: 计算2个日期Str的天数
     * @update: 2014-1-8 by huangyh
     */
    public static int getBetweenDays(String time1, String time2) {
        if (ObjectUtil.isEmpty(time1) || ObjectUtil.isEmpty(time2)) {
            return 0;
        }
        long betweenDays = 0;
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date1 = ft.parse(time1);
            Date date2 = ft.parse(time2);
            betweenDays = date1.getTime() - date2.getTime();
            betweenDays = betweenDays / 1000 / 60 / 60 / 24;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return Math.abs((int) betweenDays);
    }

    /**
     * sql.Date转化为sql.Timestamp
     *
     * @param date
     * @return
     */
    @Deprecated
    public static Timestamp convertDate2Timestamp(Date date) {
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        return new Timestamp(date.getTime());
    }

    /**
     * @param date
     * @param days
     * @return
     * @Description: 获取下一天
     * @update: 2016年1月5日 by huangyh
     */
    @Deprecated
    public static Timestamp getNextDay(Timestamp date, int days) {
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calendar.DATE, days);// 把日期往后增加一天.整数往后推,负数往前移动
        return convertDate2Timestamp(calendar.getTime());
    }

    /**
     * @param date1
     * @param date2
     * @param format
     * @return
     * @Description: 比较两个日期的大小
     * @update: 2014年8月4日 by huangyh
     */
    @Deprecated
    public static int compareDate(String date1, String date2, String format) {
        if (ObjectUtil.isEmpty(date1) || ObjectUtil.isEmpty(date2)) {
            return 0;
        }
        if (ObjectUtil.isEmpty(format)) {
            format = DEFAULT_FORMAT;
        }
        DateFormat df = new SimpleDateFormat(format);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date2);
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static int compareDate(Timestamp t1, Timestamp t2) {
        if (t1 == null || t2 == null) {
            return 0;
        }
        if (t1.getTime() > t2.getTime()) {
            return 1;
        }
        return -1;
    }

    /**
     * 获取下个日期
     *
     * @param date
     * @param days
     * @return
     */
    public static Date addDay(Date date, int days) {
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    /**
     * 获取下个时间戳
     *
     * @param date
     * @param days
     * @return
     */
    public static Timestamp addDay(Timestamp date, int days) {
        if (ObjectUtil.isEmpty(date)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp2Date(date));
        cal.add(Calendar.DATE, days);
        return date2Timestamp(cal.getTime());
    }

    /**
     * 获取下个时间戳
     *
     * @param t
     * @param months
     * @return
     */
    public static Timestamp addMonth(Timestamp t, int months) {
        if (ObjectUtil.isEmpty(t)) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(timestamp2Date(t));
        cal.add(Calendar.MONTH, months);
        return date2Timestamp(cal.getTime());
    }

    /**
     * 获取指定月份的最大天数
     *
     * @param year
     * @param month
     * @return
     */
    public static int getMonthMaxDay(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取指定月份的最大日期
     *
     * @param year
     * @param month
     * @return
     */
    public static Timestamp getMonthMaxDate(int year, int month) {
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR, year);
        //设置月份
        cal.set(Calendar.MONTH, month - 1);
        int maxDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, maxDay);
        return date2Timestamp(cal.getTime());
    }

    /**
     * 获取某一天的0点0分0秒
     *
     * @param t
     * @return
     */
    public static Timestamp getPointZero(Timestamp t) {
        if (ObjectUtil.isEmpty(t)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        //获取某一天的0点0分0秒
        calendar.setTime(timestamp2Date(t));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                0, 0, 0);
        return date2Timestamp(calendar.getTime());
    }

    public static Timestamp getPointZero(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 0, 0, 0);
        return date2Timestamp(calendar.getTime());
    }

    /**
     * 获取某一天23点59分59秒
     *
     * @param t
     * @return
     */
    public static Timestamp getPoint24(Timestamp t) {
        if (ObjectUtil.isEmpty(t)) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        //23点59分59秒
        calendar.setTime(timestamp2Date(t));
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
                23, 59, 59);
        return date2Timestamp(calendar.getTime());
    }

    public static Timestamp getPoint24(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, day, 23, 59, 59);
        return date2Timestamp(calendar.getTime());
    }

    /**
     * 根据生日和当前时间计算年龄
     *
     * @param birthday
     * @param nowday
     * @return
     * @Description
     */
    public static Integer getAgeByBirth(Timestamp birthday, Timestamp nowday) {
        if (ObjectUtil.isEmpty(birthday) || ObjectUtil.isEmpty(nowday)) {
            return null;
        }
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(nowday);// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {// 如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {// 兼容性更强,异常后返回数据
            return 0;
        }
    }

    /**
     * 计算年龄
     *
     * @param birthday
     * @return
     */
    public static int getAge(Date birthday) {
        if (ObjectUtil.isEmpty(birthday)) {
            return 0;
        }
        try {
            Calendar cal = Calendar.getInstance();
            if (cal.before(birthday)) {
                return 0;
            }
            int yearNow = cal.get(Calendar.YEAR);
            int monthNow = cal.get(Calendar.MONTH);
            int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
            cal.setTime(birthday);

            int yearBirth = cal.get(Calendar.YEAR);
            int monthBirth = cal.get(Calendar.MONTH);
            int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

            int age = yearNow - yearBirth;

            if (monthNow <= monthBirth) {
                if (monthNow == monthBirth) {
                    if (age > 0) {// 原本计算年龄这里不需要等于，现特殊需求，同月同天算在内，即跨年的时候才这样
                        if (dayOfMonthNow <= dayOfMonthBirth) {
                            age--;
                        }
                    }
                } else {
                    age--;
                }
            }
            return age;
        } catch (Exception e) {
            return 0;
        }
    }

    public static void main(String[] args) {
        Timestamp t1 = getPoint24(2019, 9, 27);
        Timestamp t2 = getPoint24(2017, 8, 27);
        System.out.println(getBetweenDays(t1, t2));//NOSONAR  已判断null
    }

}
