package lab.codeclan.foodtrackerapp.DataBase.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

import lab.codeclan.foodtrackerapp.DataBase.Contracts.FoodDBContract;
import lab.codeclan.foodtrackerapp.DataBase.DBHelper;
import lab.codeclan.foodtrackerapp.Models.FoodItem;


public class FoodDBHelper extends DBHelper {
    public FoodDBHelper(Context context) {
        super(context);
    }


    public void save(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodDBContract.COLUMN_NAME_DATE, foodItem.getDate());
        values.put(FoodDBContract.COLUMN_NAME_TYPE, foodItem.getType());
        values.put(FoodDBContract.COLUMN_NAME_DESCRIPTION, foodItem.getDescription());
        values.put(FoodDBContract.COLUMN_NAME_CALORIES, foodItem.getCalories());
        values.put(FoodDBContract.COLUMN_NAME_FIVEADAY, foodItem.getFiveAday());

        //foodItem.getRouteGrade();
        long newRowId = db.insert(FoodDBContract.TABLE_NAME, null, values);
        foodItem.setId(newRowId); // Not gonna use this just yet.
    }

    public void getFood_onID(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                FoodDBContract.COLUMN_NAME_DATE,
                FoodDBContract.COLUMN_NAME_TYPE,
                FoodDBContract.COLUMN_NAME_DESCRIPTION,
                FoodDBContract.COLUMN_NAME_CALORIES,
                FoodDBContract.COLUMN_NAME_FIVEADAY};

        // Filter results WHERE "id" = '#3'
        String selection = BaseColumns._ID + " = ?";
//        String idString = id.toString(); // READ IN LONG FROM DATABASE
        String idString = String.valueOf(id);  // does this work
        String[] selectionArgs = { idString };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                FoodDBContract.COLUMN_NAME_DATE + " DESC";

        Cursor cursor = db.query(
                FoodDBContract.TABLE_NAME,    // The table to query
                projection,                     // The array of columns to return (pass null to get all)
                selection,                      // The columns for the WHERE clause
                selectionArgs,                  // The values for the WHERE clause
                null,                  // don't group the rows
                null,                   // don't filter by row groups
                sortOrder                      // The sort order
        );

        // To iterate through all the row returned and get values from columns use:
        List itemIds = new ArrayList<>();
        while(cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String itemDate = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_DATE));
            String itemType = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_TYPE));
            String itemDescription = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_DESCRIPTION));
            String itemCalories = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_CALORIES));
            String itemFivaAday = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_FIVEADAY));


            // itemIds.add(itemId);      // etc.. use this items[] array to pass to adapter maybe?

            // FOR ME:
            System.out.println("Food ID: " + itemId + ", Date: " + itemDate + ", Type: " + itemType + ", Description: " + itemDescription + ", Calories: " + itemCalories + ", FiveAday: " + itemFivaAday);
        }
        cursor.close();
    } // end getFood_onID

}
