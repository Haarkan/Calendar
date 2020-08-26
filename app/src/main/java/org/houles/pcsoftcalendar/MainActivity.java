package org.houles.pcsoftcalendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

/**
 * Main Activity, contains the calendar
 */
public class MainActivity extends AppCompatActivity {


    private DayAdapter dayAdapter;
    private GridView gridView;
    private LinearLayout monthYearTitle;
    private DisplayableMonth month;
    private List<Day> selectedDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        monthYearTitle = findViewById(R.id.title_layout);
        gridView = findViewById(R.id.grid_view);
        setListeners();
        selectedDays = new ArrayList<Day>();
        month = new DisplayableMonth(-1,-1);
        month.build();
        dayAdapter = new DayAdapter(this, R.layout.grid_view_items, month.getDays(), selectedDays);
        gridView.setAdapter(dayAdapter);
        gridView.setDrawSelectorOnTop(false);
        setMonthYearText();


    }

    /**
     * Set the top TextView to the shown month and year
     */
    private void setMonthYearText () {
        TextView monthTextView = monthYearTitle.findViewById(R.id.month_text);
        String monthText = month.getCalendar().getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.FRANCE);
        monthTextView.setText(monthText);

        TextView yearTextView = monthYearTitle.findViewById(R.id.year_text);
        String yearText = Integer.toString(month.getCalendar().get(month.getCalendar().YEAR));
        yearTextView.setText(yearText);
    }

    /**
     * Define and set the listeners
     */
    private void setListeners() {

        gridView.setOnTouchListener(new OnSwipeListener(MainActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                MainActivity.this.onSwipeLeft();
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                MainActivity.this.onSwipeRight();
            }

            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                MainActivity.this.onSwipeUp();
            }
            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                MainActivity.this.onSwipeDown();
            }
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                super.onTouch(view, motionEvent);
                return false;
            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                gridView.setSelection(pos);
                gridView.setItemChecked(pos, true);
                if (!dayAdapter.isSelected(pos)) {
                    selectedDays = dayAdapter.addSelectedDay(pos);
                }
                else {
                    selectedDays = dayAdapter.removeSelectedDay(pos);
                }
            }
        });
    }

    /**
     * Called when user swipes to update the grid
     */
    private void rebuildGrid() {
        // refreshing grid
        dayAdapter = new DayAdapter(this, R.layout.grid_view_items, month.getDays(), selectedDays);
        gridView.setAdapter(dayAdapter);
        setMonthYearText();
    }

    private void onSwipeDown() {
        month.setCurrentYear(month.getCurrentYear()  -1);
        month.build();
        rebuildGrid();
    }

    private void onSwipeUp() {
        month.setCurrentYear(month.getCurrentYear()  +1);
        month.build();
        rebuildGrid();
    }

    private void onSwipeLeft() {
        int currentMonth = month.getCurrentMonth();
        // if current month is december then we have to increase the year
        if (currentMonth == 11) {
            month.setCurrentYear(month.getCurrentYear() + 1);
            month.setCurrentMonth(0);
        } else {
            month.setCurrentMonth(month.getCurrentMonth() + 1);
        }
        month.build();
        rebuildGrid();

    }

    private void onSwipeRight() {
        int currentMonth = month.getCurrentMonth();
        // if current month is january then we have to decrease the year
        if (currentMonth == 0) {
            month.setCurrentYear(month.getCurrentYear() - 1);
            month.setCurrentMonth(11);
        } else {
            month.setCurrentMonth(month.getCurrentMonth() - 1);
        }
        month.build();
        // refreshing grid
        rebuildGrid();
    }

}