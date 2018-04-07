package com.example.slope.androiddriver.utils;

/**
 * Created by zhou on 2018/4/6.
 */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
public final class DateUtil {
    private DateUtil() {
    }

    /** 日期格式 **/
    public interface DATE_PATTERN {
        String	YYYY				= "yyyy";
        String	YYYYMM				= "yyyyMM";
        String	YYYY_MM				= "yyyy-MM";
        String	HHMMSS				= "HHmmss";
        String	HH_MM_SS			= "HH:mm:ss";
        String	YYYYMMDD			= "yyyyMMdd";
        String	YYYY_MM_DD			= "yyyy-MM-dd";
        String	YYYYMMDDHHMMSS		= "yyyyMMddHHmmss";
        String	YYYYMMDDHHMMSSSSS	= "yyyyMMddHHmmssSSS";
        String	YYYY_MM_DD_HH_MM_SS	= "yyyy-MM-dd HH:mm:ss";
        String  CNY_DATE = "yyyy年MM月dd日";
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static final String format(Object date) {
        return format(date, DATE_PATTERN.YYYY_MM_DD);
    }

    public static final String format2(Object date) {
        return format(date, DATE_PATTERN.YYYYMM);
    }
    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     */
    public static final String format(Object date, String pattern) {
        if (date == null) {
            return null;
        }
        if (pattern == null) {
            return format(date);
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取日期
     *
     * @return
     */
    public static final String getDate() {
        return format(new Date());
    }

    /**
     * 获取日期时间
     *
     * @return
     */
    public static final String getDateTime() {
        return format(new Date(), DATE_PATTERN.YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * 获取日期时间
     *
     * @return
     */
    public static final String getTime() {
        return format(new Date(), DATE_PATTERN.YYYYMMDDHHMMSS);
    }

    /**
     * 获取日期
     *
     * @param pattern
     * @return
     */
    public static final String getDateTime(String pattern) {
        return format(new Date(), pattern);
    }

    /**
     * 日期计算
     *
     * @param date
     * @param field
     * @param amount
     * @return
     */
    public static final Date addDate(Date date, int field, int amount) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }


    /**
     * 字符串转换为日期:YYYY-MM-DD 00:00;00
     *
     * @param date
     * @return
     */
    public static final Date stringToDate3(String date) {
        if (date == null) {
            return null;
        }
        String separator = String.valueOf(date.charAt(4));
        String pattern = "yyyy-mm-dd 00:00:00";
        if (!separator.matches("\\d*")) {
            pattern = "yyyy" + separator + "MM" + separator + "dd";
            if (date.length() < 10) {
                pattern = "yyyy" + separator + "M" + separator + "d";
            }
        } else if (date.length() < 8) {
            pattern = "yyyy-mm-dd 00:00:00";
        }
        pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    /**
     * 字符串转换为日期:YYYY-MM
     *
     * @param date
     * @return
     */
    public static final Date stringToDate5(String date) {
        if (date == null) {
            return null;
        }
        String separator = String.valueOf(date.charAt(4));
        String pattern = "yyyy-mm";
        if (!separator.matches("\\d*")) {
            pattern = "yyyy" + separator + "MM";
            if (date.length() < 10) {
                pattern = "yyyy" + separator + "MM";
            }
        } else if (date.length() < 8) {
            pattern = "yyyy-mm";
        }
        pattern = pattern.substring(0, Math.min(pattern.length(), date.length()));
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 间隔天数
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getDayBetween(Date startDate, Date endDate) {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        end.set(Calendar.HOUR_OF_DAY, 0);
        end.set(Calendar.MINUTE, 0);
        end.set(Calendar.SECOND, 0);
        end.set(Calendar.MILLISECOND, 0);

        long n = end.getTimeInMillis() - start.getTimeInMillis();
        return (int) (n / (60 * 60 * 24 * 1000l));
    }

    /**
     * 间隔月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthBetween(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || !startDate.before(endDate)) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int year1 = start.get(Calendar.YEAR);
        int year2 = end.get(Calendar.YEAR);
        int month1 = start.get(Calendar.MONTH);
        int month2 = end.get(Calendar.MONTH);
        int n = (year2 - year1) * 12;
        n = n + month2 - month1;
        return n;
    }

    /**
     * 间隔月，多一天就多算一个月
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static final Integer getMonthBetweenWithDay(Date startDate, Date endDate) {
        if (startDate == null || endDate == null || !startDate.before(endDate)) {
            return null;
        }
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        int year1 = start.get(Calendar.YEAR);
        int year2 = end.get(Calendar.YEAR);
        int month1 = start.get(Calendar.MONTH);
        int month2 = end.get(Calendar.MONTH);
        int n = (year2 - year1) * 12;
        n = n + month2 - month1;
        int day1 = start.get(Calendar.DAY_OF_MONTH);
        int day2 = end.get(Calendar.DAY_OF_MONTH);
        if (day1 <= day2) {
            n++;
        }
        return n;
    }

    /**
     * 得到几天前的时间
     *
     * @param d 计算基准日期
     * @param day 前几天
     * @return 返回日期
     */
    public static Date getDateBefore(Date d, int day) {
        Calendar now = Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);
        return now.getTime();
    }

    /**
     * 或者当月的天数
     *
     * @param date 当月随意日期
     * @return 返回天数
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /***
     * 日期月份加几个月 months
     *
     * @param datetime
     *            日期(2017-11)
     * @return 2017-10
     */
    public static String AddMonth(String datetime, int months) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = null;
        try {
            date = sdf.parse(datetime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cl = Calendar.getInstance();
        cl.setTime(date);
        cl.add(Calendar.MONTH, months);
        date = cl.getTime();
        return sdf.format(date);
    }
    /**
     *
     根据传入的日期 计算当月覆盖天数
     * <dt>2017年10月22日 caoz 修改备注：</dt>
     * <dd>新建</dd>
     * </dl>
     * @param startDate
     * @param endDate
     * @param periodMonth
     * @return
     */
    public static int countCoverageDays(Date startDate,Date endDate,String periodMonth) throws Exception{
        if(startDate != null && endDate != null && periodMonth != null && !"".equals(periodMonth)){
            List<String> equalsList = new ArrayList<String>();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            List<Date> listDate = getDatesBetweenTwoDate(startDate, endDate);
            for(int i=0;i<listDate.size();i++){
                String dateStr = new SimpleDateFormat("yyyyMM").format(listDate.get(i));
                if(dateStr.equals(periodMonth)){
                    equalsList.add(sdf.format(listDate.get(i)));
                }
            }


            return equalsList.size();
        }else{
            return 0;
        }


    }




    /**
     *
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return 日期集合 格式为 年-月
     */
    public static List<String> getMonthBetweenPeriod(Date startDate, Date endDate) {
        ArrayList<String> result = new ArrayList<String>();

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(startDate);

        max.setTime(endDate);

        Calendar curr = min;
        while (curr.before(max)) {
            result.add(format(curr.getTime(), DATE_PATTERN.YYYYMM));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     *
     * @param start 开始时间
     * @param end 结束时间
     * @return 日期集合 格式为 年-月
     */
    public static List<String> getBetweenDates(Date start, Date end) {
        List<String> result = new ArrayList<String>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
        tempStart.add(Calendar.DAY_OF_YEAR, 1);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd)) {
            result.add(format(tempStart.getTime(), DATE_PATTERN.YYYYMM));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        HashSet hs  =   new  HashSet(result);
        result.clear();
        result.addAll(hs);
        Collections.sort(result);
        return result;
    }
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s1="2017-09-15";
        String s2="2017-11-15";
        List<String>  list =getBetweenDates(sdf.parse(s1),sdf.parse(s2));

        System.out.println(list);

    }

    /**
     *
     * @param startDate 开始时间
     * @param endDate 结束时间  结束时间加一个月
     * @return 日期集合 格式为 年-月
     */
    public static List<String> getMonthBetweenPeriodAddOneMonth(Date startDate, Date endDate) {
        ArrayList<String> result = new ArrayList<String>();

        Calendar min = Calendar.getInstance();
        Calendar max = Calendar.getInstance();

        min.setTime(startDate);

        max.setTime(endDate);
        max.add(Calendar.MONTH, 1);
        Calendar curr = min;
        while (curr.before(max)) {
            result.add(format(curr.getTime(), DATE_PATTERN.YYYYMM));
            curr.add(Calendar.MONTH, 1);
        }
        return result;
    }

    /**
     * 取本月第一天
     * 2017年11月2日 zhangzh 修改备注：
     * @param date  2017年11月2日 20:00:42
     * @return 2017-11-01
     */
    public static String getBeginDayOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return format(cale.getTime());
    }

    /**
     * 取本月最后一天
     * 2017年11月2日 zhangzh 修改备注：
     * @param date  2017年11月2日 20:01:47
     * @return 2017-11-30
     */
    public static String getEndDayOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return format(cale.getTime());
    }

    /**
     * 取本月第一天
     * 2017年11月2日 zhangzh 修改备注：
     * @param date  2017年11月2日 20:00:42
     * @return 2017-11-01
     */
    public static Date getBeginDateOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        return cale.getTime();
    }

    /**
     * 取本月最后一天
     * 2017年11月2日 zhangzh 修改备注：
     * @param date  2017年11月2日 20:01:47
     * @return 2017-11-30
     */
    public static Date getEndDateOfMonth(Date date) {
        Calendar cale = Calendar.getInstance();
        cale.setTime(date);
        cale.add(Calendar.MONTH, 1);
        cale.set(Calendar.DAY_OF_MONTH, 0);
        return cale.getTime();
    }




    /**
     * 根据开始时间和结束时间返回时间段内的时间集合
     *
     * @param beginDate
     * @param endDate
     * @return List
     */
    public static List<Date> getDatesBetweenTwoDate(Date beginDate, Date endDate) {
        List<Date> lDate = new ArrayList<Date>();
        lDate.add(beginDate);// 把开始时间加入集合
        Calendar cal = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        cal.setTime(beginDate);
        boolean bContinue = true;
        while (bContinue) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            cal.add(Calendar.DAY_OF_MONTH, 1);
            // 测试此日期是否在指定日期之后
            if (endDate.after(cal.getTime())) {
                lDate.add(cal.getTime());
            } else {
                break;
            }
        }
        lDate.add(endDate);// 把结束时间加入集合
        return lDate;
    }
    /**
     * @author qindc@china.kjlink.com
     * @date 2017/11/6 14:20
     * @Description   返回时间区间中符合指定会计期的时间集合
     * @param   beginDate  开始日期
     * @param   endDate   结束日期
     * @param   period    会计期 格式：201710
     * @return   返回日期集合
     *
     */
    public static List<Date> getDateListByPeriod(Date beginDate, Date endDate, String period) {
        List<Date> list = getDatesBetweenTwoDate(beginDate, endDate);
        List<Date> newList = new ArrayList<>();
        for (Date date : list) {
            if (format(date, DATE_PATTERN.YYYYMM).equals(period)) {
                newList.add(date);
            }
        }
        return newList;
    }

}
