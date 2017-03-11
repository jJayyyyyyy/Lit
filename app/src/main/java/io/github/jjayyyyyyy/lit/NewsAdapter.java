package io.github.jjayyyyyyy.lit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by steve on 3/11/17.
 */

public class NewsAdapter extends ArrayAdapter<NewsItem> {

    public NewsAdapter(Activity context, ArrayList<NewsItem> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.activity_news_list_item, parent, false);
        }
        NewsItem news = getItem(position);
        TextView newsTitleView = (TextView) listItemView.findViewById(R.id.news_list_title);
        newsTitleView.setText(news.getTitle());
        return listItemView;
    }

}
