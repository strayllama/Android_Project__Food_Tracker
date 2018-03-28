package lab.codeclan.foodtrackerapp.Activities.Adaptors;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import lab.codeclan.foodtrackerapp.Models.FoodItem;
import lab.codeclan.foodtrackerapp.R;

public class FullItemsListAdaptor extends ArrayAdapter<FoodItem> {
    public FullItemsListAdaptor(Context context, ArrayList<FoodItem> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View listItemView, ViewGroup parent) {

        FoodItem currentFoodItem = getItem(position);
        Log.d("MainActivity", "Item: " + currentFoodItem);
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.view_full_item_list, parent, false);
        }

        TextView date = listItemView.findViewById(R.id.full_item_list_date);
        date.setText(currentFoodItem.getDate());

        TextView type = listItemView.findViewById(R.id.full_item_list_type);
        type.setText(currentFoodItem.getType());

        TextView calories = listItemView.findViewById(R.id.full_item_list_cal);
        calories.setText(currentFoodItem.getCalories().toString());

        TextView fiveAday = listItemView.findViewById(R.id.full_item_list_fiveAday);
        fiveAday.setText(currentFoodItem.getFiveAday().toString());

        TextView description = listItemView.findViewById(R.id.full_item_list_description);
        description.setText(currentFoodItem.getDescription());

        listItemView.setTag(currentFoodItem);

        return listItemView;
    }

}
