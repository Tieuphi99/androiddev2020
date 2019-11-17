package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class WeatherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("create", "Noice");
        setContentView(R.layout.activity_weather);
//         Create a new Fragment to be placed in the activity layout
        WeatherFragment secondFragment = new WeatherFragment();
        ForecastFragment firstFragment = new ForecastFragment();
        WeatherAndForecastFragment Fragment1 = new WeatherAndForecastFragment();
        WeatherAndForecastFragment Fragment2 = new WeatherAndForecastFragment();
        WeatherAndForecastFragment Fragment3 = new WeatherAndForecastFragment();
//         Add the fragment to the 'container' FrameLayout
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, secondFragment).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, firstFragment).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, Fragment1).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, Fragment2).commit();
        getSupportFragmentManager().beginTransaction().add(
                R.id.container, Fragment3).commit();
        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
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