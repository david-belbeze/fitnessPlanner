package planner.fitness.com.fitnessplanner.data;

import java.util.ArrayList;

public class DayPlan extends ArrayList<Schedule> {

    /**
     * This variable is as AAAAMMDD
     */
    private int date;

    public DayPlan(int date) {

        this.date = date;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }
}
