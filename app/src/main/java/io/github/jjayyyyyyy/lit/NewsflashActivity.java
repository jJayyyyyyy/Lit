package io.github.jjayyyyyyy.lit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by steve on 3/10/17.
 */

public class NewsflashActivity extends AppCompatActivity {

    private static final String URL_NEWSFLASH = "http://new.36kr.com/newsflashes.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        new NewsflashAsyncTask().execute();
    }


    protected void onLoadFinished() {
        View progressBar = (View) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }


    private void showNewsflash(ArrayList<NewsItem> newsflashList) {

        NewsAdapter newsflashAdapter = new NewsAdapter(this, newsflashList);
        ListView newsflashListView = (ListView) findViewById(R.id.news_list);
        newsflashListView.setAdapter(newsflashAdapter);
    }


    public ArrayList<NewsItem> getNewsflash(String newsflashResp) {
        if (newsflashResp == "") {
            return null;
        }

        ArrayList<NewsItem> newsflashList = new ArrayList<>();
        try {
            JSONObject jsonNewsflashObject = new JSONObject(newsflashResp);
            jsonNewsflashObject = jsonNewsflashObject.getJSONObject("props");
            JSONArray jsonNewsflashArray = jsonNewsflashObject.getJSONArray("newsflashList|newsflash");
            String title;

            for (int i = 0; i < jsonNewsflashArray.length(); i++) {
                title = jsonNewsflashArray.getJSONObject(i).getString("title");
                newsflashList.add(new NewsItem(title + "\n\n"));
            }
        } catch (JSONException e) {
            return null;
        }
        return newsflashList;
    }


    private class NewsflashAsyncTask extends AsyncTask<Void, Void, ArrayList<NewsItem>> {

        @Override
        protected ArrayList<NewsItem> doInBackground(Void... useHardCoded) {
            String newsflashResp = "";
            Request newsflashReq = new Request();
            try {
                newsflashResp = newsflashReq.makeHttpRequest(URL_NEWSFLASH);
            } catch (IOException e) {
                return null;
            }
            return getNewsflash(newsflashResp);
        }

        @Override
        protected void onPostExecute(ArrayList<NewsItem> newsflashList) {
            onLoadFinished();
            if (newsflashList != null) {
                showNewsflash(newsflashList);
            }
        }
    }
}
