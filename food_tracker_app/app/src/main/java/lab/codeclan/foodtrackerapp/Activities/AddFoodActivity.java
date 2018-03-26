package lab.codeclan.foodtrackerapp.Activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import lab.codeclan.foodtrackerapp.R;

public class AddFoodActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);
        loadTypeSpinner();
        setTodaysDate();
    }

    public void loadTypeSpinner() {
        Spinner spinner = (Spinner) findViewById(R.id.type_spinner);

// Create an ArrayAdapter using the string(?!CharSeq?!) array and a default spinner layout ".simple_spinner_item"
         ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.food_type, android.R.layout.simple_spinner_item);

// Specify the layout to use when the list of choices appears... default ".simple_spinner_dropdown_item"
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    public void setTodaysDate() {

    }

    public void onClickDate(View view) {

    }

}
