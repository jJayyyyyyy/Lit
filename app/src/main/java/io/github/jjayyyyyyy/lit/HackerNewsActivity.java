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

public class HackerNewsActivity extends AppCompatActivity {

    private static final String URL_HACKER_NEWS = "https://news.ycombinator.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        new HackerNewsAsyncTask().execute();
    }

    protected void onLoadFinished() {
        View progressBar = (View) findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);
    }

    private void showHackerNews(ArrayList<NewsItem> hackerNewsList) {

        NewsAdapter hackerNewsAdapter = new NewsAdapter(this, hackerNewsList);
        ListView hackerNewsListView = (ListView) findViewById(R.id.news_list);
        hackerNewsListView.setAdapter(hackerNewsAdapter);
    }

    public ArrayList<NewsItem> getHackerNews(String hackerNewsResp) {
        if (hackerNewsResp == "") {
            return null;
        }

        String reHackerNews = "storylink\">(.*?)</a>";
        Matcher mHackerNews = Pattern.compile(reHackerNews).matcher(hackerNewsResp);

        ArrayList<NewsItem> hackerNewsList = new ArrayList<>();
        while (mHackerNews.find()) {
            hackerNewsList.add(new NewsItem(mHackerNews.group(1) + "\n\n"));
        }

        return hackerNewsList;
    }

    private class HackerNewsAsyncTask extends AsyncTask<Void, Void, ArrayList<NewsItem>> {

        @Override
        protected ArrayList<NewsItem> doInBackground(Void... useHardCoded) {

            String hackerNewsResp = "";
            Request hackerNewsReq = new Request();
            try {
                hackerNewsResp = hackerNewsReq.makeHttpRequest(URL_HACKER_NEWS);
            } catch (IOException e) {
                return null;
            }

            return getHackerNews(hackerNewsResp);
        }

        @Override
        protected void onPostExecute(ArrayList<NewsItem> hackerNewsList) {
            onLoadFinished();
            if (hackerNewsList != null) {
                showHackerNews(hackerNewsList);
            }
        }
    }
}
