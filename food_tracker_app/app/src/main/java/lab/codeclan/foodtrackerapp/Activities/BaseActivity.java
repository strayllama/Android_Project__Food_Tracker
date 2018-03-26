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
            Log.d("MainActivity", "Home Food Button Clicked. ");
            intent = new Intent(this, HomeActivity.class);
        }
        if (item.getItemId() == R.id.add_food) {
            Log.d("MainActivity", "Add Food Button Clicked. ");
             intent = new Intent(this, AddFoodActivity.class);
        }
        if (item.getItemId() == R.id.list_all) {
            Log.d("MainActivity", "List All Button Clicked. ");
             intent = new Intent(this, ListAllActivity.class);
        }

        if (item.getItemId() == R.id.settings) {
            Log.d("MainActivity", "Settings Button Clicked. ");
             intent = new Intent(this, SettingsActivity.class);
        }

        startActivity(intent);
        finish();

        return true;
    }

}
