package lab.codeclan.foodtrackerapp.Activities;

import android.os.Bundle;
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

        ArrayList<DaySummaries> dayList = myDB.getAllDaySummaries();
        System.out.println("I have a list of Day Summaries of length: " + dayList.size());

        DailySummariesListAdaptor dailySummariesListAdaptor = new DailySummariesListAdaptor(this, dayList);

        ListView listView = findViewById(R.id.daySummariesListView);
        listView.setAdapter(dailySummariesListAdaptor);
    }
}
