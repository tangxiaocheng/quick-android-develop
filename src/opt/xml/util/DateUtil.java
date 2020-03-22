package opt.xml.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import tangxiaocheng.log.MyDebug;

/**
 * @author Randy
 *     <p>this is a lot of static method for operate the date or date by milliseconds we can use
 *     this to get a month start time and end time by millisecond easily
 */
public class DateUtil {

  private static final long DAY_IN_MILLISECOND = 24 * 60 * 60 * 1000l;

  private static final SimpleDateFormat simpleDateFormat =
      new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");

  /**
   * get the specific month first and last millisecond array by parameter date time
   *
   * @param date the date time ,could be date string or date in millisecond ,like this : 2001-4-23
   *     3:33:44 or 1343836800000l
   * @return long array ,it's two child :firstMillisecond and lastMillisecond
   */
  public static long[] getMonthMillisencondSet(Object date) {
    long firstMillisecond = 0, lastMillisecond = 0;
    String dateString = null;
    if (date instanceof String) {
      dateString = (String) date;
    }
    if (date instanceof Long) {
      dateString = getDateStringByMillisecond((Long) date);
    }
    try {
      firstMillisecond = formatDate(dateString, new SimpleDateFormat("yyyy-MM"));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    lastMillisecond = firstMillisecond - 1l + getMonth_DaysByDate(date) * DAY_IN_MILLISECOND;
    return new long[] {firstMillisecond, lastMillisecond};
  }

  /**
   * get a specific time's days
   *
   * @param date the time should be computer ,String or long
   * @return days the days by the given date
   */
  public static int getMonth_DaysByDate(Object date) {
    int days;
    long milliseconds = -1;
    Calendar calendar = Calendar.getInstance();
    if (date instanceof String) {
      milliseconds = getMillisecondByDateString((String) date);
    }
    if (date instanceof Long) {
      milliseconds = (Long) date;
    }
    calendar.setTimeInMillis(milliseconds);
    days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    MyDebug.printI(date + "当月有：" + days + " 天");
    return days;
  }

  /**
   * this mothod's function is get millisecond by a date string if inner exception happens ,it's
   * return -1l
   *
   * @param dateString the date string need be convert millisecond ,is like this "2010-09-06
   *     20:33:18 "
   * @return the millisecond convert by date string ,like this 1283776398000
   */
  private static Long getMillisecondByDateString(String dateString) {
    Long millisecond = -1l;
    try {
      millisecond = formatDate(dateString, simpleDateFormat);
    } catch (ParseException e) {
    }
    MyDebug.printI(millisecond + "");
    return millisecond;
  }

  private static Long formatDate(String dateString, SimpleDateFormat simpleDateFormat)
      throws ParseException {
    Date date;
    Long millisecond;
    date = simpleDateFormat.parse(dateString);
    millisecond = date.getTime();
    return millisecond;
  }

  /**
   * get a date string by millisecond
   *
   * @param millisecond the millisecond need be convert
   * @return a date string : should be like this :2012-09-06 22:34:33
   */
  public static String getDateStringByMillisecond(long millisecond) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(millisecond);
    String dateString = simpleDateFormat.format(calendar.getTime());
    MyDebug.printI(dateString);
    return dateString;
  }

  private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
  /**
   * get today
   *
   * @return today
   */
  public static String getNowDay() {
    return format.format(new Date());
  }

  public static String getWeekDay(String dateString) {
    SimpleDateFormat formatD = new SimpleDateFormat("E"); // "E"表示"day in week"
    Date date = null;
    String weekDay = "";
    try {
      date = format.parse(dateString); // 将String 转换为符合格式的日期
      weekDay = formatD.format(date);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return weekDay;
  }

  public static void main(String[] args) {
    System.out.println(getWeekDay("2015-3-22").replace("星期", "周"));
  }
}
