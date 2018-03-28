package lab.codeclan.foodtrackerapp.Models;

import java.util.Date;

public class DaySummaryWater {
    private Date date;
    private Integer dayWater = 0;

    public DaySummaryWater(Date date, Integer dayWater){
        this.date = date;
        this.dayWater = dayWater;
    }

    public Date getDate() {
        return this.date;
    }


    public Integer getDayWater() {
        return this.dayWater;
    }

    public String toString() {
        return "Day: " + this.date + " has a total of " +  this.dayWater + " water mls";
    }
}
