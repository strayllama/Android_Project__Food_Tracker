package lab.codeclan.foodtrackerapp.Activities.Adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import lab.codeclan.foodtrackerapp.Models.DaySummaries;
import lab.codeclan.foodtrackerapp.R;

public class WeeklyItemsListAdaptor extends ArrayAdapter<DaySummaries> {

    public WeeklyItemsListAdaptor(Context context, ArrayList<DaySummaries> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        DaySummaries currentDaySummary = getItem(position);
        Log.d("MainActivity", "Item: " + currentDaySummary);
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.view_week_summary_list, parent, false);
        }

//        TextView date = listItemView.findViewById(R.id.week_summary_date);
//        date.setText(currentDaySummary.getDate());
//
//        TextView calories = listItemView.findViewById(R.id.week_summary_avg_cals);
//        calories.setText(currentDaySummary.getCalories().toString());
//
//        TextView water = listItemView.findViewById(R.id.week_summary_avg_water);
//        water.setText(currentDaySummary.getWater().toString());
//
//        TextView fiveAday = listItemView.findViewById(R.id.week_summary_avg_5day);
//        fiveAday.setText(currentDaySummary.getFiveAday().toString());

        listItemView.setTag(currentDaySummary);

        return listItemView;
    }

}
