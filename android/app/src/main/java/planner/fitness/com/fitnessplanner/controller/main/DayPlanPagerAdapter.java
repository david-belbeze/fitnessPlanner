package planner.fitness.com.fitnessplanner.controller.main;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.format.DateFormat;

import planner.fitness.com.fitnessplanner.formatter.DateTool;

public class DayPlanPagerAdapter extends FragmentPagerAdapter {

    private DayPlanProvider provider;
    private Context context;

    public DayPlanPagerAdapter(FragmentManager fm, Context context, DayPlanProvider provider) {
        super(fm);
        this.context = context;
        this.provider = provider;
    }

    @Override
    public int getCount() {
        return provider.getDayCount();
    }

    @Override
    public Fragment getItem(int position) {
        DayPlanFragment fragment = new DayPlanFragment();

        fragment.setIndex(position);

        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        long dayTime = DateTool.convertIntDate(provider.getDayPlan(position).getDate());
        java.text.DateFormat format = DateFormat.getMediumDateFormat(context);
        return format.format(dayTime);
    }
}
