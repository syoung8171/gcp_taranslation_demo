package com.trans.agent.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.regex.Pattern;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class DateUtil.
 *
 * @author
 * @version 1.0
 * @see <pre>
 * (Modification Information)
 *
 *
 * -----------  --------  ---------------------------
 * 2014. 12. 9   Ʈ
 *
 * </pre>
 * @since 2014. 12. 9
 */

public class DateUtil
{
	/** The instance. */
    private volatile static DateUtil instance;

    /**
	 * Gets the single instance of CryptUtil.
	 *
	 * @return single instance of CryptUtil
	 */

    public static DateUtil inst() {
		if(instance == null) {
			synchronized (DateUtil.class) {
				if(instance == null) {
					instance = new DateUtil();
				}
			}
		}
		return instance;
	}


    /**
	 * Gets the time.
	 *
	 * @return the time
	 */
    public String getTime() {
		return getTime("yyyyMMddHHmmss");
	}

    /**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return getTime("yyyyMMdd");
	}

    /**
	 * Gets the time.
	 *
	 * @param format
	 *            the format
	 * @return the time
	 */
	public String getTime(String format)	{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

    /**
	 * String to date.
	 *
	 * @param dateString
	 *            the date string
	 * @param format
	 *            the format
	 * @return the date
	 */
	public Date stringToDate(String dateString, String format) {

		Date d = null;
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
			d= sdf.parse(dateString, pos);
		}catch(Exception e){
			d = new Date();
		}

		return d;
	}

	public Date dbStringToDate(String dateString) {
		String format = "yyyy-MM-dd HH:mm:ss";
		Date d = null;
		try{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
			d= sdf.parse(dateString, pos);
		}catch(Exception e){
			d = new Date();
		}

		return d;
	}

  	/**
		 * Checks if is date.
		 *
		 * @param date
		 *            the date
		 * @return true, if is date
		 */
  	public boolean isDate(CharSequence date) {

		    // some regular expression
		    String time = "(\\s(([01]?\\d)|(2[0123]))[:](([012345]\\d)|(60))"
		        + "[:](([012345]\\d)|(60)))?"; // with a space before, zero or one time

		    // no check for leap years (Schaltjahr)
		    // and 31.02.2006 will also be correct
		    String day = "(([12]\\d)|(3[01])|(0?[1-9]))"; // 01 up to 31
		    String month = "((1[012])|(0\\d))"; // 01 up to 12
		    String year = "\\d{4}";

		    // define here all date format
		    ArrayList<Pattern> patterns = new ArrayList<Pattern>();
		    patterns.add(Pattern.compile(day + "[-.:]" + month + "[-.:]" + year + time));
		    patterns.add(Pattern.compile(year + "[-.:]" + month + "[-.:]" + day + time));
		    // here you can add more date formats if you want

		    // check dates
		    for (Pattern p : patterns)
		      if (p.matcher(date).matches())
		        return true;

		    return false;

	}

    /**
	 * String to calendar.
	 *
	 * @param dateString
	 *            the date string
	 * @param format
	 *            the format
	 * @return the calendar
	 */
	public Calendar stringToCalendar(String dateString, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
		Date date = sdf.parse(dateString, pos);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

    /**
	 * Format date.
	 *
	 * @param date
	 *            the date
	 * @param format
	 *            the format
	 * @return the string
	 */
	public String formatDate(Date date, String format) {
		if (date == null) return "";

		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return sdf.format(cal.getTime());
	}

	public Date formatDate(int date, String format) {
		if (date < 0) return null;

		int year = date / 10000;
		int month = ((date % 10000) / 100) -1;
		int day = date % 100;

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, day);
		Date dt = cal.getTime();
		return dt;
	}

	public Date formatDate(long date, String format) {
		if (date < 0) return null;

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(date);
		Date dt = cal.getTime();
		return dt;
	}

	public Date formatDate(String date, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public String formatDate(Date date) {
		if (date == null) return "";
		String format = "yyyy-MM-dd HH:mm:ss";

		return formatDate(date, format);
	}

    /**
	 * Format calendar.
	 *
	 * @param cal
	 *            the cal
	 * @param format
	 *            the format
	 * @return the string
	 */
	public String formatCalendar(Calendar cal, String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(cal.getTime());
	}

	public String formatCalendar(Calendar cal) {
		String format = "yyyy-MM-dd HH:mm:ss";
		return formatCalendar(cal, format);
	}

	/**
	 * Checks if is leap year.(윤년)
	 *
	 * @param year
	 *            the year
	 * @return true, if is leap year
	 */
    public boolean isLeapYear(int year)
    {
        if(year % 4 != 0)
            return false;
        if(year % 400 == 0)
            return true;
        return year % 100 != 0;
    }

    /**
	 * Last date.
	 *
	 * @param year
	 *            the year
	 * @param month
	 *            the month
	 * @return the int
	 */
    public int lastDate(int year, int month)
    {
        int kLastDates[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
		if(month > 12 || month < 0)
			month = 0;

        if(month == 2 && isLeapYear(year))
            return kLastDates[month] + 1;
        else
            return kLastDates[month];
    }


	public int lastyyyyMMdd_yyyy(int yyyy) {
		Date date = yyyy(yyyy);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.MONTH, Calendar.DECEMBER);
		cal.set(Calendar.DAY_OF_MONTH, 31);
		return yyyyMMdd(cal.getTime());
	}

	/**
	 * min diff.
	 *
	 * @param earlierDate
	 *            the earlier date
	 * @param laterDate
	 *            the later date
	 * @param format
	 *            the format
	 * @return the String
	 */
	public String timeDiff(Date earlierDate, Date laterDate) {
		if (earlierDate == null || laterDate == null)
			return null;

		int min_cal = (int) ((laterDate.getTime() - earlierDate.getTime()) / (60 * 1000));
		int second = (int) ((laterDate.getTime() - earlierDate.getTime()) / 1000) % 60;
		String ssecond = String.valueOf(second);
		if (second < 10) {
			ssecond = "0" + ssecond;
		}

		int hour = (int) (min_cal / 60);
		String shour = String.valueOf(hour);
		if (hour < 10) {
			shour = "0" + shour;
		}

		int mininute = min_cal % 60;
		String smininute = String.valueOf(mininute);
		if (mininute < 10) {
			smininute = "0" + smininute;
		}

		String res = shour + ":" + smininute + ":" + ssecond;

		return res;
	}

	public String timeToHHmmdd(long time) {

		int second =  (int)(time / 1000) % 60;
		String ssecond = String.valueOf(second);
		if (second < 10) {
			ssecond = "0" + ssecond;
		}

		int min_cal = (int)time / (60*1000);

		int hour = (int) (min_cal / 60);
		String shour = String.valueOf(hour);
		if (hour < 10) {
			shour = "0" + shour;
		}

		int mininute = (int) (min_cal % 60);
		String smininute = String.valueOf(mininute);
		if (mininute < 10) {
			smininute = "0" + smininute;
		}

		String res = shour + ":" + smininute + ":" + ssecond;

		return res;
	}
	/**
	 *
	 * /** Days diff.
	 *
	 * @param earlierDate
	 *            the earlier date
	 * @param laterDate
	 *            the later date
	 * @param format
	 *            the format
	 * @return the int
	 */
       public int daysDiff( String earlierDate, String laterDate, String format )
       {
           if( earlierDate == null || laterDate == null ) return 0;

           Date d1 = null;
           Date d2 = null;
           try{
   			d1 = stringToDate(earlierDate, format);
   			d2 = stringToDate(laterDate, format);
           }catch(Exception e){
           	return 65530;
           }

           return (int)((d2.getTime() - d1.getTime())/(24*60*60*1000));
       }
       /**
      	 * min diff.
      	 *
      	 * @param earlierDate
      	 *            the earlier date
      	 * @param laterDate
      	 *            the later date
      	 * @param format
      	 *            the format
      	 * @return the int
      	 */
          public int minsDiff( String earlierDate, String laterDate, String format )
          {
              if( earlierDate == null || laterDate == null ) return -1;

              Date d1 = null;
              Date d2 = null;
              try{
      			d1 = stringToDate(earlierDate, format);
      			d2 = stringToDate(laterDate, format);
              }catch(Exception e){
              	return -1;
              }

              return (int)((d2.getTime() - d1.getTime())/(60000));
          }
          /**
           * min diff.
           *
           * @param earlierDate
           *            the earlier date
           * @param laterDate
           *            the later date
           * @param format
           *            the format
           * @return the long
           */
          public long minsDifflong( String earlierDate, String laterDate, String format )
          {
        	  if( earlierDate == null || laterDate == null ) return -1;

        	  Date d1 = null;
        	  Date d2 = null;
        	  try{
        		  d1 = stringToDate(earlierDate, format);
        		  d2 = stringToDate(laterDate, format);
        	  }catch(Exception e){
        		  return -1;
        	  }

        	  return ((d2.getTime() - d1.getTime()));
          }

     /**
		 * Compare date.
		 *
		 * @param date1
		 *            the date1
		 * @param date2
		 *            the date2
		 * @param format
		 *            the format
		 * @return the int
		 */
     public int compareDate( String date1, String date2, String format )
     {
		Calendar c1 = stringToCalendar(date1, format);
		Calendar c2 = stringToCalendar(date2, format);

		return compareDate(c1, c2);
	 }

     /**
		 * Compare date.
		 *
		 * @param date1
		 *            the date1
		 * @param date2
		 *            the date2
		 * @return the int
		 */
     public int compareDate( Date date1, Date date2 )
     {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		return compareDate(c1, c2);
	 }

     public int compareDateSECOND( Date date1, Date date2 )
     {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date1);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(date2);

		double su = Math.abs(c1.getTime().getTime()-c2.getTime().getTime());

		if((int)su/1000==0)
			return 0;
		else
			return -1;
	 }

     /**
		 * Compare date.
		 *
		 * @param cal1
		 *            the cal1
		 * @param cal2
		 *            the cal2
		 * @return the int
		 */
     public int compareDate( Calendar cal1, Calendar cal2 )
     {
		int value = 9;

		if (cal1.before(cal2)) {
			value = -1;
        }
        if (cal1.after(cal2)) {
            value = 1;
        }
        if (cal1.equals(cal2)) {
            value = 0;
        }
		return value;
	 }

	/**
	 * Roll years.
	 *
	 * @param startDate
	 *            the start date
	 * @param years
	 *            the years
	 * @return the java.sql. date
	 */
    public java.sql.Date rollYears( java.util.Date startDate, int years)
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(startDate);
        gc.add(Calendar.YEAR, years);
        return new java.sql.Date(gc.getTime().getTime());
    }

   /**
	 * Roll months.
	 *
	 * @param startDate
	 *            the start date
	 * @param months
	 *            the months
	 * @return the java.sql. date
	 */
    public java.sql.Date rollMonths( java.util.Date startDate, int months )
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(startDate);
        gc.add(Calendar.MONTH, months);
        return new java.sql.Date(gc.getTime().getTime());
    }

   /**
	 * Roll days.
	 *
	 * @param startDate
	 *            the start date
	 * @param days
	 *            the days
	 * @return the java.sql. date
	 */
    public java.sql.Date rollDays( java.util.Date startDate, int days )
    {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(startDate);
        gc.add(Calendar.DATE, days);
        return new java.sql.Date(gc.getTime().getTime());
    }

   /**
	 * Roll hours.
	 *
	 * @param startDate
	 *            the start date
	 * @param hours
	 *            the hours
	 * @return the java.sql. date
	 */
    public java.sql.Date rollHours( java.util.Date startDate, int hours )
    {
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(startDate);
    	gc.add(Calendar.HOUR, hours);
    	return new java.sql.Date(gc.getTime().getTime());
    }

    /**
	 * Roll mins.
	 *
	 * @param startDate
	 *            the start date
	 * @param mins
	 *            the mins
	 * @return the java.sql. date
	 */
    public java.sql.Date rollMins( java.util.Date startDate, int mins )
    {
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(startDate);
    	gc.add(Calendar.MINUTE, mins);
    	return new java.sql.Date(gc.getTime().getTime());
    }

    public java.sql.Date rollMinsEx( java.util.Date startDate, int mins ) {
    	final long ONE_MINUTE_IN_MILLIS = 60000;

    	long curTimeInsMs = startDate.getTime();

    	return new java.sql.Date(curTimeInsMs + (mins * ONE_MINUTE_IN_MILLIS));
    }

    /**
	 * Roll secs.
	 *
	 * @param startDate
	 *            the start date
	 * @param secs
	 *            the secs
	 * @return the java.sql. date
	 */
    public java.sql.Date rollSecs( java.util.Date startDate, int secs )
    {
    	GregorianCalendar gc = new GregorianCalendar();
    	gc.setTime(startDate);
    	gc.add(Calendar.SECOND, secs);
    	return new java.sql.Date(gc.getTime().getTime());
    }

    /**
	 * Gets the tomorrow.
	 *
	 * @return the tomorrow
	 */
    public String getTomorrow() {
    	return getDate(1, "yyyyMMdd");
    }

    /**
	 * Gets the today.
	 *
	 * @return the today
	 */
    public Date getToday()	{
		Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		return today;
	}

    /**
	 * Gets the tomorrow.
	 *
	 * @param format
	 *            the format
	 * @return the tomorrow
	 */
    public String getTomorrow(String format) {
    	return getDate(1, format);
    }

    /**
	 * Gets the yesterday.
	 *
	 * @return the yesterday
	 */
    public String getYesterday() {
    	return getDate(-1, "yyyyMMdd");
    }

    /**
	 * Gets the yesterday.
	 *
	 * @param format
	 *            the format
	 * @return the yesterday
	 */
    public String getYesterday(String format) {
    	return getDate(-1, format);
    }

    /**
	 * Gets the date.
	 *
	 * @param days
	 *            the days
	 * @return the date
	 */
    public String getDate(int days) {
    	return getDate(days, "yyyyMMdd");
    }

    /**
	 * Gets the date.
	 *
	 * @param days
	 *            the days
	 * @param format
	 *            the format
	 * @return the date
	 */
    public String getDate(int days, String format) {
    	GregorianCalendar gc = new GregorianCalendar();
    	SimpleDateFormat sdf = new SimpleDateFormat(format);
    	gc.add(Calendar.DATE, days);

    	return sdf.format(gc.getTime());
    }

    public Date getDate(XMLGregorianCalendar time) {
		GregorianCalendar calendar = time.toGregorianCalendar();
		return calendar.getTime();
	}

    public Date getDate(String dt) {
    	Calendar cal = Calendar.getInstance();
		dt = dt.replaceAll("-", "");
		if (dt.length() == 14) {
			cal.set(
					Integer.valueOf(dt.substring(0, 4)).intValue(),
					Integer.valueOf(dt.substring(4, 6)).intValue() - 1,
					Integer.valueOf(dt.substring(6, 8)).intValue(),
					Integer.valueOf(dt.substring(8, 10)).intValue(),
					Integer.valueOf(dt.substring(10, 12)).intValue(),
					Integer.valueOf(dt.substring(12, 14)).intValue()
					);
		} else if (dt.length() == 12) {
			cal.set(
					Integer.valueOf(dt.substring(0, 4)).intValue(),
					Integer.valueOf(dt.substring(4, 6)).intValue() - 1,
					Integer.valueOf(dt.substring(6, 8)).intValue(),
					Integer.valueOf(dt.substring(8, 10)).intValue(),
					Integer.valueOf(dt.substring(10, 12)).intValue(),
					0
					);
		} else if (dt.length() == 10) {
			cal.set(
					Integer.valueOf(dt.substring(0, 4)).intValue(),
					Integer.valueOf(dt.substring(4, 6)).intValue() - 1,
					Integer.valueOf(dt.substring(6, 8)).intValue(),
					Integer.valueOf(dt.substring(8, 10)).intValue(),
					0,
					0
					);
		} else if (dt.length() == 8) {
			cal.set(
					Integer.valueOf(dt.substring(0, 4)).intValue(),
					Integer.valueOf(dt.substring(4, 6)).intValue() - 1,
					Integer.valueOf(dt.substring(6, 8)).intValue(),
					0,
					0,
					0
					);
		}
		return cal.getTime();
    }

   /**
	 * Date filter.
	 *
	 * @param src
	 *            the src
	 * @param format
	 *            the format
	 * @return the string
	 */
    public String dateFilter(String src, String format)
    {
        if ( src == null || src.length() <14 || (format.length() < 2) )
            return "";

        String str_ret="";
        int iyyyy, iyy, imm, idd, i24hh, ihh, imi, iss;

        iyyyy = Integer.parseInt(src.substring(0,4));
        iyy = Integer.parseInt(src.substring(2,4));
        imm = Integer.parseInt(src.substring(4,6));
        idd = Integer.parseInt(src.substring(6,8));
        i24hh = Integer.parseInt(src.substring(8,10));
        if( i24hh > 12 ) ihh = i24hh-12;
        else ihh = i24hh;
        imi = Integer.parseInt(src.substring(10,12));
        iss = Integer.parseInt(src.substring(12,14));

        // YYYY-MM-DD 122513
        str_ret = replaceString(format, "YYYY", intToString(iyyyy));
        str_ret = replaceString(str_ret, "YY", intToString(iyy));
        str_ret = replaceString(str_ret, "MM", intToString(imm));
        str_ret = replaceString(str_ret, "DD", intToString(idd));
        str_ret = replaceString(str_ret, "HH24", intToString(i24hh));
        str_ret = replaceString(str_ret, "HH", intToString(ihh));
        str_ret = replaceString(str_ret, "MI", intToString(imi));
        str_ret = replaceString(str_ret, "SS", intToString(iss));

        return str_ret;
    }

    /**
	 * Date filter.
	 *
	 * @param src
	 *            the src
	 * @param format
	 *            the format
	 * @param zeroFG
	 *            the zero fg
	 * @return the string
	 */
    public String dateFilter(String src, String format, boolean zeroFG)
    {
        if ( src == null || src.length() <14 || (format.length() < 2) )
            return "";

        String str_ret = "";
        int iyyyy, iyy, imm, idd, i24hh, ihh, imi, iss,temphh;
		String strhh = "";

		if(zeroFG) {     //ڸ ϶ 0
			temphh = Integer.parseInt(src.substring(8,10));		//ð(۾)

			if( temphh > 12 ) ihh = temphh-12;
			else ihh = temphh;

			strhh = intToString(ihh);
			if(strhh.length() <= 1) strhh =  "0" + strhh;

		    str_ret = replaceString(format, "YYYY", src.substring(0,4));
	        str_ret = replaceString(str_ret, "YY", src.substring(2,4));
	        str_ret = replaceString(str_ret, "MM", src.substring(4,6));
	        str_ret = replaceString(str_ret, "DD", src.substring(6,8));
	        str_ret = replaceString(str_ret, "HH24", src.substring(8,10));
	        str_ret = replaceString(str_ret, "HH", strhh);
	        str_ret = replaceString(str_ret, "MI", src.substring(10,12));
	        str_ret = replaceString(str_ret, "SS", src.substring(12,14));

		} else {		//ڸ ϶ 0

			iyyyy = Integer.parseInt(src.substring(0,4));			//
			iyy = Integer.parseInt(src.substring(2,4));				//(ڸ)
			imm = Integer.parseInt(src.substring(4,6));			//
			idd = Integer.parseInt(src.substring(6,8));			//
			i24hh = Integer.parseInt(src.substring(8,10));		//ð

			if( i24hh > 12 ) ihh = i24hh-12;
			else ihh = i24hh;

			imi = Integer.parseInt(src.substring(10,12));			//
			iss = Integer.parseInt(src.substring(12,14));			//

	        str_ret = replaceString(format, "YYYY", intToString(iyyyy));
	        str_ret = replaceString(str_ret, "YY", intToString(iyy));
	        str_ret = replaceString(str_ret, "MM", intToString(imm));
	        str_ret = replaceString(str_ret, "DD", intToString(idd));
	        str_ret = replaceString(str_ret, "HH24", intToString(i24hh));
		    str_ret = replaceString(str_ret, "HH", intToString(ihh));
	        str_ret = replaceString(str_ret, "MI", intToString(imi));
	        str_ret = replaceString(str_ret, "SS", intToString(iss));
		}
		return str_ret;
    }

    /**
	 * Int to string.
	 *
	 * @param i
	 *            the i
	 * @return the string
	 */
    private static String intToString(int i)
    {
        return (new Integer(i)).toString();
    }

    /**
	 * Replace string.
	 *
	 * @param src
	 *            the src
	 * @param from
	 *            the from
	 * @param to
	 *            the to
	 * @return the string
	 */
    private static String replaceString(String src, String from, String to)
    {
        StringBuffer res_Buff = new StringBuffer();
        int pos=-1;

        if (src == null || from == null || from.equals("")) return src;
        if (to == null) to = "";

        while (true)
        {
            if ((pos = src.indexOf(from)) == -1)
            {
                res_Buff.append(src);
                break;
            }
            res_Buff.append(src.substring(0, pos)).append(to);
            src = src.substring(pos + from.length());
        }
        return res_Buff.toString();
    }

    /**
	 * Date format string.
	 *
	 * @param dateString
	 *            the date string
	 * @param gubun
	 *            the gubun
	 * @return the string
	 */
    public String dateFormatString(String dateString, String gubun)
    {
    	String returnStr = "-";
    	if(dateString.length()>6){
	    	String yy = dateString.substring(0,4);
			String mm = dateString.substring(4,6);
			String dd = dateString.substring(6);
			returnStr = yy+gubun+mm+gubun+dd;
    	}else if(dateString.length()>4 && dateString.length()<=6){
	    	String yy = dateString.substring(0,4);
			String mm = dateString.substring(4);
			returnStr = yy+gubun+mm;
    	}else if(dateString.length()>0){
    		returnStr = dateString;
    	}
    	return returnStr;
    }

	/**
	 * Days diff from today.
	 *
	 * @param validToDate
	 *            the valid to date
	 * @return the int
	 */
	public int daysDiffFromToday(String validToDate) {
		return daysDiff(getTime(),validToDate,"yyyyMMddHHmmss");
	}


	/**
	 * Format issue date.
	 *
	 * @param date
	 *            the date
	 * @param gubun
	 *            the gubun
	 * @return the string
	 */
	public String formatIssueDate(String date, String gubun){
        String returnDate = "-";

        if(date.length() < 8) {
        	return returnDate;

        }else {
        	if(date.equals("00000000")){
            	returnDate = "";

        	}else {
	        	String year = date.substring(0,4);
	            String month = date.substring(4,6);
	            String day = date.substring(6,8);

	            if(!year.equals("0000")) returnDate = year;
	            if(!month.equals("00")) returnDate = returnDate + gubun + month;
	            if(!day.equals("00")) returnDate = returnDate + gubun + day;
	        }
            return returnDate;
       }
   }


	/**
	 * Chk genre order issue date.
	 *
	 * @param orderIssueDate
	 *            the order issue date
	 * @return true, if successful
	 */
	public boolean chkGenreOrderIssueDate(String orderIssueDate){
		String date = orderIssueDate.trim();
        boolean returnValue = false;
        int daydiff = 0;

        try{
	        if(date.length() < 8 || date.equals("0000000000") || date.equals("") || date == null) {
	        	return returnValue;

	        }else {
	        	if(((date.substring(0,8)).trim()).length() == 8){
	        		daydiff = daysDiff( date.substring(0,8) , getDate(), "yyyyMMdd");
	        		if(daydiff<=7){
		        		returnValue = true;
		        	}
	        	}
	        }

        }catch (Exception e){
        	return false;
        }

        return returnValue;
   }


	/**
	 * Chk other genre order issue date.
	 *
	 * @param orderIssueDate
	 *            the order issue date
	 * @return true, if successful
	 */
	public boolean chkOtherGenreOrderIssueDate(String orderIssueDate){
		String date = orderIssueDate.trim();
        boolean returnValue = false;
        int daydiff = 0;

        try{
	        if(date.length() < 8 || date.equals("0000000000") || date.equals("") || date == null) {
	        	return returnValue;

	        }else {
	        	if(((date.substring(0,8)).trim()).length() == 8){
	        		daydiff = daysDiff( date.substring(0,8) , getDate(), "yyyyMMdd");
	        		if(daydiff<=30){
		        		returnValue = true;
		        	}
	        	}
	        }

        }catch (Exception e){
        	return false;
        }

        return returnValue;
   }

 	/**
		 * Chk order issue date.
		 *
		 * @param orderIssueDate
		 *            the order issue date
		 * @param period
		 *            the period
		 * @return true, if successful
		 */
	public boolean chkOrderIssueDate(String orderIssueDate, int period){
		String date = orderIssueDate.trim();
        boolean returnValue = false;
        int daydiff = 0;

        try{
	        if(date.length() < 8 || date.equals("0000000000") || date.equals("") || date == null) {
	        	return returnValue;

	        }else {
	        	if(((date.substring(0,8)).trim()).length() == 8){
	        		daydiff = daysDiff( date.substring(0,8) , getDate(), "yyyyMMdd");
	        		if(daydiff <= period){
		        		returnValue = true;
		        	}
	        	}
	        }

        }catch (Exception e){
        	return false;
        }

        return returnValue;
   }

	/**
	 * String dur.
	 *
	 * @param createdTime
	 *            the created time
	 * @return the string
	 */
	public String StringDur(String createdTime){
		String returnValue = "";
		int duration = 0;
		Date d1 = stringToDate(getTime(), "yyyyMMddHHmmss");
		Date d2 = stringToDate(createdTime, "yyyyMMddHHmmss");
		duration = (int)((d1.getTime() - d2.getTime())/(60*1000));
		int result = 0;
		if(duration != 0 && duration%60 == 0){ //ð
			result = duration / 60;
			returnValue = result + "H";

			if(result%24 == 0){
				result = result / 24;
				returnValue = result + "D";

				if(result%7 == 0){
					result = result / 7;
					returnValue = result + "W";
				}
			}
		}else{
			returnValue = duration + "M";
		}

		return returnValue;
	}

	/**
	 * Gets the to day of week name.
	 *
	 * @return the to day of week name
	 */
	public String getToDayOfWeekName() {
		String dayOfWeekName = "";
		final String[] weekDay = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		Calendar cal = Calendar.getInstance();
		dayOfWeekName = weekDay[cal.get(Calendar.DAY_OF_WEEK)-1];

		return dayOfWeekName;
	}

	public String getWeekName(Date date) {
		String dayOfWeekName = "";
		final String[] weekDay = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		dayOfWeekName = weekDay[cal.get(Calendar.DAY_OF_WEEK)-1];

		return dayOfWeekName;
	}

	public boolean isWeekend(Date date) {
		int weekend = week(date);
		return weekend==0||weekend==6?true:false;
	}

	public int week(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK)-1;
	}

	public int yy(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.YEAR)%100;
	}

	public int yy(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) % 100;	//yy
	}

	public int yyyy(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.YEAR);	//yyyy
	}

	public int MM(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MONTH)+1; //MM
	}

	public int dd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_MONTH); //dd
	}

	public int yyyyMM(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int yyyyMM = (cal.get(Calendar.YEAR) * 100) + (cal.get(Calendar.MONTH)+1);
		return yyyyMM;	//yyyyMM
	}

	public Date yyyyMM(int month) {
		return stringToDate(Integer.toString(month), "yyyyMM");
	}

	public Date yyyy(int yyyy) {
		return stringToDate(Integer.toString(yyyy), "yyyy");
	}

	public int yyyyMMdd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int yyyyMMdd = (cal.get(Calendar.YEAR) * 10000) + ((cal.get(Calendar.MONTH)+1)*100) + cal.get(Calendar.DAY_OF_MONTH);
		return yyyyMMdd;	//yyyyMMdd
	}

	public int yyyyMMddhh(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);

		int yyyyMMddhh = (cal.get(Calendar.YEAR) * 1000000) + ((cal.get(Calendar.MONTH)+1)*10000) + (cal.get(Calendar.DAY_OF_MONTH)*100) + cal.get(Calendar.HOUR_OF_DAY);
		return yyyyMMddhh;	//yyyyMMdd
	}

	public Date yyyyMMdd(int day) {
		return stringToDate(Integer.toString(day), "yyyyMMdd");
	}

	public Date yyyyMMddhh(int hh) {
		return stringToDate(Integer.toString(hh), "yyyyMMddhh");
	}

	public Date yyyyMMddHHmmss(String day) {
		return stringToDate(day, "yyyyMMddHHmmss");
	}

	public Date yyyyMMddHHmm(String day) {
		return stringToDate(day, "yyyyMMddHHmm");
	}

	public int yyyyMMdd() {
		Calendar cal = Calendar.getInstance();

		int yyyyMMdd = (cal.get(Calendar.YEAR) * 10000) + ((cal.get(Calendar.MONTH)+1)*100) + cal.get(Calendar.DAY_OF_MONTH);
		return yyyyMMdd;	//yyyyMMdd
	}

	public int yyyyMMddToyyyy(int day){
		return yyyy(yyyyMMdd(day));
	}

	public int HH(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.HOUR_OF_DAY);	//HH
	}

	public int HH() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.HOUR_OF_DAY);	//HH
	}

	public int mm(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.MINUTE);	//mm
	}

	public int HHmm(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int HH = cal.get(Calendar.HOUR_OF_DAY);
		if(HH == 0){
			return cal.get(Calendar.MINUTE);
		}
		return (HH*100) + cal.get(Calendar.MINUTE);
	}

	public String HHmm_String(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int HH = cal.get(Calendar.HOUR_OF_DAY);
		if(HH == 0){
			return String.format("%4d", cal.get(Calendar.MINUTE)).replaceAll(" ", "0");
		}
		return String.format("%4d", (HH*100) + cal.get(Calendar.MINUTE)).replaceAll(" ", "0");
	}

	public Date HHmm(String HHmm) {
		if(HHmm.length()!=4) {
			HHmm = String.format("%04s", HHmm);
		}
		return stringToDate(HHmm, "HHmm");
	}

	public Date HHmm(int HHmm) {
		if(new Integer(HHmm).SIZE!=4) {
			return stringToDate(String.format("%04d", HHmm), "HHmm");
		}
		return stringToDate(String.valueOf(HHmm), "HHmm");
	}

	public int ss(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.SECOND);	//ss
	}

	//숫자를 2자리수 스트링으로 변환
	public String intToHH(int h) {
		String HH=null;
		if(h<10){
			HH= "0"+Integer.toString(h);
		}else{
			HH= Integer.toString(h);
		}

		return HH;
	}

	//00시00분
	public boolean is_HHmm(Date dt, int i){
		int HHmm = HHmm(dt);
		return HHmm == i;
	}

	//00분
	public boolean is_mm(Date dt, int i){
		int mm = mm(dt);
		return mm == i;
	}

	//현재시간 기준 전달 받은 시간이 지나지 않았다면 금일 시간
	//같은 시간이거나 지난시간이라면 다음날을 지정
	public Calendar getNextHourDate(int hour, int min){
		Calendar cal = Calendar.getInstance();
		int year,month,day;

		if(cal.get(Calendar.HOUR_OF_DAY) > hour){
			cal.add(Calendar.DAY_OF_YEAR, 1);
		}
		else if(cal.get(Calendar.HOUR_OF_DAY) == hour){
			if(cal.get(Calendar.MINUTE) > min){
				cal.add(Calendar.DAY_OF_YEAR, 1);
			}
		}

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, month, day, hour, min, 0);

		return cal;
	}


	//현재시간 기준 전달 받은 시간이 지나지 않았다면 금일 시간
	//같은 시간이거나 지난시간이라면 다음날을 지정
	public Calendar getNextHourDate(int hour){
		Calendar cal = Calendar.getInstance();
		int year,month,day;

		if(cal.get(Calendar.HOUR_OF_DAY) >= hour){
		    cal.add(Calendar.DAY_OF_YEAR, 1);
		}

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		day = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(year, month, day, hour, 0, 0);

		return cal;
	}

	public Calendar getDay(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);
		int day = cal.get(Calendar.HOUR_OF_DAY);

		cal.set(year, month, day, 0, 0, 0);
		return cal;
	}

	public Calendar getDay(int day){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		cal.set(year, month, day, 0, 0, 0);
		return cal;
	}

	//현재일 기준 전달 받은 일이 지나지 않았거나 같은 일이면 지난달 시작일
	//지난일이라면 이번달 시작일
	public Calendar getStartDayAtToday(int day){
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Calendar startCal = getDay(day);
		if(currentDay <= day){
			Calendar cal = Calendar.getInstance();
			cal.setTime(rollMonths(startCal.getTime(), -1));
			return cal;
		}
		return startCal;
	}

	//현재일 기준 전달 받은 일이 지나지 않았거나 같은 일이면 이번달 종료일
	//지난일이라면 다음달 종료일
	public Calendar getEndDayAtToday(int day){
		int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		Calendar startCal = getDay(day);
		if(currentDay <= day){
			return startCal;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(rollMonths(startCal.getTime(), +1));
		return cal;
	}




	//해당 년의 일수 반환
	//윤년 계산 하여 반환
	public int getDayCntByYear(int year){

		int cnt=0;

		boolean  isLeap = isLeapYear(year);
		if(isLeap){
			cnt= 366;//윤년 : 366
		}else{
			cnt= 365;//평년 : 365
		}
		return cnt;
	}

	//해당 월의 일수 반환
	//윤달 계산하여 반환
	public int getDayCntByMonth(int year, int month){

		int cnt=0;

		if(month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12 ){//평달 1,3,5,7,8,10,12 :31
			cnt=31;
		}else if(month == 4 || month == 6 || month == 9 || month == 11){//평달 4,6,9,11 : 30
			cnt=30;
		}else if(month == 2){
			boolean  isLeap = isLeapYear(year);
			if(isLeap){
				cnt= 29; //윤달 2 :29
			}else{
				cnt= 28; //평달 2: 28
			}
		}
		return cnt;
	}

	/**
	 *	@return Key = year, Value = 해당 년도 일자
	 */
	public Map<Integer, List<Integer>> getDuringDay(int sday, int eday){
		Map<Integer, List<Integer>> res = new TreeMap<>();
		List<Integer> days = getDuringDate(sday, eday);
		for(int day : days) {
			int year = yyyy(yyyyMMdd(day));

			if(res.get(year) == null) {
				res.put(year, new ArrayList<Integer>());
			}

			res.get(year).add(day);
		}
		return res;
	}

	/* 시작일과 종료일 사이의 날짜 구하기
	 *startDt yyyyMMdd
	 */
	public ArrayList<Integer> getDuringDate(int  startDt, int endDt){
		ArrayList<Integer> list = new ArrayList<Integer>();

		String sd= String.valueOf(startDt);

		  int startYear = Integer.parseInt(sd.substring(0,4));
	      int startMonth= Integer.parseInt(sd.substring(4,6));
	      int startDate = Integer.parseInt(sd.substring(6,8));
	      Calendar cal = Calendar.getInstance();

	        // Calendar의 Month는 0부터 시작하므로 -1 해준다.
	        // Calendar의 기본 날짜를 startDt로 셋팅해준다.
	        cal.set(startYear, startMonth -1, startDate);
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        list.add(startDt);
	        while(true) {
	            // 날짜 출력

	            // Calendar의 날짜를 하루씩 증가한다.
	            cal.add(Calendar.DATE, 1); // one day increment

	            // 현재 날짜가 종료일자보다 크면 종료

	           int nextday = Integer.parseInt(sdf.format(cal.getTime()));

	            if(nextday > endDt) {
	            	break;
	            }else{
	            	list.add(nextday);
	            }
	        }

		return list;
	}

	/* 시작일과 종료일 사이의 월 구하기
	 *startDt yyyyMM
	 */
	public ArrayList<Integer> getDuringMonth(int  sMonth, int eMonth){
		ArrayList<Integer> list = new ArrayList<Integer>();

		String sm= String.valueOf(sMonth)+"01";
		String em= String.valueOf(eMonth)+"01";
		int startYear = Integer.parseInt(sm.substring(0,4));
		int startMonth= Integer.parseInt(sm.substring(4,6));
		int startDate = Integer.parseInt(sm.substring(6,8));

		Calendar cal = Calendar.getInstance();

	        // Calendar의 Month는 0부터 시작하므로 -1 해준다.
	        // Calendar의 기본 날짜를 startDt로 셋팅해준다.
	        cal.set(startYear, startMonth -1, startDate);
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	        list.add(sMonth);

	        while(true) {
	            // Calendar의 월을 1달씩 증가한다.
	            cal.add(Calendar.MONTH, 1); // one month increment

	            // 현재 날짜가 종료일자보다 크면 종료
	           int nextday = Integer.parseInt(sdf.format(cal.getTime()));

	            if(nextday > Integer.parseInt(em)) {
	            	break;
	            }else{
	            	list.add(Integer.parseInt((String.valueOf(nextday)).substring(0,6)));
	            }
	        }
		return list;
	}

	/*현재 시간 기준 시작시간과 종료 시간 사이의 시간 구하기
	 *startDt yyyyMMddHHmmss
	 */
	public ArrayList<Integer> getDuringTime(String  sTime, String eTime){
		ArrayList<Integer> list = new ArrayList<Integer>();
		Date st = stringToDate(sTime, "yyyyMMddHHmmss");//20180508091500
		Date et = stringToDate(eTime, "yyyyMMddHHmmss");//20180508101500
		String sTimeHH =sTime.substring(8,10);
		String sTimemm =sTime.substring(10,12);
		list.add(Integer.parseInt(sTimeHH));

        while(true) {
            // Calendar의 시간을 1시간씩 증가한다.
        	st = rollHours( st, 1 );
        	st = stringToDate(DateUtil.inst().formatDate(st, "yyyyMMddHHmmss"), "yyyyMMddHHmmss");//20180508091500
          	long diff = et.getTime() - st.getTime();
        	long sec = diff / 1000;
        	// 현재 날짜가 종료일자보다 크면 종료
        	if(sec ==0) {
        		if(!sTimemm.equals("00")) {//정시가 아닐때 시간 포함
        			int HH=DateUtil.inst().HH(st);
        			list.add(HH);
        		}
        	}else if(sec >0){
        		int HH=DateUtil.inst().HH(st);
    			list.add(HH);
        	}else {
        		break;
        	}
        }
		return list;
	}

    public String getDateByString(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public String getDateString(Date date, String pattern) {
    	SimpleDateFormat sdf = new SimpleDateFormat(pattern);
    	return sdf.format(date);
    }

    public int timeToIndex(int hour, int min, int type) {
    	int time = (60 * hour) + min;
    	return ((time / type) + 1);
    }

    public String indexToTime(int timeIndex, int type) {
    	if ((timeIndex * type) > 1440) {
    		timeIndex = timeIndex % ((type == 5) ? 289:97);
    	}
    	int time = (type * (timeIndex - 1));
    	int h = (time / 60);
    	int m = (time % 60);
    	return ((h < 10)?"0":"") + String.valueOf(h) + ((m < 10)?"0":"") + String.valueOf(m);
    }

    public int getGapOfMinutes(Date begin, Date end) {
    	long diff = end.getTime() - begin.getTime();
    	long diffMin = diff / (60 * 1000);
    	return (int)diffMin;
    }

    public int getGapOfDate(Date begin, Date end) {
    	begin = this.getDate(this.getDateString(begin, "yyyy-MM-dd"));
    	end = this.getDate(this.getDateString(end, "yyyy-MM-dd"));

    	long diff = end.getTime() - begin.getTime();
    	long diffDays = diff / (24 * 60 * 60 * 1000);
    	return (int)diffDays;
    }

	public int getGapOfSecond(Date begin, Date end) {
		long diff = end.getTime() - begin.getTime();
		long diffSec = diff / (1000);
		return (int) diffSec;
	}

    public int getIntDate(Date dt, int calendarType) {
    	Calendar c = Calendar.getInstance();
    	c.setTime(dt);
    	return c.get(calendarType);
    }

    public String getISODate(Date dt) {
    	String format = "ISODate(%s)";
    	TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		df.setTimeZone(tz);
		return String.format(format, df.format(dt));
    }

    public String getUTCDate(Date dt) {
    	TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		df.setTimeZone(tz);
		return df.format(dt);
    }

    public Date setDaydd(Date dt, int dd){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dt);
    	cal.set(Calendar.DAY_OF_MONTH, dd);
    	return cal.getTime();
    }

    public Date setDayHH(Date dt, int h){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dt);
    	cal.set(Calendar.HOUR_OF_DAY, h);
    	return cal.getTime();
    }

    public Date setDaymm(Date dt, int mm){
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(dt);
    	cal.set(Calendar.MINUTE, mm);
    	return cal.getTime();
    }

    public Date setDayHHmm(Date dt, String HHmm) {
    	Date date = formatDate(HHmm, "HHmm");
    	if(date==null)
    		return null;
    	dt = setDayHH(dt, HH(date));
    	dt = setDaymm(dt, mm(date));
    	return dt;
    }

    public int lastdd(int yyyyMMdd) {
    	Calendar cal = this.stringToCalendar(String.valueOf(yyyyMMdd), "yyyyMMdd");
    	return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public int lastdd(Date yyyyMM) {
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(yyyyMM);
    	return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
    }


    /**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws ParseException
	 */
	public static void main(String args[]) throws ParseException {
		/*TimeZone tz = TimeZone.getTimeZone("UTC");
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		df.setTimeZone(tz);
		String nowAsISO = df.format(new Date());

		System.out.println(nowAsISO);

		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
		//nowAsISO = "2013-05-31T00:00:00Z";
		Date finalResult = df1.parse(nowAsISO);

		System.out.println(finalResult);*/


/*		Date dt = DateUtil.inst().setDaydd(DateUtil.inst().getToday(), 10);
		System.out.println("10일? : "+dt);
		Date today = DateUtil.inst().getToday();
		int dd = DateUtil.inst().getGapOfDate(today, DateUtil.inst().setDaydd(today, 26));
		System.out.println("차이값 : "+dd);

		DateUtil.inst().setDayHH(today, 15);
		System.out.println("현재 시간 : "+today);
		System.out.println("바뀐 시간 : "+DateUtil.inst().setDayHH(today, 15));*/
		/*
		System.out.println(DateUtil.getTime("yyyy-MM-dd'T'HH:mm:ss.SSS"));
		System.out.println(new Date(1302679567349L));
		System.out.println(new Date(1302765967349L));
		System.out.println(DateUtil.getToday());
		System.out.println("getToDayOfWeekName : "+DateUtil.getToDayOfWeekName());
		*/

		/*
		System.out.println(DateUtil.inst().getDayCntByMonth(2017, 4));
		System.out.println("평년 : "+DateUtil.inst().getDayCntByYear(2017));
		System.out.println("평년 : "+DateUtil.inst().getDayCntByYear(2018));
		System.out.println("평년 : "+DateUtil.inst().getDayCntByYear(2019));
		System.out.println("윤년 : "+DateUtil.inst().getDayCntByYear(2020));
		System.out.println("평년 : "+DateUtil.inst().getDayCntByYear(2021));
		System.out.println("평년 : "+DateUtil.inst().getDayCntByYear(2022));
		System.out.println("평년 : "+DateUtil.inst().getDayCntByYear(2023));
		*/

		//Date todt = DateUtil.inst().getToday();
		/*System.out.println(DateUtil.inst().yyyy(todt));
		System.out.println(DateUtil.inst().yyyyMM(todt));
		System.out.println(DateUtil.inst().yyyyMMdd(todt));
		System.out.println(DateUtil.inst().MM(todt));
		System.out.println(DateUtil.inst().dd(todt));
		System.out.println(DateUtil.inst().HH(todt));
		System.out.println(DateUtil.inst().mm(todt));
		System.out.println(DateUtil.inst().ss(todt));*/

		/*
		Calendar c=DateUtil.inst().stringToCalendar("0528", "HHmm");
		System.out.println(c.get(Calendar.HOUR_OF_DAY));
		System.out.println(c.get(Calendar.MINUTE));
		*/

		/*Date date = DateUtil.inst().yyyyMM(201802);
		System.out.println(DateUtil.inst().yyyyMMdd(date));
		System.out.println(DateUtil.inst().yyyyMM(date));*/

//		Date d = DateUtil.inst().getToday();
//		String MMdd = DateUtil.inst().MMdd_string(d);
//		System.out.println(MMdd);
		System.out.println(DateUtil.inst().getDuringDay(20181221, 20190211).toString());
		System.out.println(DateUtil.inst().getDuringMonth(201812, 201901).toString());
	}

	public String MMdd_string(Date today) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		String MM = String.format("%02d", cal.get(Calendar.MONTH) + 1);
		String dd = String.format("%02d", cal.get(Calendar.DAY_OF_MONTH));
		return MM + dd;
	}


	public Date getYesterDay(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, -1);
		return cal.getTime();
	}

	public int getYesterDayInt(Date dt) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		cal.add(Calendar.DATE, -1);
		return yyyyMMdd(cal.getTime());
	}

	// 현재 시간의 Tic을 계산한다.
	public Date getCurrentTic(int tic) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		int modulo = cal.get(Calendar.MINUTE) % tic;
        if(modulo > 0) {
        	cal.add(Calendar.MINUTE, (tic-modulo));
        }
		return cal.getTime();
	}
}

