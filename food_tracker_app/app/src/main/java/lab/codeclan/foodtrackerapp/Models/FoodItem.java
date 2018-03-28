package lab.codeclan.foodtrackerapp.Models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodItem implements Serializable {
    private long id;
    private String date;
    private String type;
    private String description;
    private Integer calories;
    private Integer fiveAday;
    public static SimpleDateFormat dateSQLformat = new SimpleDateFormat("yyyy/MM/dd");

    // CONSTRUCTOR for initial creation to add to database
    public FoodItem(Date date, String type, String description, Integer calories, Integer fiveAday) {
        this.date = dateSQLformat.format(date);
        this.type = type;
        this.description = description;
        this.calories = calories;
        this.fiveAday = fiveAday;
    }

    // CONSTRUCTOR for editing item in database, so with ID.
    public FoodItem(long id, Date date, String type, String description, Integer calories, Integer fiveAday) {
        this.id = id;
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
        return this.date;
    }

    public void setDate(Date date) {
        this.date = dateSQLformat.format(date);
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

    public Integer getCalories() {
        return this.calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getFiveAday() {
        return this.fiveAday;
    }

    public void setFiveAday(Integer fiveAday) {
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
