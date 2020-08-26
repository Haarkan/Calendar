package org.houles.pcsoftcalendar;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.Objects;

/**
 * A class that store a day's data
 */
public class Day {


    private int number;
    /*We might need those data if we decide to implements new features such as for example events*/
    private int month;
    private int year;
    private int dayOfWeek;

    public Day (int number, int month, int year, int dayOfWeek) {
        this.number = number;
        this.month = month;
        this.year = year;
        this.dayOfWeek = dayOfWeek;
    }

    public int getNumber() {
        return number;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return number == day.number &&
                month == day.month &&
                year == day.year &&
                dayOfWeek == day.dayOfWeek;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public int hashCode() {
        return Objects.hash(number, month, year, dayOfWeek);
    }
}
