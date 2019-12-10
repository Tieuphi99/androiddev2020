package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
//import android.os.Handler;
//import android.os.Looper;
//import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {
    MediaPlayer mp;
    //    Handler handler;
    URL url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("create", "Noice");
        setContentView(R.layout.activity_weather);
        try {
            url = new URL("https://usth.edu.vn/uploads/logo_1.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
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

    private void volley() {
        Response.Listener<Bitmap> listener =
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        Drawable d = new BitmapDrawable(getResources(), response);
                        LinearLayout forecast_fragment = findViewById(R.id.forecast_fragment);
                        forecast_fragment.setBackground(d);
                    }
                };

        RequestQueue queue = Volley.newRequestQueue(this);
        ImageRequest imageRequest = new ImageRequest(
                "https://usth.edu.vn/uploads/logo_1.png",
                listener, 0, 0, ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888, null);
        queue.add(imageRequest);
    }

//    private class backGround extends AsyncTask<URL, Integer, Bitmap> {
//        Bitmap bitmap;
//
//        @Override
//        protected void onPreExecute() {
//        }
//
//        @Override
//        protected Bitmap doInBackground(URL... urls) {
//            try {
//                HttpURLConnection connection =
//                        (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");
//                connection.setDoInput(true);
//// allow reading response code and response dataconnection.
//                connection.connect();
//                // Receive response
//                int response = connection.getResponseCode();
//                Log.i("USTHWeather", "The response is: " + response);
//                InputStream is = connection.getInputStream();
//// Process image response
//                bitmap = BitmapFactory.decodeStream(is);
//                connection.disconnect();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return bitmap;
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//            super.onProgressUpdate(values);
//        }
//
//        @Override
//        protected void onPostExecute(Bitmap bitmap) {
//            Drawable d = new BitmapDrawable(getResources(), bitmap);
//            LinearLayout forecast_fragment = findViewById(R.id.forecast_fragment);
//            forecast_fragment.setBackground(d);
//            super.onPostExecute(bitmap);
//        }
//    }

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
//                new backGround().execute(url);
                volley();
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