package lab.codeclan.foodtrackerapp.DataBase.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.Date;

import lab.codeclan.foodtrackerapp.DataBase.Contracts.FoodDBContract;
import lab.codeclan.foodtrackerapp.DataBase.DBHelper;
import lab.codeclan.foodtrackerapp.Models.DaySummaries;
import lab.codeclan.foodtrackerapp.Models.DaySummaryCals;
import lab.codeclan.foodtrackerapp.Models.DaySummaryFiveAday;
import lab.codeclan.foodtrackerapp.Models.DaySummaryWater;
import lab.codeclan.foodtrackerapp.Models.FoodItem;
import lab.codeclan.foodtrackerapp.Models.SummaryUtility;

public class FoodDBHelper extends DBHelper {
    public FoodDBHelper(Context context) {
        super(context);
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public void save(FoodItem foodItem) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(FoodDBContract.COLUMN_NAME_DATE, foodItem.getDate());
        values.put(FoodDBContract.COLUMN_NAME_TYPE, foodItem.getType());
        values.put(FoodDBContract.COLUMN_NAME_DESCRIPTION, foodItem.getDescription());
        values.put(FoodDBContract.COLUMN_NAME_CALORIES, foodItem.getCalories());
        values.put(FoodDBContract.COLUMN_NAME_FIVEADAY, foodItem.getFiveAday());

        long newRowId = db.insert(FoodDBContract.TABLE_NAME, null, values);
//        foodItem.setId(newRowId); // Not gonna use this just yet.
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<FoodItem> getAllItems() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<FoodItem> allItems = new ArrayList<>();

        String[] projection = { // The array of columns to return (can pass null to get all)
                BaseColumns._ID,
                FoodDBContract.COLUMN_NAME_DATE,
                FoodDBContract.COLUMN_NAME_TYPE,
                FoodDBContract.COLUMN_NAME_DESCRIPTION,
                FoodDBContract.COLUMN_NAME_CALORIES,
                FoodDBContract.COLUMN_NAME_FIVEADAY};

        String selection = null; // want all columns
        String[] selectionArgs = null; // want all rows where = ? matchs all rows
        String groupBy = null; // don't group the rows
        String having = null; // don't filter by row groups
        String sortOrder = FoodDBContract.COLUMN_NAME_DATE + " DESC";  // sort by date

        Cursor cursor = db.query(
                FoodDBContract.TABLE_NAME,    // The table to query
                projection,                   // The array of columns to return (pass null to get all)
                selection,                    // The columns for the WHERE clause
                selectionArgs,                // The values for the WHERE clause
                groupBy,                      // don't group the rows
                having,                       // don't filter by row groups
                sortOrder                     // The sort order
        );

        // To iterate through all the rows returned and get values from columns use:
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID));
            String itemDateStr = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_DATE));
            String itemType = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_TYPE));
            String itemDescription = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_DESCRIPTION));
            String itemCaloriesStr = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_CALORIES));
            String itemFiveAdayStr = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_FIVEADAY));

            Date itemDate = new Date(itemDateStr);
            Integer itemCalories = Integer.valueOf(itemCaloriesStr);
            Integer itemFiveAday = Integer.valueOf(itemFiveAdayStr);

            FoodItem aFoodItem = new FoodItem(itemId, itemDate, itemType, itemDescription, itemCalories, itemFiveAday);

            allItems.add(aFoodItem);
        }
        cursor.close();

        return allItems;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public ArrayList<DaySummaries> getDaySummariesDateRange(String sinceDate) {
        Date theDate = new Date();
        String todaysDate = FoodItem.dateSQLformat.format(theDate);
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<DaySummaries> listDaySummaries;
        ArrayList<DaySummaryCals> listDaySummaryCals = new ArrayList<>();
        ArrayList<DaySummaryWater> listDaySummaryWater = new ArrayList<>();
        ArrayList<DaySummaryFiveAday> listDaySummaryFiveAday = new ArrayList<>();

// WATER MLS SECTION ************************************************
        String sqlWater = ("SELECT DATE, SUM(calories) from " + FoodDBContract.TABLE_NAME + " WHERE "
                + FoodDBContract.COLUMN_NAME_TYPE + " = ? and " + FoodDBContract.COLUMN_NAME_DATE +
                " BETWEEN ? AND ? GROUP BY " +
                FoodDBContract.COLUMN_NAME_DATE);
        String[] selectionArgsWater = {"Water", sinceDate, todaysDate};

        Cursor cursorWater = db.rawQuery(sqlWater, selectionArgsWater);
        while (cursorWater.moveToNext()) {
            Date dayDate = new Date(cursorWater.getString(0));
            Integer dayWater = cursorWater.getInt(1);
            DaySummaryWater aDaySummaryWater = new DaySummaryWater(dayDate, dayWater);
            listDaySummaryWater.add(aDaySummaryWater);
        }
        cursorWater.close();

//      CALORIES COLLECTION ************************************************
        String sqlCal = ("SELECT DATE, SUM(calories) from " + FoodDBContract.TABLE_NAME + " WHERE "
                + FoodDBContract.COLUMN_NAME_TYPE + " = ? or " + FoodDBContract.COLUMN_NAME_TYPE + " = ? or "
                + FoodDBContract.COLUMN_NAME_TYPE + " = ? and " + FoodDBContract.COLUMN_NAME_DATE +
                " BETWEEN ? AND ? GROUP BY " +
                FoodDBContract.COLUMN_NAME_DATE);
        String[] selectionArgsCal = {"Meal", "Snack", "Activity", sinceDate, todaysDate};

        Cursor cursorCalories = db.rawQuery(sqlCal, selectionArgsCal);
        while (cursorCalories.moveToNext()) {
            Date dayDate = new Date(cursorCalories.getString(0));
            Integer dayCalories = cursorCalories.getInt(1);
            DaySummaryCals aDaySummaryCals = new DaySummaryCals(dayDate, dayCalories);
            listDaySummaryCals.add(aDaySummaryCals);
        }
        cursorCalories.close();

//      FIVEADAY COLLECTION ************************************************
        String sqlFive = ("SELECT DATE, SUM(fiveAday) from " + FoodDBContract.TABLE_NAME +
                " WHERE " + FoodDBContract.COLUMN_NAME_DATE +
                " BETWEEN ? AND ? GROUP BY " +
                FoodDBContract.COLUMN_NAME_DATE + " ORDER BY " + FoodDBContract.COLUMN_NAME_DATE + " DESC");
        String[] selectionArgsFive = {sinceDate, todaysDate};

        Cursor cursorFiveAday = db.rawQuery(sqlFive, selectionArgsFive);
        while (cursorFiveAday.moveToNext()) {
            Date dayDate = new Date(cursorFiveAday.getString(0));
            Integer dayFiveAday = cursorFiveAday.getInt(1);
            DaySummaryFiveAday aDaySummaryFiveAday = new DaySummaryFiveAday(dayDate, dayFiveAday);
            listDaySummaryFiveAday.add(aDaySummaryFiveAday);
        }
        cursorFiveAday.close();

        listDaySummaries = SummaryUtility.combineFiveCalWaterArrays(listDaySummaryFiveAday, listDaySummaryCals, listDaySummaryWater);
        return listDaySummaries;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public ArrayList<FoodItem> getDateItems(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<FoodItem> listDayItems = new ArrayList<>();

        String sql = ("SELECT * from " + FoodDBContract.TABLE_NAME + " WHERE "
                + FoodDBContract.COLUMN_NAME_DATE + " = ? ");
        String[] selectionArgsCal = {date};
        Cursor cursor = db.rawQuery(sql, selectionArgsCal);
        while (cursor.moveToNext()) {
            long itemId = cursor.getLong(0);
            Date itemDate = new Date(cursor.getString(1));
            String itemType = cursor.getString(2);
            String itemDescription = cursor.getString(3);
            Integer itemCalories = cursor.getInt(4);
            Integer itemFiveAday = cursor.getInt(5);

            FoodItem aFoodItem = new FoodItem(itemId, itemDate, itemType, itemDescription, itemCalories, itemFiveAday);

            listDayItems.add(aFoodItem);
        }
        cursor.close();


        return listDayItems;
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////


//    public FoodItem getFood_onID(long id) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        FoodItem extractedItem = null;
//
//        // Define a projection that specifies which columns from the database
//        // you will actually use after this query.
//        String[] projection = {
//                BaseColumns._ID,
//                FoodDBContract.COLUMN_NAME_DATE,
//                FoodDBContract.COLUMN_NAME_TYPE,
//                FoodDBContract.COLUMN_NAME_DESCRIPTION,
//                FoodDBContract.COLUMN_NAME_CALORIES,
//                FoodDBContract.COLUMN_NAME_FIVEADAY};
//
//        // Filter results WHERE "id" = '#3'
//        String selection = BaseColumns._ID + " = ?";
////        String idString = id.toString(); // READ IN LONG FROM DATABASE
//        String idString = String.valueOf(id);  // does this work
//        String[] selectionArgs = { idString };
//
//        // How you want the results sorted in the resulting Cursor
//        String sortOrder =
//                FoodDBContract.COLUMN_NAME_DATE + " DESC";
//
//        Cursor cursor = db.query(
//                FoodDBContract.TABLE_NAME,    // The table to query
//                projection,                     // The array of columns to return (pass null to get all)
//                selection,                      // The columns for the WHERE clause
//                selectionArgs,                  // The values for the WHERE clause
//                null,                  // don't group the rows
//                null,                   // don't filter by row groups
//                sortOrder                      // The sort order
//        );
//
//        // To iterate through all the row returned and get values from columns use:
//        List itemIds = new ArrayList<>();
//        while(cursor.moveToNext()) {
//            long itemId = cursor.getLong(cursor.getColumnIndexOrThrow(BaseColumns._ID));
//            String itemDate = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_DATE));
//            String itemType = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_TYPE));
//            String itemDescription = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_DESCRIPTION));
//            String itemCalories = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_CALORIES));
//            String itemFivaAday = cursor.getString(cursor.getColumnIndexOrThrow(FoodDBContract.COLUMN_NAME_FIVEADAY));
//            Date date = new Date(itemDate);
//            extractedItem = new FoodItem(date, itemType, itemDescription, Integer.valueOf(itemCalories), Integer.valueOf(itemFivaAday));
//
//            // itemIds.add(itemId);      // etc.. use this items[] array.... but no need when just one item, pass FoodItem back
//
//            // FOR ME:
//            System.out.println("Food ID: " + itemId + ", Date: " + itemDate + ", Type: " + itemType + ", Description: " + itemDescription + ", Calories: " + itemCalories + ", FiveAday: " + itemFivaAday);
//        }
//        cursor.close();
//        return extractedItem;
//    } // end getFood_onID

}