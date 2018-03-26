package lab.codeclan.foodtrackerapp.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import lab.codeclan.foodtrackerapp.DataBase.Contracts.FoodDBContract;

public class DBHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version!!!
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Food_tracker.db";

    //CONSTRUCTOR
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //METHODS
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(FoodDBContract.SQL_CREATE_FOOD_TABLE);
        // add line here for each extra table
    }


    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(FoodDBContract.SQL_DELETE_FOOD_TABLE);

        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
