package io.github.jjayyyyyyy.lit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by steve on 3/10/17.
 */

public class HackerNewsActivity extends AppCompatActivity {

    private static final String URL_HACKER_NEWS = "https://news.ycombinator.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
    }
}
