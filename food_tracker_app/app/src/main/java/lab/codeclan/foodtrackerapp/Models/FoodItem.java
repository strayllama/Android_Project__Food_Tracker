package lab.codeclan.foodtrackerapp.Models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodItem {
    private long id;
    private String date;
    private String type;
    private String description;
    private int calories;
    private int fiveAday;
    private static SimpleDateFormat dateSQLformat;

    // CONSTRUCTOR for initial creation to add to database
    public FoodItem(Date date, String type, String description, int calories, int fiveAday) {
        dateSQLformat = new SimpleDateFormat("yyyy/MM/dd");
        this.date = dateSQLformat.format(date);
        this.type = type;
        this.description = description;
        this.calories = calories;
        this.fiveAday = fiveAday;
    }

    // CONSTRUCTOR for editing item in database, so with ID.
    public FoodItem(long id, Date date, String type, String description, int calories, int fiveAday) {
        this.id = id;
        dateSQLformat = new SimpleDateFormat("yyyy/MM/dd");
        this.date = dateSQLformat.format(date);
        this.type = type;
        this.description = description;
        this.calories = calories;
        this.fiveAday = fiveAday;
    }

    // Gettors and Settors
    public long getId() {
        return this.id;
    }

    public void setId(Long ID) { this.id = id; }

    public String getDate() {
        return this.date.toString();
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return this.calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getFiveAday() {
        return this.fiveAday;
    }

    public void setFiveAday(int fiveAday) {
        this.fiveAday = fiveAday;
    }

    public String toString() {
        String fullDescription = "This food is - Date: " + getDate() + ", Type: " + getType() +
                ", Description: " + getDescription() + ", Calories: " + getCalories() +
                ", FiveAday: " + getFiveAday() + ".";
        return fullDescription;
    }
    // Other Methods

}
