package lab.codeclan.foodtrackerapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;

import lab.codeclan.foodtrackerapp.Activities.Adaptors.FullItemsListAdaptor;
import lab.codeclan.foodtrackerapp.DataBase.Contracts.FoodDBContract;
import lab.codeclan.foodtrackerapp.DataBase.Helpers.FoodDBHelper;
import lab.codeclan.foodtrackerapp.Models.DaySummaries;
import lab.codeclan.foodtrackerapp.Models.FoodItem;
import lab.codeclan.foodtrackerapp.R;

public class DayActivity extends AppCompatActivity {
    private FoodDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);
        TextView theDate;
        TextView count;
        Integer numberOfItems;

        theDate = findViewById(R.id.daySummaryDate);
        count = findViewById(R.id.daySummaryCount);
        Intent intent = getIntent();
        DaySummaries selectedDaySummary = (DaySummaries) intent.getSerializableExtra("day");
        Log.d("Day", selectedDaySummary.getDate());
        String date = selectedDaySummary.getDate();
        myDB = new FoodDBHelper(this);
        theDate.setText(date); // convert todays date to yyyy/mm/dd and set to selection

        ArrayList<FoodItem> foodList = myDB.getDateItems(date);
        numberOfItems = foodList.size();
        count.setText(numberOfItems.toString());

        FullItemsListAdaptor allFoodItemsAdaptor = new FullItemsListAdaptor(this, foodList);

        ListView listView = findViewById(R.id.dayListFoodItems);
        listView.setAdapter(allFoodItemsAdaptor);
    }

    public boolean onClickBackButton(View view) {
            finish();
            return true;
    }

}
