package com.base.baseproject.utils.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *Programmer Iman Khoshabi
 *iman.neofight@gmail.com
 */
public class DateHandler {
    public enum DateType {
        SHAMSI,
        MILADI
    }

    private static DateHandler mDateHadnler;
    public static DateHandler getInstance(){
        return new DateHandler();
    }

    private DateType mDateType;

    public void setType(DateType t){
        mDateType = t;
    }
    public DateType getType(){
        return mDateType;
    }
    private DateHandler(){
        mDateType = DateType.MILADI;

    }


    public int getMiladiY(long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int year = calendar.get(Calendar.YEAR);
        return year;
    }
    public int getMiladiM(long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int m = calendar.get(Calendar.MONTH)+1;
        return m;
    }
    public int getMiladiD(long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int d = calendar.get(Calendar.DAY_OF_MONTH);
        return d;
    }

    public int getShamsiY(long date){
        PersianDateHandler persianDate = new PersianDateHandler();
        persianDate.GregorianToPersian(getMiladiY(date), getMiladiM(date), getMiladiD(date));
        int persianYear = persianDate.getYear();
        return persianYear;
    }
    public int getShamsiM(long date){
        PersianDateHandler persianDate = new PersianDateHandler();
        persianDate.GregorianToPersian(getMiladiY(date), getMiladiM(date), getMiladiD(date));
        int persianMonth = persianDate.getMonth();
        return persianMonth;
    }
    public int getShamsiD(long date){
        PersianDateHandler persianDate = new PersianDateHandler();
        persianDate.GregorianToPersian(getMiladiY(date), getMiladiM(date), getMiladiD(date));
        int persianDay = persianDate.getDay();
        return persianDay;
    }

    public int getYear(long date){
        if(mDateType== DateType.SHAMSI) {
            return getShamsiY(date);
        }else if(mDateType== DateType.MILADI){
            return getMiladiY(date);
        }
        return getMiladiY(date);
    }
    public int getMonth(long date){
        if(mDateType== DateType.SHAMSI) {
            return getShamsiM(date);
        }else if(mDateType== DateType.MILADI){
            return getMiladiM(date);
        }
        return getMiladiM(date);
    }
    public int getDay(long date){
        if(mDateType== DateType.SHAMSI) {
            return getShamsiD(date);
        }else if(mDateType== DateType.MILADI){
            return getMiladiD(date);
        }
        return getMiladiD(date);
    }
    public int getDayOfWeek(long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        int d = calendar.get(Calendar.DAY_OF_WEEK);
        return d;
    }
    public int getHourOfDay(long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    public int getMinutes(long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.MINUTE);
    }
    public int getSeconds(long date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(date);
        return calendar.get(Calendar.SECOND);
    }

    public String makeDateString(long timeStamp){
        int year  = getYear(timeStamp);
        int month = getMonth(timeStamp);
        int day = getDay(timeStamp);
        return year + "/" + month + "/" + day;
    }

    public int getTimestamp(int year,int month,int day){
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, day);

        return (int) (c.getTimeInMillis() / 1000L);
    }


    public String getShortMonthName(int month){
        switch (month){
            case 1:return mDateType==DateType.MILADI?"Jan":"فروردین";
            case 2:return mDateType==DateType.MILADI?"Feb":"اردیبهشت";
            case 3:return mDateType==DateType.MILADI?"Mar":"خرداد";
            case 4:return mDateType==DateType.MILADI?"Apr":"تیر";
            case 5:return mDateType==DateType.MILADI?"May":"مرداد";
            case 6:return mDateType==DateType.MILADI?"Jun":"شهریور";
            case 7:return mDateType==DateType.MILADI?"Jul":"مهر";
            case 8:return mDateType==DateType.MILADI?"Aug":"آبان";
            case 9:return mDateType==DateType.MILADI?"Sep":"آذر";
            case 10:return mDateType==DateType.MILADI?"Oct":"دی";
            case 11:return mDateType==DateType.MILADI?"Nov":"بهمن";
            case 12:return mDateType==DateType.MILADI?"Dec":"اسفند";
        }

        return "";
    }

    //params : day from 1 to 7
    public String getWeekdayShortName(int day){
        day%=7;
        switch (day){
            case Calendar.SUNDAY :return mDateType==DateType.MILADI?"Sun":"یک شنبه";
            case Calendar.MONDAY :return mDateType==DateType.MILADI?"Mon":"دو شنبه";
            case Calendar.TUESDAY :return mDateType==DateType.MILADI?"Tue":"سه شنبه";
            case Calendar.WEDNESDAY :return mDateType==DateType.MILADI?"Wed":"چهار شنبه";
            case Calendar.THURSDAY :return mDateType==DateType.MILADI?"Thu":"پنج شنبه";
            case Calendar.FRIDAY :return mDateType==DateType.MILADI?"Fri":"جمعه";
            case Calendar.SATURDAY :return mDateType==DateType.MILADI?"Sat":"شنبه";
        }

        return "";
    }

    public String getSimpleDate(long date){
        if(date==0)return "";

        int day = getDay(date);
        int year = getYear(date);
        int month = getMonth(date);

        if(mDateType== DateType.SHAMSI) {
            return String.valueOf(day) + " " + getShortMonthName(month) + " " + String.valueOf(year);
        }else if(mDateType== DateType.MILADI) {
            return String.valueOf(day) + " " + getShortMonthName(month) + " " + String.valueOf(year);
        }

        return "";
    }

    public String getSimpleDateWithTime(long timestamp){
        String dateTime = getSimpleDate(timestamp);
        if(mDateType == DateType.MILADI)
            dateTime += " - " + getHourOfDay(timestamp) + " : " + getMinutes(timestamp) + " : " + getSeconds(timestamp);
        else
            dateTime += " - " + getSeconds(timestamp) + " : " + getMinutes(timestamp) + " : " + getHourOfDay(timestamp);
        return dateTime;
    }

    public String getShowingDate(){
        long timeStamp = System.currentTimeMillis();
        int day = getDay(timeStamp);
        int month = getMonth(timeStamp);
        int dayOfWeek = getDayOfWeek(timeStamp)+1;

        String dayOfWeekShortName = getWeekdayShortName(dayOfWeek);
        String monthShortName = getShortMonthName(month);
        if(mDateType== DateType.SHAMSI) {
            String date = dayOfWeekShortName + " " + day + " " + monthShortName;
            return date;
        }else if(mDateType== DateType.MILADI){
            String date = day + "th " + monthShortName + ". " + dayOfWeekShortName + ".";
            return date;
        }

        return "";
    }

    public long getTimeStampFromDateTime(String dateTime){
        if(dateTime==null) return 0;

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = (Date)formatter.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(date!=null)return date.getTime();
        else return 0;
    }
}