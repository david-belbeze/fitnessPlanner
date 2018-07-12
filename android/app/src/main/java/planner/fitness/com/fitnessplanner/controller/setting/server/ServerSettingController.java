package planner.fitness.com.fitnessplanner.controller.setting.server;

import android.support.v4.app.Fragment;

/**
 * Created by david on 27/03/18.
 */

public class ServerSettingController {

    public static final int FRAGMENT_STATE = 0;
    public static final int FRAGMENT_CITY = 1;
    public static final int FRAGMENT_ORGANISATION = 2;

    private int currentFragmentId;

    private static Fragment[] fragments;

    public ServerSettingController() {
        this(FRAGMENT_STATE);
    }

    public ServerSettingController(int currentFragmentId) {
        currentFragmentId = currentFragmentId;
    }

    public static Fragment[] getFragment() {
        return fragments;
    }
}
