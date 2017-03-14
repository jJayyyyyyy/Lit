package io.github.jjayyyyyyy.lit;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String URL_WEATHER_SH = "http://m.weather.com.cn/mweather/101020100.shtml";
    private static final String URL_WEATHER_HA = "http://m.weather.com.cn/mweather/101190502.shtml";
    private static final String URL_WEATHER_HZ = "http://m.weather.com.cn/mweather/101210101.shtml";
    private static final String URL_WEATHER_PJ = "http://m.weather.com.cn/mweather/101210902.shtml";

    private static final String URL_DICT_BASE = "http://dict.youdao.com/fsearch?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ArrayList<String> cityList = new ArrayList<>();
        cityList.add(URL_WEATHER_SH);
        cityList.add(URL_WEATHER_HA);
        cityList.add(URL_WEATHER_HZ);
        cityList.add(URL_WEATHER_PJ);


        Spinner spinner = (Spinner) findViewById(R.id.city_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.city_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                if(pos!=0){
                    if(hasNetwork()){
                        new WeatherAsyncTask().execute(cityList.get(pos-1));
                    }else {
                        Toast.makeText(getApplicationContext(), "警告！手机将在10秒后爆炸！", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                return;
            }
        });

        // Click to get the Weather of today and tomorrow
//        TextView weather = (TextView) findViewById(R.id.weather);
//        weather.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(hasNetwork()){
//                    new WeatherAsyncTask().execute(URL_WEATHER_HZ);
//                }else {
//                    Toast.makeText(getApplicationContext(), "No Network", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });


        // Translate EN to/from CN
        SearchView mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(hasNetwork()){
                    try {
                        new TranslationAsyncTask().execute(URL_DICT_BASE + URLEncoder.encode(query, "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "BANG!!!", Toast.LENGTH_SHORT).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        // Click to create a new HackerNewsActivity
        TextView hackerNews = (TextView) findViewById(R.id.hacker_news);
        hackerNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasNetwork()){
                    Intent hackerNewsIntent = new Intent(MainActivity.this, HackerNewsActivity.class);
                    startActivity(hackerNewsIntent);
                }else {
                    Toast.makeText(getApplicationContext(), "别戳我，还没联网呢", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Click to create a new SolidotActivity
        TextView solidot = (TextView) findViewById(R.id.solidot);
        solidot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasNetwork()){
                    Intent solidotIntent = new Intent(MainActivity.this, SolidotActivity.class);
                    startActivity(solidotIntent);
                }else {
                    Toast.makeText(getApplicationContext(), "Life is short, you need Python", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Click to create a new NewsflashActivity
        TextView newsflash = (TextView) findViewById(R.id.newsflash);
        newsflash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(hasNetwork()){
                    Intent newsflashIntent = new Intent(MainActivity.this, NewsflashActivity.class);
                    startActivity(newsflashIntent);
                }else {
                    Toast.makeText(getApplicationContext(), "  Verweile doch\nDu bist so schön", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean hasNetwork() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    // custom AsyncTask to get Weather from m.weather.com.cn
    private class WeatherAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... strWeatherUrl) {
            if (strWeatherUrl[0] == "") {
                return null;
            }
            String weatherResp = "";
            Request weatherReq = new Request();
            try {
                weatherResp = weatherReq.makeHttpRequest(strWeatherUrl[0]);
            } catch (IOException e) {
                return null;
            }
            return weatherReq.getWeather(weatherResp);
        }

        @Override
        protected void onPostExecute(ArrayList<String> weatherList) {
            if (weatherList != null) {
                // private void showWeather(ArrayList<String> weatherList)
                if (weatherList != null && weatherList.size() != 0) {
                    TextView weatherView = (TextView) findViewById(R.id.weather);
                    if (weatherList.size() == 2) {
                        weatherView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
                        weatherView.setText(weatherList.get(0) + weatherList.get(1));
                    }
                }
            }
        }
    }


    // custom AsyncTask to get Translation from dict.youdao.com
    private class TranslationAsyncTask extends AsyncTask<String, Void, ArrayList<String>> {

        @Override
        protected ArrayList<String> doInBackground(String... strTransUrl) {
            if (strTransUrl[0] == "") {
                return null;
            }
            String transResp = "";
            Request transReq = new Request();
            try {
                transResp = transReq.makeHttpRequest(strTransUrl[0]);
            } catch (IOException e) {
                return null;
            }
            return transReq.getTranslation(transResp);
        }

        @Override
        protected void onPostExecute(ArrayList<String> transList) {
            if (transList != null) {
                // private void showTranslation(ArrayList<String> transList)
                if (transList != null && transList.size() != 0) {
                    TextView translation = (TextView) findViewById(R.id.translation);
                    if (transList.size() == 1) {
                        translation.setText(transList.get(0));
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "再试一次~", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
