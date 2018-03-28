package lab.codeclan.foodtrackerapp.Models;

import java.util.Date;

public class DaySummaryFiveAday {
    private Date date;
    private Integer dayFiveAday = 0;

    public DaySummaryFiveAday(Date date, Integer dayFiveAday){
        this.date = date;
        this.dayFiveAday = dayFiveAday;
    }

    public Date getDate() {
        return date;
    }

    public Integer getDayFiveAday() {
        return this.dayFiveAday;
    }

    public String toString() {
        return "Day: " + this.date + " has a total of " +  this.dayFiveAday + " of fruit and veg";
    }
}
