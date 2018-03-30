package lab.codeclan.foodtrackerapp.Models;

import java.io.Serializable;
import java.util.Date;

public class DaySummaries implements Serializable{
    private Date date;
    private Integer dayCalories = 0;
    private Integer dayWater = 0;
    private Integer dayFiveAday = 0;

//    public DaySummaries(Date date){this.date = date;}

    public DaySummaries(Date date, Integer dayFiveAday){
        this.date = date;
        this.dayFiveAday = dayFiveAday;

    }
    public String getDate() {
        return FoodItem.dateSQLformat.format(this.date);
    }

    public Integer getDayWater() {
        return this.dayWater;
    }

    public Integer getDayFiveAday() {
        return this.dayFiveAday;
    }

    public Integer getDayCalories() {
        return this.dayCalories;
    }

    public void addDayWater(Integer water) {
        this.dayWater += water;
    }

    public void addFiveAday(Integer fiveAday) {
        this.dayFiveAday += fiveAday;
    }

    public void addDayCalories(Integer calories) {
        this.dayCalories += calories;
    }

}
