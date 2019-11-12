package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("create", "Noice");
        setContentView(R.layout.activity_weather);
        // Create a new Fragment to be placed in the activity layout
        ForecastFragment firstFragment = new ForecastFragment();
        // Add the fragment to the 'container' FrameLayout
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, firstFragment).commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("start", "Cool");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("resume", "Very cool");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("pause", "Not very cool");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("stop", "Not cool");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("destroy", "Ur ded");
    }
}