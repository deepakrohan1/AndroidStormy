package rohan.com.stormy.ui;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.OnClick;
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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String message = daysList.get(position).getSummary();
        String temperature = daysList.get(position).getMaxTemperature()+"";
        String dayOfWeek = daysList.get(position).getDayOfWeek();

        String finalS = String.format("On %s it will be %s and today %s",dayOfWeek,temperature,message);
        Toast.makeText(this,finalS,Toast.LENGTH_SHORT).show();
    }
}
