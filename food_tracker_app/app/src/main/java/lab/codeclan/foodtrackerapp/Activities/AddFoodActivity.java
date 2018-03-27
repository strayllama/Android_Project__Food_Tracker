package lab.codeclan.foodtrackerapp.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lab.codeclan.foodtrackerapp.DataBase.Helpers.FoodDBHelper;
import lab.codeclan.foodtrackerapp.Models.FoodItem;
import lab.codeclan.foodtrackerapp.R;

public class AddFoodActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener {
    private FoodDBHelper myDB;
    private Date date = new Date(); // default is today's date.
    private Spinner spinner;

    RadioGroup sizeRadiogroup;

    TextView caloriesView;
    TextView fiveAdayView;
    TextView descriptionView;
    TextView dateView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        myDB = new FoodDBHelper(this);

        caloriesView = findViewById(R.id.calories_textView);
        fiveAdayView = findViewById(R.id.fiveAday_textView);
        descriptionView = findViewById(R.id.description_textView);
        dateView = findViewById(R.id.date_textView);
        dateView.setText(SetTodaysDate(date)); // convert todays date to yyyy/mm/dd and set to selection

        this.spinner = (Spinner) findViewById(R.id.type_spinner);
        loadTypeSpinner();

        sizeRadiogroup = (RadioGroup) findViewById(R.id.size);

        sizeRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup size, int checkedId) {
                // find which radio button is selected
                if(checkedId == R.id.large_food_radio) {
                    updateCalories(800);
                } else if(checkedId == R.id.medium_food_radio) {
                    updateCalories(400);
                } else {
                    updateCalories(200);
                }
            }
        });

    }

    public void updateCalories(Integer newValue) {
        caloriesView.setText(newValue.toString());
    }

    public void loadTypeSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.food_type, android.R.layout.simple_spinner_item); // Create an ArrayAdapter using the string(?!CharSeq?!) array and a default spinner layout ".simple_spinner_item"
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Layout set to default ".simple_spinner_dropdown_item"
        spinner.setAdapter(adapter); // Apply the adapter to the spinner
    }


    public String SetTodaysDate(Date date) {  // Pass in a java.util.date, get out a string of "yyyy/MM/dd"
        SimpleDateFormat dateSQLformat = new SimpleDateFormat("yyyy/MM/dd");
        return dateSQLformat.format(date);
    }

    public void showDatePickerDialog(View view) {  // CLICKING DATE BOX RUNS THIS - onClick
            DatePickerFragment dateFragment = new DatePickerFragment();
            dateFragment.onDateSetListener = this;
            dateFragment.show(getFragmentManager(), "datePicker");
    }


    // Embedded class for DatePickerFragment. Works with 'showDatePickerDialog' and 'onDateSet' to get user input date.
    public static class DatePickerFragment extends DialogFragment {

        public static DatePickerDialog.OnDateSetListener onDateSetListener;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();   // Use the current date as the default date in the picker
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), onDateSetListener, year, month, day);

        }
    } // end of DatePickerFragment

    @Override  // onDateSet is called when DatePickerDialog OK button is pressed.
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        String monthStr = "";
        String dayStr = "";
        month += 1;
        if (month < 10) {
            monthStr = "0" + month;
        } else { monthStr = String.valueOf(month);}
        if (day < 10) {
            dayStr = "0" + day;
        } else { dayStr = String.valueOf(day);}

        dateView.setText(year + "/" + monthStr + "/" + dayStr);
        //Log.e("MainActivity", "DATE: " + year + "/" + monthStr + "/" + dayStr );
    }

//    @Override   // When coming back from fragment - datePicker - passes onDateSet
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Date date = (Date) data.getSerializableExtra("DERP");
//        Log.e("MainActivity", date.toGMTString());
//    }

    public void onClickSubmitButton(View view) {
        FoodItem aNewItem;

        Date aDate;
        String aType;
        String aDescription;
        int aCalories;
        int aFiveAday;

        //Log.e("MainActivity", "Date in format?: " + (dateView.getText().toString())); // aDate = new Date(dateView.getText().toString());
        aDate = new Date(dateView.getText().toString());
        aType = spinner.getSelectedItem().toString();
        aDescription = descriptionView.getText().toString();
        aCalories = 400; // aCalories = Integer.getInteger(caloriesView.getText().toString());
        aFiveAday = Integer.valueOf(fiveAdayView.getText().toString());

        aNewItem = new FoodItem(aDate, aType, aDescription, aCalories, aFiveAday);

        myDB.save(aNewItem);
    }

} // end of AddFoodActivity
