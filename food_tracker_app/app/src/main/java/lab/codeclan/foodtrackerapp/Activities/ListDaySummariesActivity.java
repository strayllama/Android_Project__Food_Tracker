package lab.codeclan.foodtrackerapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import lab.codeclan.foodtrackerapp.Activities.Adaptors.DailySummariesListAdaptor;
import lab.codeclan.foodtrackerapp.DataBase.Helpers.FoodDBHelper;
import lab.codeclan.foodtrackerapp.Models.DaySummaries;
import lab.codeclan.foodtrackerapp.R;

public class ListDaySummariesActivity extends BaseActivity {
    private FoodDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_day_summaries);

        myDB = new FoodDBHelper(this);

        ArrayList<DaySummaries> dayList = myDB.getDaySummariesDateRange("2000/01/01");

        DailySummariesListAdaptor dailySummariesListAdaptor = new DailySummariesListAdaptor(this, dayList);

        ListView listView = findViewById(R.id.daySummariesListView);
        listView.setAdapter(dailySummariesListAdaptor);
    }

    public void onListClickItem(View listItem) {
        DaySummaries selectedDaySummary = (DaySummaries) listItem.getTag();

        Intent intent = new Intent(this, DayActivity.class);
        intent.putExtra("day", selectedDaySummary);

        startActivity(intent);
    }
}
