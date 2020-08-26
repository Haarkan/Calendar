package org.houles.pcsoftcalendar;

import android.util.Log;

import org.junit.Test;

import java.util.Calendar;
import java.util.List;

import static org.junit.Assert.*;


public class DayTest {
    @Test
    public void equalsTest() {
        Day day1 = new Day(18, 10, 2020, 7);
        Day day2 = new Day(18, 10, 2020, 7);

        assertEquals(day1, day2);

        day2 = new Day(18, 10, 2019, 7);
        assertNotEquals(day1, day2);

        day1 = new Day(20, 11, 1990, 7);
        assertNotEquals(day1, day2);


    }


}