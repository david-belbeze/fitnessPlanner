package planner.fitness.com.fitnessplanner.controller.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import planner.fitness.com.fitnessplanner.R;
import planner.fitness.com.fitnessplanner.view.recyclerview.viewholder.ScheduleViewHolder;

/**
 * Created by david on 28/03/18.
 */

public class DayPlanAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {

    private DayPlanProvider mProvider;
    private int mIndex;

    private DayPlanFragment mDayPlanFragment;

    /**
     * @param provider the provider to use to get the item
     * @param index the index of the day to load
     */
    public DayPlanAdapter(DayPlanProvider provider, int index) {
        mProvider = provider;
        mIndex = index;
    }

    public DayPlanAdapter(DayPlanFragment dayPlanFragment) {
        mDayPlanFragment = dayPlanFragment;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ScheduleViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.schedule_view_item,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        holder.update(
                mDayPlanFragment.getProvider().getScheduleItem(mDayPlanFragment.getIndex(), position)
        );
    }

    @Override
    public int getItemCount() {
        return mProvider.getScheduleCount(mIndex);
    }
}
