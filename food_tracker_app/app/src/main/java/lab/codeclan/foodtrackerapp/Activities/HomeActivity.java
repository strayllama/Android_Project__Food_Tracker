package lab.codeclan.foodtrackerapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import lab.codeclan.foodtrackerapp.Activities.Adaptors.FullItemsListAdaptor;
import lab.codeclan.foodtrackerapp.Activities.Adaptors.WeeklyItemsListAdaptor;
import lab.codeclan.foodtrackerapp.DataBase.Helpers.FoodDBHelper;
import lab.codeclan.foodtrackerapp.Models.DaySummaries;
import lab.codeclan.foodtrackerapp.Models.FoodItem;
import lab.codeclan.foodtrackerapp.Models.SummaryUtility;
import lab.codeclan.foodtrackerapp.R;

public class HomeActivity extends BaseActivity {
    private Intent intent = null;
    private FoodDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView dayCalView;
        TextView dayWaterView;
        TextView dayFruitView;
        TextView weekCalView;
        TextView weekWaterView;
        TextView weekFruitView;
        TextView monthCalView;
        TextView monthWaterView;
        TextView monthFruitView;

        dayCalView = findViewById(R.id.dayProgressCal);
        dayWaterView = findViewById(R.id.dayProgressWater);
        dayFruitView = findViewById(R.id.dayProgressFruit);
        weekCalView = findViewById(R.id.weekSummaryAvgCal);
        weekWaterView = findViewById(R.id.weekSummaryAvgWater);
        weekFruitView = findViewById(R.id.weekSummaryAvgFruit);
        monthCalView = findViewById(R.id.monthSummaryAvgCal);
        monthWaterView = findViewById(R.id.monthSummaryAvgWater);
        monthFruitView = findViewById(R.id.monthSummaryAvgFruit);

        ArrayList<Integer> dayCalWaterFruit;
        ArrayList<Integer> weekCalWaterFruit;
        ArrayList<Integer> monthCalWaterFruit;

        Date today = new Date ();
        Calendar cal30 = Calendar.getInstance();
        cal30.add(Calendar.DATE, -29);
        Date thirtyDaysAgo = cal30.getTime();
        String thirtyDaysAgoStr = FoodItem.dateSQLformat.format(thirtyDaysAgo);

        Calendar cal7 = Calendar.getInstance();
        cal7.add(Calendar.DATE, -6);
        Date oneWeekAgo = cal7.getTime();
        String oneWeekAgoStr = FoodItem.dateSQLformat.format(oneWeekAgo);

        myDB = new FoodDBHelper(this);

        ArrayList<DaySummaries> dayDayList = myDB.getDaySummariesDateRange(FoodItem.dateSQLformat.format(today));
        ArrayList<DaySummaries> weekDayList = myDB.getDaySummariesDateRange(oneWeekAgoStr);
        ArrayList<DaySummaries> monthDayList = myDB.getDaySummariesDateRange(thirtyDaysAgoStr);
        System.out.println("I have a week list of Day Summaries of length: " + weekDayList.size());
        System.out.println("I have a month list of Day Summaries of length: " + monthDayList.size());

        dayCalWaterFruit = SummaryUtility.sumAndAverageCalWaterFruit(dayDayList);
        weekCalWaterFruit = SummaryUtility.sumAndAverageCalWaterFruit(weekDayList);
        monthCalWaterFruit = SummaryUtility.sumAndAverageCalWaterFruit(monthDayList);

        dayCalView.setText(dayCalWaterFruit.get(0).toString());
        dayWaterView.setText(dayCalWaterFruit.get(1).toString());
        dayFruitView.setText(dayCalWaterFruit.get(2).toString());

        weekCalView.setText(weekCalWaterFruit.get(0).toString());
        weekWaterView.setText(weekCalWaterFruit.get(1).toString());
        weekFruitView.setText(weekCalWaterFruit.get(2).toString());

        monthCalView.setText(monthCalWaterFruit.get(0).toString());
        monthWaterView.setText(monthCalWaterFruit.get(1).toString());
        monthFruitView.setText(monthCalWaterFruit.get(2).toString());
    }



// ADD FOOD BUTTON
    public void onClickAddFood(View view) {
        intent = new Intent(this, AddFoodActivity.class);
        startActivity(intent);
        finish();
    }

}
