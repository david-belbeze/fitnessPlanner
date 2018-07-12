package planner.fitness.com.fitnessplanner.controller.main;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import planner.fitness.com.fitnessplanner.R;
import planner.fitness.com.fitnessplanner.view.recyclerview.SpaceDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class DayPlanFragment extends Fragment {

    /**
     * This index is the position in the day plan provider of the day
     */
    private int index;

    private DayPlanProvider dayPlanProvider;

    private DayPlanAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_day_plan, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.ftpl_recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new SpaceDecoration(
                getResources().getDimensionPixelSize(R.dimen.dimen_8)
        ));

        recyclerView.setLayoutManager(getLayoutManager());

        adapter = new DayPlanAdapter(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DayPlanProvider.Pipe) {
            dayPlanProvider = ((DayPlanProvider.Pipe) context).getProvider();
        }
    }

    /**
     * This method get the best layout manager
     * @return the selected layout manager
     */
    private RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public DayPlanProvider getProvider() {
        return dayPlanProvider;
    }
}
