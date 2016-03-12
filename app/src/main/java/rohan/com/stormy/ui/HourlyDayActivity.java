package rohan.com.stormy.ui;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import rohan.com.stormy.R;
import rohan.com.stormy.adapters.HourAdapter;
import rohan.com.stormy.model.Day;
import rohan.com.stormy.model.Hour;

public class HourlyDayActivity extends AppCompatActivity {

    ArrayList<Hour> hourArrayList;
    @Bind (R.id.recyclerView) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hourly_day);
        ButterKnife.bind(this);


        if(getIntent().getExtras() != null){
            hourArrayList = getIntent().getExtras().getParcelableArrayList(MainActivity.DAY_HOUR);
        }

        HourAdapter hourAdapter = new HourAdapter(this,hourArrayList);
        recyclerView.setAdapter(hourAdapter);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

}
