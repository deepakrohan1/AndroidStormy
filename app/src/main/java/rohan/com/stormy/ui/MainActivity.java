package rohan.com.stormy.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import rohan.com.stormy.R;
import rohan.com.stormy.model.Current;
import rohan.com.stormy.model.Day;
import rohan.com.stormy.model.Forecast;
import rohan.com.stormy.model.Hour;

public class MainActivity extends AppCompatActivity {

    private Forecast forecast;

    public static final String TAG = MainActivity.class.getSimpleName();
    public static final String DAY_DATA="day_info";
    public static final String DAY_HOUR="day_hour";
    @Bind(R.id.textViewTemperature) TextView textViewTemperature;
    @Bind(R.id.textViewHumidity) TextView textViewHumidity;
    @Bind(R.id.textViewPrecipVal) TextView textViewPrecipVal;
    @Bind(R.id.textViewSummary) TextView textViewSummary;
    @Bind(R.id.textViewTime) TextView textViewTime;
    @Bind(R.id.imageViewIcon) ImageView imageViewIcon;
    @Bind(R.id.imageViewRefresh) ImageView imageViewRefresh;
    @Bind(R.id.progressBar) ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        progressBar.setVisibility(View.INVISIBLE);
        final String apiKey = "b7fa25eb5a4b4cba3416c40d6ce34d43";
        final String latitude = "37.8267";
        final String longitude = "-122.423";
        getForecast(apiKey, latitude, longitude);

        imageViewRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getForecast(apiKey, latitude, longitude);
            }
        });
    }

    private void getForecast(String apiKey, String latitude, String longitude) {
        String urlApi = "https://api.forecast.io/forecast/" + apiKey +
                "/" + latitude + "," + longitude + "";

        if (isNetworkAvailable()) {
            toggleRefresh();
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            toggleRefresh();
                        }
                    });
                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            forecast = parseForecastConditions(jsonData);
                            /**
                             * Run Asynch tasks on UI thread
                             */
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    updateUiForUser();
                                }
                            });
                            Log.v(TAG, response.body().string());
                        } else {
                            alertUserError();
                        }
                    } catch (IOException e) {
                        Log.e(TAG, "Exception caught " + e);
                    } catch (JSONException e) {
                        Log.e(TAG, "Exception caught " + e);
                    }
                }
            });
        } else {
            Toast.makeText(this, "No network Available", Toast.LENGTH_LONG).show();
        }
    }

    private void toggleRefresh() {
        if (progressBar.getVisibility() == View.INVISIBLE) {
            imageViewRefresh.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);
        } else {
            imageViewRefresh.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void updateUiForUser() {
        Current currentWeather = forecast.getCurrentWeather();

        textViewTemperature.setText(currentWeather.getTemperature() + "");
        textViewTime.setText("At " + currentWeather.getFormattedTime() + " it will be");
        textViewHumidity.setText(currentWeather.getHumidity() + "");
        textViewPrecipVal.setText(currentWeather.getPrecipChance() + " %");
        textViewSummary.setText(currentWeather.getSummary());
        Drawable drawable = getResources().getDrawable(currentWeather.getIconId());
        imageViewIcon.setImageDrawable(drawable);

    }

    private Forecast parseForecastConditions(String jsonData) throws JSONException {
        Forecast forecast1 = new Forecast();
        forecast1.setCurrentWeather(getCurrentDetails(jsonData));
        forecast1.setDaysList(getDailyDetails(jsonData));
        forecast1.setHoursList(getHourlyDetails(jsonData));

        return forecast1;
    }

    private ArrayList<Hour> getHourlyDetails(String jsonData) throws JSONException{
        ArrayList<Hour> hoursList = new ArrayList<>();
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");

        JSONObject hourly = forecast.getJSONObject("hourly");
        JSONArray data = hourly.getJSONArray("data");

        for (int i = 0; i < data.length();i++){
            JSONObject jsonObject = data.getJSONObject(i);
            Hour hour = new Hour();
            hour.setSummary(jsonObject.getString("summary"));
            hour.setTime(jsonObject.getLong("time"));
            hour.setTemperature(jsonObject.getDouble("temperature"));
            hour.setIcon(jsonObject.getString("icon"));
            hour.setTimezone(timeZone);

            hoursList.add(hour);
        }

        return hoursList;
    }

    private ArrayList<Day> getDailyDetails(String jsonData) throws JSONException{
        ArrayList<Day> daysList = new ArrayList<>();
        JSONObject forecast = new JSONObject(jsonData);
        String timeZone = forecast.getString("timezone");

        JSONObject daily = forecast.getJSONObject("daily");
        JSONArray data = daily.getJSONArray("data");

        for (int i= 0; i < data.length(); i++){
            JSONObject jsonObject = data.getJSONObject(i);
            Day day = new Day();
            day.setTimezone(timeZone);
            day.setTime(jsonObject.getLong("time"));
            day.setIcon(jsonObject.getString("icon"));
            day.setSummary(jsonObject.getString("summary"));
            day.setMaxTemperature(jsonObject.getDouble("temperatureMax"));

            daysList.add(day);
        }

        return daysList;
    }

    private Current getCurrentDetails(String jsonData) throws JSONException {
        JSONObject forecast = new JSONObject(jsonData);
        JSONObject currently = forecast.getJSONObject("currently");

        Current currentWeather1 = new Current();
        currentWeather1.setIcon(currently.getString("icon"));
        currentWeather1.setHumidity(currently.getDouble("humidity"));
        currentWeather1.setPrecipChance(currently.getDouble("precipProbability"));
        currentWeather1.setTemperature(currently.getDouble("temperature"));
        currentWeather1.setSummary(currently.getString("summary"));
        currentWeather1.setTime(currently.getLong("time"));
        currentWeather1.setTimeZone(forecast.getString("timezone"));

        Log.d(TAG, currentWeather1.getFormattedTime());

        return currentWeather1;
    }

    private boolean isNetworkAvailable() {

        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo.isConnected() && networkInfo != null) {
            return true;
        }

        return false;
    }

    private void alertUserError() {
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(), "error_message");
    }

    @OnClick (R.id.buttonWeek)
    public void onClickWeek(View view){
        Intent i = new Intent(this, DailyListActivity.class);
        i.putExtra(DAY_DATA,forecast.getDaysList());
        startActivity(i);
    }

    @OnClick(R.id.buttonHour)
    public void onClickHour(View view){
        Intent i = new Intent(this, HourlyDayActivity.class);
//        i.putExtra(DAY_HOUR, forecast.getHoursList());
        i.putParcelableArrayListExtra(DAY_HOUR,forecast.getHoursList());
        startActivity(i);
    }

}