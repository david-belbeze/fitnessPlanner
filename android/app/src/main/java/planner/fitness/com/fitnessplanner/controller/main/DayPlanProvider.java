package planner.fitness.com.fitnessplanner.controller.main;

import java.util.ArrayList;

import planner.fitness.com.fitnessplanner.data.DayPlan;
import planner.fitness.com.fitnessplanner.data.Schedule;

/**
 * This provider help to get the planning of days to load
 */

public class DayPlanProvider {

    private ArrayList<DayPlan> days;

    public DayPlanProvider() {
        days = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            days.add(new DayPlan(20180401 + i));
        }
    }

    /**
     * Get the day plan at the given index
     * @param index the index of the day plan
     * @return the json object of the day plan or null if an error occurred
     */
    public DayPlan getDayPlan(int index) {
        if (index >= 0 && index < days.size()) {
            return days.get(index);
        }
        return null;
    }

    /**
     * Count the number of days loaded
     * @return the number of days
     */
    public int getDayCount() {
        return days.size();
    }

    /**
     * Get the schedule item at the given index of the defined day
     * @param dayIndex the index of the day in json array
     * @param scheduleIndex the index of the schedule int the json array of schedule of the day
     * @return the json object which represent the schedule item or null if an error occurred
     */
    public Schedule getScheduleItem(int dayIndex, int scheduleIndex) {
        if (dayIndex >= 0 && dayIndex < days.size()) {
            DayPlan day = days.get(dayIndex);

            if (scheduleIndex >= 0 && scheduleIndex < day.size()) {
                return day.get(scheduleIndex);
            }
        }
        return null;
    }

    /**
     * Count the number of schedules for the day at the given index
     * @param index the index
     * @return the number of schedules
     */
    public int getScheduleCount(int index) {
        if (index >= 0 && index < days.size()) {
            return days.get(index).size();
        }

        return 0;
    }

    /**
     * This interface help to get the provider
     */
    public interface Pipe {

        /**
         * Get the day plan provider
         * @return the instance of the provider
         */
        DayPlanProvider getProvider();

    }

}
