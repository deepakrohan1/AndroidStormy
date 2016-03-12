package rohan.com.stormy.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import rohan.com.stormy.R;
import rohan.com.stormy.model.Day;

public class HourlyDayActivity extends AppCompatActivity {

    ArrayList<Day> daysList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_day);


        if(getIntent().getExtras() != null){
            daysList = getIntent().getExtras().getParcelableArrayList(MainActivity.DAY_HOUR);
        }

    }

}
