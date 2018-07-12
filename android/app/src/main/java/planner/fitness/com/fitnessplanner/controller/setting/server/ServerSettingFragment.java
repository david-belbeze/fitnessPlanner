package planner.fitness.com.fitnessplanner.controller.setting.server;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import planner.fitness.com.fitnessplanner.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ServerSettingFragment extends Fragment {


    public ServerSettingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_server_setting, container, false);
    }

}
