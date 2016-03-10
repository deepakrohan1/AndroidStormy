package rohan.com.stormy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
    import okhttp3.Callback;
    import okhttp3.OkHttpClient;
    import okhttp3.Request;
    import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private CurrentWeather currentWeather;

    public static final String TAG=MainActivity.class.getSimpleName();
    @Bind (R.id.textViewTemperature)TextView textViewTemperature;
    @Bind (R.id.textViewHumidity)TextView textViewHumidity;
    @Bind (R.id.textViewPrecipVal)TextView textViewPrecipVal;
    @Bind (R.id.textViewSummary)TextView textViewSummary;
    @Bind (R.id.textViewTime)TextView textViewTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        String apiKey = "b7fa25eb5a4b4cba3416c40d6ce34d43";
        String latitude = "37.8267";
        String longitude="-122.423";
        String urlApi = "https://api.forecast.io/forecast/" +apiKey+
                "/"+latitude+","+longitude+"";
        
        if (isNetworkAvailable()) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlApi)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    try {
                        String jsonData = response.body().string();
                        if (response.isSuccessful()) {
                            currentWeather = getCurrentDetails(jsonData);
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
        }else{
            Toast.makeText(this, "No network Available",Toast.LENGTH_LONG).show();
        }
    }

    private void updateUiForUser() {

        textViewTemperature.setText(currentWeather.getTemperature()+"");
    }

    private CurrentWeather getCurrentDetails(String jsonData) throws JSONException{
        JSONObject forecast = new JSONObject(jsonData);
        JSONObject currently = forecast.getJSONObject("currently");

        CurrentWeather currentWeather1 = new CurrentWeather();
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
        if(networkInfo.isConnected() && networkInfo != null){
            return true;
        }

        return false;
    }

    private void alertUserError(){
        AlertDialogFragment dialogFragment = new AlertDialogFragment();
        dialogFragment.show(getFragmentManager(),"error_message");
    }

}