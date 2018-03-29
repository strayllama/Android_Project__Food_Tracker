package lab.codeclan.foodtrackerapp.Models;

import java.util.ArrayList;

public class SummaryUtility {
    private SummaryUtility(){}

    public static ArrayList<DaySummaries> combineFiveCalWaterArrays(ArrayList<DaySummaryFiveAday> listDaySummaryFiveAday, ArrayList<DaySummaryCals> listDaySummaryCals, ArrayList<DaySummaryWater> listDaySummaryWater) {
        ArrayList<DaySummaries> listDaySummaries = new ArrayList<>();

        // Loop through the list arrays from the queries, create day summary with 5aday then add water and cals if exist.
        for (DaySummaryFiveAday dayF : listDaySummaryFiveAday) {
            DaySummaries daySummary = new DaySummaries(dayF.getDate(), dayF.getDayFiveAday());
            for (DaySummaryWater dayW : listDaySummaryWater) {
                if (dayW.getDate().equals(dayF.getDate())) {
                    daySummary.addDayWater(dayW.getDayWater());
                }
            }
            for (DaySummaryCals dayC : listDaySummaryCals) {
                if (dayC.getDate().equals(dayF.getDate())) {
                    daySummary.addDayCalories(dayC.getDayCalories());
                }
            }
            listDaySummaries.add(daySummary);

        }
        return listDaySummaries;
    }

    public static ArrayList<Integer> sumAndAverageCalWaterFruit(ArrayList<DaySummaries> daySummaries) {
        ArrayList<Integer> summedCalWaterFruit = new ArrayList<>();
        Integer cal = 0;
        Integer water = 0;
        Integer fruit = 0;

        for (DaySummaries day : daySummaries) {
            cal += day.getDayCalories();
            water += day.getDayWater();
            fruit += day.getDayFiveAday();
        }

        summedCalWaterFruit.add((cal / daySummaries.size()));
        summedCalWaterFruit.add((water / daySummaries.size()));
        summedCalWaterFruit.add((fruit / daySummaries.size()));

        return summedCalWaterFruit;
    }


}
