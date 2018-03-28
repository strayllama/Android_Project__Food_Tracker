package lab.codeclan.foodtrackerapp.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import lab.codeclan.foodtrackerapp.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = null;
        if (item.getItemId() == R.id.home) {
            intent = new Intent(this, HomeActivity.class);
        }

        if (item.getItemId() == R.id.add_food) {
             intent = new Intent(this, AddFoodActivity.class);
        }

        if (item.getItemId() == R.id.list_day_summaries) {
            intent = new Intent(this, ListDaySummariesActivity.class);
        }

        if (item.getItemId() == R.id.list_all) {
            intent = new Intent(this, ListAllActivity.class);
        }

        if (item.getItemId() == R.id.settings) {
            intent = new Intent(this, SettingsActivity.class);
        }


        startActivity(intent);
        finish();

        return true;
    }

}
