package vn.edu.usth.weather;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class WeatherAndForecastFragment extends Fragment {

    String owm_url;
    TextView tv_place, tv_status;

    public WeatherAndForecastFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_weather_and_forecast, container, false);

        tv_place = v.findViewById(R.id.place);
        tv_status = v.findViewById(R.id.status);

        JSONParse();

        return v;
    }

    private void JSONParse() {
        owm_url = "https://api.openweathermap.org/data/2.5/forecast?id=1905577&APPID=2276e6b7e6bef00eb2c0e65e70b8a630";
        StringRequest request = new StringRequest(owm_url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("USTHWeather", "Json response " + response);
                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject object_city = object.getJSONObject("city");
                            String place = object_city.getString("name");
                            tv_place.setText(place);
                            JSONArray array = object.getJSONArray("list");
                            JSONObject object_today_12 = array.getJSONObject(2);
                            JSONObject object_today_main_12 = object_today_12.getJSONObject("main");
                            Double today_temp_12 = (double)Math.round(object_today_main_12.getDouble("temp") - 273.15);
                            String temp = today_temp_12 + "C";
                            tv_status.setText(temp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                });
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }
}
