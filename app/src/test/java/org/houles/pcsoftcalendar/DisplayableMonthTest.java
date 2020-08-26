package org.houles.pcsoftcalendar;

import android.util.Log;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;


public class DisplayableMonthTest {
    @Test
    public void defaultDateTest() {
        DisplayableMonth month = new DisplayableMonth(-1, -1);
        assertEquals(Calendar.getInstance().get(Calendar.getInstance().MONTH), month.getCurrentMonth());
        assertEquals(Calendar.getInstance().get(Calendar.getInstance().YEAR), month.getCurrentYear());
    }

    @Test
    public void setCurrentMonthTest() {
        DisplayableMonth month = new DisplayableMonth(-1, -1);
        month.setCurrentMonth(13);
        assertEquals(Calendar.getInstance().get(Calendar.getInstance().MONTH), month.getCurrentMonth());

        month.setCurrentMonth(11);
        assertEquals(Calendar.DECEMBER, month.getCurrentMonth());
    }

    @Test
    public void setYearTest () {
        DisplayableMonth month = new DisplayableMonth(-1, -1);
        month.setCurrentYear(-1);
        assertEquals(Calendar.getInstance().get(Calendar.getInstance().YEAR), month.getCurrentYear());
        month.setCurrentYear(2017);
        assertEquals(2017, month.getCurrentYear());
    }

    @Test
    public void buildArrayTest() {
        DisplayableMonth month = new DisplayableMonth(-1, -1);
        month.setCurrentYear(2020);
        month.setCurrentMonth(Calendar.NOVEMBER);
        month.build();

        List<Day> days = month.getDays();
        assertEquals(2, days.get(7).getNumber());
        assertEquals(26, days.get(0).getNumber());
        assertEquals(36, days.size());

        month.setCurrentMonth(Calendar.FEBRUARY);
        month.setCurrentYear(2021);
        month.build();
        days =  month.getDays();
        assertEquals(1, days.get(0).getNumber());
        assertEquals(28, days.get(27).getNumber());
        assertEquals(28, days.size());


    }
}