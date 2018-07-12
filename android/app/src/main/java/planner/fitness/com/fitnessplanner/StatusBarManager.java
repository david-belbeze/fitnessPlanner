package planner.fitness.com.fitnessplanner;

import android.content.Context;

/**
 * This class help to manage the status bar view actions
 */

public class StatusBarManager {

    /**
     * Get the status bar height
     * @return the status bar height in pixel
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
