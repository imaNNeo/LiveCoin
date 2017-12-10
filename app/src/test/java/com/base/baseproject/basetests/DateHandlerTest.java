package com.base.baseproject.basetests;

import com.base.baseproject.utils.date.DateHandler;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;


/**
 * Created by iman.
 * iman.neofight@gmail.com
 */
public class DateHandlerTest {
    private static final long TIMESTAMP = 1512126927071l;//(01 Dec 2017 local timezone (UTC+3.5h) 14:45:27)

    DateHandler mDateHandlerMiladi;
    DateHandler mDateHandlerShamsi;

    public DateHandlerTest(){
        mDateHandlerMiladi = DateHandler.getInstance();
        mDateHandlerMiladi.setType(DateHandler.DateType.MILADI);

        mDateHandlerShamsi = DateHandler.getInstance();
        mDateHandlerShamsi.setType(DateHandler.DateType.SHAMSI);
    }
    @Test
    public void testMiladiDay(){
        assertEquals(mDateHandlerMiladi.getDay(TIMESTAMP),01);
    }

    @Test
    public void testShamsiDay(){
        assertEquals(mDateHandlerShamsi.getDay(TIMESTAMP),10);
    }

    @Test
    public void testMiladiMonth(){
        assertEquals(mDateHandlerMiladi.getMonth(TIMESTAMP),12);
    }

    @Test
    public void testShamsiMonth(){
        assertEquals(mDateHandlerShamsi.getMonth(TIMESTAMP),9);
    }

    @Test
    public void testMiladiYear(){
        assertEquals(mDateHandlerMiladi.getYear(TIMESTAMP),2017);
    }

    @Test
    public void testShamsiYear(){
        assertEquals(mDateHandlerShamsi.getYear(TIMESTAMP),1396);
    }

    @Test
    public void testSeconds(){
        assertEquals(mDateHandlerMiladi.getSeconds(TIMESTAMP),27);
    }

    @Test
    public void testMinutes(){
        assertEquals(mDateHandlerMiladi.getMinutes(TIMESTAMP),45);
    }

    @Test
    public void testHour(){
        assertEquals(mDateHandlerMiladi.getHourOfDay(TIMESTAMP),14);
    }

    @Test
    public void testWeekday(){
        assertEquals(mDateHandlerMiladi.getDayOfWeek(TIMESTAMP), Calendar.FRIDAY);
    }

    @Test
    public void testMiladiShortMonthName(){
        assertEquals(mDateHandlerMiladi.getShortMonthName(mDateHandlerMiladi.getMonth(TIMESTAMP)),"Dec");
    }

    @Test
    public void testShamsiShortMonthName(){
        assertEquals(mDateHandlerShamsi.getShortMonthName(mDateHandlerShamsi.getMonth(TIMESTAMP)),"آذر");
    }

    @Test
    public void testMiladiShortWeekName(){
        assertEquals(mDateHandlerMiladi.getWeekdayShortName(mDateHandlerMiladi.getDayOfWeek(TIMESTAMP)),"Fri");
    }

    @Test
    public void testShamsiShortWeekName(){
        assertEquals(mDateHandlerShamsi.getWeekdayShortName(mDateHandlerShamsi.getDayOfWeek(TIMESTAMP)),"جمعه");
    }
}
