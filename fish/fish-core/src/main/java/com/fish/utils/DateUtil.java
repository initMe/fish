package com.fish.utils;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author fish
 * @date 2016/5/24
 */
public class DateUtil {

    private final static Logger _log = Logger.getLogger(DateUtil.class);

    private final static String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String format(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_PATTERN);
        return dateFormat.format(date);
    }

    public static String format(Date date, String pattern){
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static Date parse(String str) {
        Date dateTemp = null;
        SimpleDateFormat formater = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            dateTemp = formater.parse(str);
        } catch (Exception e) {
            _log.error("exception in convert string to date!");
        }
        return dateTemp;
    }

    public static Date parse(String str, String pattern) {
        Date dateTemp = null;
        SimpleDateFormat formater = new SimpleDateFormat(pattern);
        try {
            dateTemp = formater.parse(str);
        } catch (Exception e) {
            _log.error("exception in convert string to date!");
        }
        return dateTemp;
    }

    /**
     * 取得从startDate开始的前(正)/后(负)day天
     *
     * @param startDate
     * @param day
     * @return
     */
    public static Date getRelativeDate(Date startDate, int day) {
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(startDate);
            calendar.add(Calendar.DAY_OF_MONTH, day);
            return calendar.getTime();
        } catch (Exception e) {
            _log.error(e);
            return startDate;
        }
    }

    /**
     * 根据日期获取星期几
     *
     * @param date java.util.Date对象,不能为null
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 计算当前日期到结束日期的天小时分钟
     *
     * @param endDate
     * @return
     */
    public static String getCountDays(Date endDate) {
        Date now = new Date();
        String returnString = "";
        try {
            long l = endDate.getTime() - now.getTime();
            if (l < 0) {
                return "已结束";
            }
            long day = l / (24 * 60 * 60 * 1000);
            if (day != 0) {
                returnString = day + "天";
            }
            long hour = (l / (60 * 60 * 1000) - day * 24);
            long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
            returnString = returnString + hour + "小时" + min + "分";
        } catch (Exception e) {
            return returnString;
        }
        return returnString;
    }
}
