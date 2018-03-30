package lab.codeclan.foodtrackerapp.Activities;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import lab.codeclan.foodtrackerapp.Activities.Adaptors.FullItemsListAdaptor;
import lab.codeclan.foodtrackerapp.DataBase.Helpers.FoodDBHelper;
import lab.codeclan.foodtrackerapp.Models.FoodItem;
import lab.codeclan.foodtrackerapp.R;

public class ListAllActivity extends BaseActivity {
    private FoodDBHelper myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);

        myDB = new FoodDBHelper(this);

        ArrayList<FoodItem> foodList = myDB.getAllItems();
        FullItemsListAdaptor allFoodItemsAdaptor = new FullItemsListAdaptor(this, foodList);

        ListView listView = findViewById(R.id.allFoodItemsList);
        listView.setAdapter(allFoodItemsAdaptor);
    }
}
