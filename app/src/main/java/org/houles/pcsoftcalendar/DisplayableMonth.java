package org.houles.pcsoftcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A class containing methods to build an array of days that can fit in a calendar
 * Uses the Java.util.Calendar package
 */
public class DisplayableMonth {


    private Calendar calendar;
    private List<Day> days;

    private int currentMonth;
    private int currentYear;

    /**
     * Constructor
     * @param currentMonth
     * @param currentYear
     */
    public DisplayableMonth(int currentMonth, int currentYear) {
        days = new ArrayList<Day>();
        calendar = Calendar.getInstance();
        setCurrentMonth(currentMonth);
        setCurrentYear(currentYear);
    }

    /**
     * Set the current month to the given value
     * if the value equals -1 then we set the calendar to the today's date
     *
     * @param currentMonth between -1(current month) and 11 (december)
     */
    public void setCurrentMonth(int currentMonth) {
        if (currentMonth == -1 || currentMonth > 11) {
            calendar.setTime(new Date());
            this.currentMonth = calendar.get(calendar.MONTH);
        } else {
            this.currentMonth = currentMonth;

        }
    }

    /**
     *
     * @return the calender
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * Set the month's year to the given value
     * -1 means the today's year
     * @param currentYear new calendar year value
     */
    public void setCurrentYear(int currentYear) {
        if (currentYear == -1) {
            calendar.setTime(new Date());
            this.currentYear = calendar.get(calendar.YEAR);
        } else {
            this.currentYear = currentYear;

        }
    }

    public int getCurrentMonth() {
        return currentMonth;
    }

    public int getCurrentYear() {
        return currentYear;
    }

    public List<Day> getDays() {
        return days;
    }

    /**
     * Reset the calendar to the saved value
     * Used when we need to get days from last month or at the end of the whiles loops in the build method
     */
    private void resetCalendar () {
        calendar.set(Calendar.YEAR, currentYear);
        calendar.set(Calendar.MONTH, currentMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
    }

    /**
     * Finds and returns the first month's monday
     * @return the index of the first month's monday
     */
    private int getFirstMondayIndex () {
        // determine the first monday index
        int firstMonday = 0;
        while (calendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            ++ firstMonday;
            calendar.add(calendar.DAY_OF_MONTH,1);
        }
        return firstMonday;
    }

    /**
     * Get a few days from the previous month in order to fill out the blanks before the first month's
     * monday in the calendar
     * Number of days to add = 7 - first monday index
     * @param firstMonday the index of the first monday of the month
     */
    private void fillPastMonthDays(int firstMonday) {
        if (firstMonday != 0) {
            calendar.add(calendar.MONTH, -1);
            int monthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(calendar.DAY_OF_MONTH, monthLastDay);
            for (int i = 0; i < 7 - firstMonday; ++i) {
                calendar.set(calendar.DAY_OF_MONTH, monthLastDay - (7 - firstMonday) + i + 1);

                days.add(new Day(calendar.get(calendar.DAY_OF_MONTH), calendar.get(calendar.MONTH),
                        calendar.get(calendar.YEAR), calendar.get(calendar.DAY_OF_WEEK)));
            }

        }
    }

    /**
     * Build the days array
     */
    public void build () {
        days = new ArrayList<Day>();
        resetCalendar();
        int currentMonth = calendar.get(calendar.MONTH);

        int firstMonday = getFirstMondayIndex();
        fillPastMonthDays(firstMonday);

        resetCalendar();

        // building list with current month's days
        while  (currentMonth == calendar.get(Calendar.MONTH)) {
            days.add(new Day(calendar.get(calendar.DAY_OF_MONTH), calendar.get(calendar.MONTH),
                    calendar.get(calendar.YEAR), calendar.get(calendar.DAY_OF_WEEK)));
            calendar.add(calendar.DAY_OF_MONTH,1);
        }

        resetCalendar();
    }
}
