package lab.codeclan.foodtrackerapp.Models;

import java.sql.Date; // ?? or java.utl.Date;
import java.util.LinkedList;

public class FoodItem {
    private long id;
    private Date date;
    private String type;
    private String description;
    private int calories;
    private int fiveAday;

    // CONSTRUCTOR for initial creation to add to database
    public FoodItem(Date date, String type, String description, int calories, int fiveAday) {
        this.date = date;
        this.type = type;
        this.description = description;
        this.calories = calories;
        this.fiveAday = fiveAday;
    }

    // CONSTRUCTOR for editing item in database, so with ID.
    public FoodItem(long id, Date date, String type, String description, int calories, int fiveAday) {
        this.id = id;
        this.date = date;
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

    public void setDate(Date date) {
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


    // Other Methods

}
