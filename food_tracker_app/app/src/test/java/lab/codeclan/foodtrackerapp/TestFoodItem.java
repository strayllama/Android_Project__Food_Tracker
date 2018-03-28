package lab.codeclan.foodtrackerapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import lab.codeclan.foodtrackerapp.Models.FoodItem;

import static org.junit.Assert.assertEquals;

public class TestFoodItem {
    private FoodItem aNewItem;
    private Date aDate;
    private String aType;
    private String aDescription;
    private int aCalories;
    private Integer aFiveAday;

    @Before
    public void before() {
        aDate = new Date("2018/03/26");
        aType = "Meal";
        aDescription = "Veggies Pasta";
        aCalories = 100;
        aFiveAday = 1;

        aNewItem = new FoodItem(aDate, aType, aDescription, aCalories, aFiveAday);

    }

    @Test
    public void foodItemHasDate() {
        assertEquals("2018/03/26", aNewItem.getDate());
    }

    @Test
    public void foodItemHasType() {
        assertEquals("Meal", aNewItem.getType());
    }

    @Test
    public void foodItemHasDesciption() {
        assertEquals("Veggies Pasta", aNewItem.getDescription());
    }

    @Test
    public void foodItemHasCalories() {
        assertEquals(100, aNewItem.getCalories());
    }

    @Test
    public void foodItemHasFiveAday() {
        assertEquals(4, aNewItem.getFiveAday());
    }

    @Test
    public void foodItemHasFullDescription() {
        System.out.println(aNewItem.toString());
    }

}
