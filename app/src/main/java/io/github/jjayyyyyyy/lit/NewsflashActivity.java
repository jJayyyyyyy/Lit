package io.github.jjayyyyyyy.lit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by steve on 3/10/17.
 */

public class NewsflashActivity extends AppCompatActivity {

    private static final String URL_NEWSFLASH = "http://new.36kr.com/newsflashes.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
    }
}
