package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {
    MediaPlayer mp;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("create", "Noice");
        setContentView(R.layout.activity_weather);
        mp = MediaPlayer.create(WeatherActivity.this, R.raw.anhlaai_phuongly);
        mp.start();
        Toolbar toolbar = findViewById(R.id.toolbar_weather);
        setSupportActionBar(toolbar);
        final PagerAdapter adapter = new HomeFragmentPagerAdapter(
                getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);
        pager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tab);
        tabLayout.setupWithViewPager(pager);
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


    private class backGround extends AsyncTask<Void, Integer, String> {
        @Override
        protected void onPreExecute() {
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
// wait for 5 seconds to simulate a long network access
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String server_response = "some sample json here";
            return server_response;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String string) {
            Toast.makeText(WeatherActivity.this, string, Toast.LENGTH_LONG).show();
            super.onPostExecute(string);
        }
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
                new backGround().execute();
                Toast.makeText(this, "Refreshing", Toast.LENGTH_SHORT).show();
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