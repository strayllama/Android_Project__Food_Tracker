package lab.codeclan.foodtrackerapp.Activities.Adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lab.codeclan.foodtrackerapp.Models.DaySummaries;
import lab.codeclan.foodtrackerapp.R;

public class DailySummariesListAdaptor extends ArrayAdapter<DaySummaries> {
    public DailySummariesListAdaptor(Context context, ArrayList<DaySummaries> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        DaySummaries currentDaySummary = getItem(position);
        Log.d("MainActivity", "Item: " + currentDaySummary);
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.view_daily_summary_list, parent, false);
        }

        TextView date = listItemView.findViewById(R.id.daySummaryDate);
        date.setText(currentDaySummary.getDate().toString());

        TextView calories = listItemView.findViewById(R.id.daySummaryCalories);
        calories.setText(currentDaySummary.getDayCalories().toString());

        TextView water = listItemView.findViewById(R.id.daySummaryWater);
        water.setText(currentDaySummary.getDayWater().toString());

        TextView fiveAday = listItemView.findViewById(R.id.daySummaryFiveAday);
        fiveAday.setText(currentDaySummary.getDayFiveAday().toString());

        listItemView.setTag(currentDaySummary);

        return listItemView;
    }

}
