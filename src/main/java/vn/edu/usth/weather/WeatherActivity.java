package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class WeatherActivity extends AppCompatActivity {
    MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("create", "Noice");
        setContentView(R.layout.activity_weather);
        mp = MediaPlayer.create(WeatherActivity.this, R.raw.anhlaai_phuongly);
        mp.start();
        Toolbar toolbar = findViewById(R.id.toolbar_weather);
        setSupportActionBar(toolbar);
        PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
        Extraction();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                Toast.makeText(this, "Congrazt, you pressed reset button", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
                return true;
            default:
                super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }


    private void Extraction() {
        String filePath = Environment.getExternalStorageDirectory() + "/Android/data/vn.edu.usth.weather/Anh la ai - Phuong Ly.mp3";
        try {
            InputStream is = getResources().openRawResource(R.raw.anhlaai_phuongly);
            OutputStream os = new FileOutputStream(filePath);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
            // Close the streams
            os.flush();
            os.close();
            is.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("start", "Cool");
    }

    @Override
    protected void onResume() {
        super.onResume();
        mp.start();
        Log.i("resume", "Very cool");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mp.pause();
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

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
        mp.pause();
    }
}