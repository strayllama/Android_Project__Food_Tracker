package lab.codeclan.foodtrackerapp.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import lab.codeclan.foodtrackerapp.R;

public class ListAllActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_all);
    }

    // CREATE ADAPTOR TO LIST ALL ITEMS IN MY DATA BASE.... ALL DAYS? SORTED BY DATE!!

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(item.getItemId() == R.id.add_food) {
//            // GO TO ADD FOOD MAIN ACTIVITY
//        }
//
//        return true;
//    }

}
