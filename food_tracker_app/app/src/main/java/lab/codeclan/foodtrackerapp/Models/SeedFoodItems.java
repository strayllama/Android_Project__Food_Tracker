package lab.codeclan.foodtrackerapp.Models;

import java.util.ArrayList;
import java.util.Date;


public class SeedFoodItems {
    private ArrayList<FoodItem> allItems;
    private Date aDate;
    public SeedFoodItems() {
        this.allItems = new ArrayList<>();
        aDate = new Date("2018/03/24");
        allItems.add(new FoodItem(aDate, "Water", "water",250, 0 ));
        allItems.add(new FoodItem(aDate, "Meal", "Bacon + Bannan Pancakes",450, 1 ));
        allItems.add(new FoodItem(aDate, "Meal", "Veggie Pasta",550, 2 ));
        allItems.add(new FoodItem(aDate, "Snack", "Porridge",250, 0));
        allItems.add(new FoodItem(aDate, "Meal", "Roast Dinner",800, 2));
        aDate = new Date("2018/03/25");
        allItems.add(new FoodItem(aDate, "Water", "water",100, 0 ));
        allItems.add(new FoodItem(aDate, "Meal", "Bacon Pasta",650, 0 ));
        allItems.add(new FoodItem(aDate, "Snack", "Porridge",250, 0));
        allItems.add(new FoodItem(aDate, "Meal", "Dahl Curry",700, 2));

    }

    public ArrayList<FoodItem> getSeedItems() {
        return new ArrayList<FoodItem>(this.allItems);
    }
}
