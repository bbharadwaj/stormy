package com.brijesh.stormy.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brijesh.stormy.R;
import com.brijesh.stormy.adapters.DayAdapter;
import com.brijesh.stormy.weather.Day;

import java.util.Arrays;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DailyForecastActivity extends Activity {

    private Day[] mDays;

    @Bind(android.R.id.list) ListView mListView;
    @Bind(android.R.id.empty) TextView mEmptyTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        ButterKnife.bind(this);


        Intent intent = getIntent();
        Parcelable[] parcelables = intent.getParcelableArrayExtra(MainActivity.DAILY_FORECAST);
        mDays = Arrays.copyOf(parcelables, parcelables.length, Day[].class);


        DayAdapter adapter = new DayAdapter(this, mDays);
        mListView.setAdapter(adapter);

        mListView.setEmptyView(mEmptyTextView);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String dayOfTheWeek = mDays[position].getDayOfTheWeek();
                String summary = mDays[position].getSummary();
                String tempMax = mDays[position].getTemperatureMax() + "";

                String message = String.format("On %s the high will be %s and it will be %s",
                        dayOfTheWeek,tempMax,summary);

                Toast.makeText(DailyForecastActivity.this, message, Toast.LENGTH_LONG).show();

            }
        });


    }

}
