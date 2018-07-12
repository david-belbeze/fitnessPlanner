package planner.fitness.com.fitnessplanner.formatter;

import android.content.Context;

import belbeze.david.com.timeformatter.DurationUtils;

public class TimeTool {

    public static String format(Context context, long duration) {
        return new DurationUtils(context, duration).format(
                DurationUtils._HOUR|DurationUtils._MINUTE
        );
    }

}
