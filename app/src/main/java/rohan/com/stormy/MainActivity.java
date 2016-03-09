package rohan.com.stormy;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG=MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String apiKey = "b7fa25eb5a4b4cba3416c40d6ce34d43";
        String urlApi = "https://api.forecast.io/forecast/" +apiKey+ "/37.8267,-122.423";
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
//                    response = call.execute();
                    if(response.isSuccessful()){
                        Log.v(TAG, response.body().string());
                    }
                } catch (IOException e) {
//            e.printStackTrace();
                    Log.e(TAG,"Exception caught "+e );
                }

            }
        });




    }

}