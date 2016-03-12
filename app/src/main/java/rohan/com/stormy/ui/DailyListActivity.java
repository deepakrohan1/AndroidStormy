package rohan.com.stormy.ui;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

import rohan.com.stormy.R;
import rohan.com.stormy.adapters.DayAdapter;
import rohan.com.stormy.model.Day;

public class DailyListActivity extends ListActivity {

    ArrayList<Day> daysList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_list);

        if(getIntent().getExtras() != null){
            daysList = getIntent().getExtras().getParcelableArrayList(MainActivity.DAY_DATA);
            Log.d("DailyA", daysList.toString());

        }

        DayAdapter adapter = new DayAdapter(this,daysList);
        setListAdapter(adapter);
    }

}
