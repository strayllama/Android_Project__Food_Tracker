package lab.codeclan.foodtrackerapp.Models;

import java.util.Date;

public class DaySummaryCals {
    private Date date;
    private Integer dayCalories = 0;

    public DaySummaryCals(Date date, Integer dayCalories){
        this.date = date;
        this.dayCalories = dayCalories;
    }

    public Date getDate() {
        return this.date;
    }

    public Integer getDayCalories() {
        return this.dayCalories;
    }

    public String toString() {
        return "Day: " + this.date + " has a total of " +  this.dayCalories + " calories";
    }

}
