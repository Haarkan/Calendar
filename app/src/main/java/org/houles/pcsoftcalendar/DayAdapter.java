package org.houles.pcsoftcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DayAdapter extends ArrayAdapter {

    private List<Day> days;
    private List<Day> selectedDays;
    private List<Integer> daysNumbers;

    public DayAdapter(Context context, int textViewResourceId, List<Day> days, List<Day> selectedDays) {
        super(context, textViewResourceId, days);
        this.days = days;
        this.selectedDays = selectedDays;
        daysNumbers = new ArrayList<Integer>();
        buildDaysNumbers();
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.grid_view_items, null);
        TextView dayNumberText = (TextView) v.findViewById(R.id.dayNumberView);
        if (position < daysNumbers.size())
            dayNumberText.setText(Integer.toString(daysNumbers.get(position)));
        Day d = days.get(position);
        if (position < days.size() && selectedDays.contains(days.get(position))) {
            v.setBackgroundColor(getContext().getResources().getColor(R.color.colorYellow));
        }
        return v;

    }

    /**
     * Add a day in the selected array, refreshes the ui
     * @param pos
     */
    public List<Day> addSelectedDay(int pos) {
        selectedDays.add(days.get(pos));
        notifyDataSetChanged();
        return selectedDays;
    }

    /**
     * remove a day from the selected array, refreshes the ui
     * @param pos
     */
    public List<Day> removeSelectedDay(int pos) {
        selectedDays.remove(days.get(pos));
        notifyDataSetChanged();
        return  selectedDays;
    }


    /**
     * Check whether or not a day is selected
     * @param pos the index to check
     * @return true or false
     */
    public boolean isSelected(int pos) {
        if (selectedDays.contains(days.get(pos))) return true;
        return false;
    }

    /**
     * Convert the days array into an array of integer
     * Only this array can be shown on the UI
     * @return an integer arraylist of the days numbers
     */
    public void buildDaysNumbers() {
        daysNumbers = new ArrayList<Integer>();
        for (Day d : days) {
            daysNumbers.add(d.getNumber());
        }
    }
}