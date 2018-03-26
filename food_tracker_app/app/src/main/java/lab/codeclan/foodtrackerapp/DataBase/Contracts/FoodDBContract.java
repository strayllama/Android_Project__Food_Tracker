package lab.codeclan.foodtrackerapp.DataBase.Contracts;

import android.provider.BaseColumns;

public class FoodDBContract implements BaseColumns {
    // To prevent someone from accidentally instantiating the contract class, make the constructor private.
    private FoodDBContract() {}

    public static final String TABLE_NAME = "food_items";
    public static final String COLUMN_NAME_DATE = "date";
    public static final String COLUMN_NAME_TYPE = "type";
    public static final String COLUMN_NAME_DESCRIPTION = "description";
    public static final String COLUMN_NAME_CALORIES = "calories";
    public static final String COLUMN_NAME_FIVEADAY = "fiveAday";


    public static final String SQL_CREATE_FOOD_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    _ID + " INTEGER PRIMARY KEY," +
                    COLUMN_NAME_DATE + " DATE," +
                    COLUMN_NAME_TYPE + " TEXT," +
                    COLUMN_NAME_DESCRIPTION + " TEXT," +
                    COLUMN_NAME_CALORIES + " INTEGER," +
                    COLUMN_NAME_FIVEADAY + " INTEGER)";


    public static final String SQL_DELETE_FOOD_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
