package planner.fitness.com.fitnessplanner.view.recyclerview.viewholder;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import planner.fitness.com.fitnessplanner.R;
import planner.fitness.com.fitnessplanner.controller.main.DayPlanAdapter;
import planner.fitness.com.fitnessplanner.data.Schedule;
import planner.fitness.com.fitnessplanner.formatter.TimeTool;

/**
 * The view holder used by the {@link DayPlanAdapter}
 */

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    private ImageView imageView;

    private TextView textViewName;

    private TextView textViewTime;
    private TextView textViewLocation;
    private TextView textViewDuration;

    public ScheduleViewHolder(View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.ftpl_image_view);

        textViewName = itemView.findViewById(R.id.ftpl_text_view_name);

        textViewTime = itemView.findViewById(R.id.ftpl_text_view_time);
        textViewLocation = itemView.findViewById(R.id.ftpl_text_view_location);
        textViewDuration = itemView.findViewById(R.id.ftpl_text_view_duration);
    }

    /**
     * This method is called to update the content of the view holder from the data
     */
    public void update(Schedule scheduleData) {
        textViewName.setText(scheduleData.getName());

        // set schedule with fine format
        java.text.DateFormat timeFormat = DateFormat.getTimeFormat(itemView.getContext());
        textViewTime.setText(timeFormat.format(scheduleData.getTime()));

        textViewLocation.setText(scheduleData.getPlace());

        textViewDuration.setText(TimeTool.format(itemView.getContext(), scheduleData.getDuration()));

        Picasso.get().load(scheduleData.getConceptSrc()).centerCrop().into(imageView);
    }

}
