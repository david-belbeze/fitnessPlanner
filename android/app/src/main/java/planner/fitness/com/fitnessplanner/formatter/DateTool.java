package planner.fitness.com.fitnessplanner.formatter;

import java.util.Calendar;

public class DateTool {

    public static long convertIntDate(int date) {
        int day = date % 100;
        date /= 100;

        int month = date % 100;
        date /= 100; // is the same as year

        Calendar calendar = Calendar.getInstance();
        calendar.set(date, month - 1, day);

        return calendar.getTimeInMillis();
    }

}
