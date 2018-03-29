package lab.codeclan.foodtrackerapp.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import lab.codeclan.foodtrackerapp.Activities.Adaptors.FullItemsListAdaptor;
import lab.codeclan.foodtrackerapp.Activities.Adaptors.WeeklyItemsListAdaptor;
import lab.codeclan.foodtrackerapp.DataBase.Helpers.FoodDBHelper;
import lab.codeclan.foodtrackerapp.Models.DaySummaries;
import lab.codeclan.foodtrackerapp.Models.FoodItem;
import lab.codeclan.foodtrackerapp.R;

public class HomeActivity extends BaseActivity {
    private Intent intent = null;
    private FoodDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ArrayList<FoodItem> pastSevenDaysFood = new ArrayList<>();
        ArrayList<FoodItem> pastThirtyDaysFood = new ArrayList<>();
        myDB = new FoodDBHelper(this);

        //pastSevenDaysFood = myDB.getPastSevenDays();
        //pastThirtyDaysFood = myDB.getPastThirtyDays();

    }


    public void onClickAddFood(View view) {
        intent = new Intent(this, AddFoodActivity.class);
        startActivity(intent);
        finish();
    }

}
