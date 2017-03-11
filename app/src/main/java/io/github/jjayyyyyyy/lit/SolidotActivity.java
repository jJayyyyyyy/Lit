package io.github.jjayyyyyyy.lit;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by steve on 3/10/17.
 */

public class SolidotActivity extends AppCompatActivity {

    private static final String URL_SOLIDOT = "http://www.solidot.org/index.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        new SolidotAsyncTask().execute();
    }

    protected void onLoadFinished() {
        View progressBar = (View) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    private void showSolidot(ArrayList<NewsItem> solidotList) {

        NewsAdapter solidotAdapter = new NewsAdapter(this, solidotList);
        ListView solidotListView = (ListView) findViewById(R.id.news_list);
        solidotListView.setAdapter(solidotAdapter);
    }

    public ArrayList<NewsItem> getSolidot(String solidotResp) {
        if (solidotResp == "") {
            return null;
        }

        String reSolidot = "<title><!\\[CDATA\\[(.*?)\\]\\]>";
        Matcher mSolidot = Pattern.compile(reSolidot).matcher(solidotResp);

        ArrayList<NewsItem> hackerNewsList = new ArrayList<>();
        while (mSolidot.find()) {
            hackerNewsList.add(new NewsItem(mSolidot.group(1) + "\n\n"));
        }

        return hackerNewsList;
    }

    private class SolidotAsyncTask extends AsyncTask<Void, Void, ArrayList<NewsItem>> {

        @Override
        protected ArrayList<NewsItem> doInBackground(Void... useHardCoded) {

            String solidotResp = "";
            Request solidotReq = new Request();
            try {
                solidotResp = solidotReq.makeHttpRequest(URL_SOLIDOT);
            } catch (IOException e) {
                return null;
            }

            return getSolidot(solidotResp);
        }

        @Override
        protected void onPostExecute(ArrayList<NewsItem> hackerNewsList) {
            onLoadFinished();
            if (hackerNewsList != null) {
                showSolidot(hackerNewsList);
            }
        }
    }
}
