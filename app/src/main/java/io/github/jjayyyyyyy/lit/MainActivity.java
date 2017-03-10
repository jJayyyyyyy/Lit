package io.github.jjayyyyyyy.lit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String URL_WEATHER_JH = "http://m.weather.com.cn/mweather/101210901.shtml";
    private static final String URL_WEATHER_SH = "http://m.weather.com.cn/mweather/101020100.shtml";
    private static final String URL_DICT_BASE = "http://dict.youdao.com/fsearch?q=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView hackerNews = (TextView) findViewById(R.id.hacker_news);
        hackerNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent hackerNewsIntent = new Intent(MainActivity.this, HackerNewsActivity.class);
                startActivity(hackerNewsIntent);
            }
        });


        final TextView solidot = (TextView) findViewById(R.id.solidot);
        solidot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent solidotIntent = new Intent(MainActivity.this, SolidotActivity.class);
                startActivity(solidotIntent);
            }
        });

        TextView newsflash = (TextView) findViewById(R.id.newsflash);
        newsflash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newsflashIntent = new Intent(MainActivity.this, NewsflashActivity.class);
                startActivity(newsflashIntent);
            }
        });

        final SearchView mSearchView = (SearchView) findViewById(R.id.search_view);
        mSearchView.setSubmitButtonEnabled(true);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                TextView translation = (TextView) findViewById(R.id.translation);
                translation.setText(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


    }
}
