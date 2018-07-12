package planner.fitness.com.fitnessplanner;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import planner.fitness.com.fitnessplanner.controller.main.DayPlanPagerAdapter;
import planner.fitness.com.fitnessplanner.controller.main.DayPlanProvider;

public class MainActivity extends AppCompatActivity implements DayPlanProvider.Pipe {

    private DayPlanPagerAdapter pagerAdapter;
    private ViewPager mViewPager;

    private DayPlanProvider dayPlanProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set data
        dayPlanProvider = new DayPlanProvider();
        // TODO: initialize from async task

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        pagerAdapter = new DayPlanPagerAdapter(getSupportFragmentManager(), this, getProvider());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.ftpl_tab_layout);
        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, ConceptActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public DayPlanProvider getProvider() {
        return dayPlanProvider;
    }
}
