package io.github.jjayyyyyyy.lit;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String URL_WEATHER_HZ = "http://m.weather.com.cn/mweather/101210101.shtml";
    private static final String URL_WEATHER_SH = "http://m.weather.com.cn/mweather/101020100.shtml";
    private static final String URL_DICT_BASE = "http://dict.youdao.com/fsearch?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Click to get the Weather of today and tomorrow
        TextView weather = (TextView) findViewById(R.id.weather);
        weather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WeatherAsyncTask().execute(URL_WEATHER_HZ);
            }
        });


        // Translate EN to/from CN
        SearchView mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                new TranslationAsyncTask().execute(URL_DICT_BASE + query);
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
                Intent hackerNewsIntent = new Intent(MainActivity.this, HackerNewsActivity.class);
                startActivity(hackerNewsIntent);
            }
        });


        // Click to create a new SolidotActivity
        TextView solidot = (TextView) findViewById(R.id.solidot);
        solidot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent solidotIntent = new Intent(MainActivity.this, SolidotActivity.class);
                startActivity(solidotIntent);
            }
        });


        // Click to create a new NewsflashActivity
        TextView newsflash = (TextView) findViewById(R.id.newsflash);
        newsflash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsflashIntent = new Intent(MainActivity.this, NewsflashActivity.class);
                startActivity(newsflashIntent);
            }
        });
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
                }
            }
        }
    }

}
