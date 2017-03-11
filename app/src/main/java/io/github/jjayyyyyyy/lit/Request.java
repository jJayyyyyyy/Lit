package io.github.jjayyyyyyy.lit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by steve on 3/11/17.
 */

public class Request {
    private String mRequest;

    private URL getUrl(String strUrl) {
        URL url = null;
        try {
            url = new URL(strUrl);
        } catch (MalformedURLException e) {
            return null;
        }
        return url;
    }

    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                stringBuilder.append(line);
                line = reader.readLine();
            }
        }
        return stringBuilder.toString();
    }

    public String makeHttpRequest(String strUrl) throws IOException {
        URL url = getUrl(strUrl);
        String strResp = "";

        if (url == null) {
            return strResp;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;

        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(10000);
            urlConnection.connect();

            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                strResp = readFromStream(inputStream);
            }
        } catch (IOException e) {
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return strResp;
    }

    public ArrayList<String> getWeather(String weatherResp) {
        if (weatherResp == "") {
            return null;
        }
        String reAQI = "\"aqi\":\"([0-9]*)\"";
        String reDay = "<li><b>(今天)</b><i><img src=\".*?\" alt=\"(.*?)\"/><img src=\".*?\" alt=\"(.*?)\"/></i><span>(.*?)</span><em></em></li>";
        String reNight = "<li><b>(夜间)</b>.*?<i><img src=\".*?\" alt=\"(.*?)\"/></i><span>(.*?)</span>";
        String reTomorrow = "<li><b>(明天)</b><i><img src=\".*?\" alt=\"(.*?)\"/><img src=\".*?\" alt=\"(.*?)\"/></i><span>(.*?)</span><em></em></li>";

        Matcher mAQI = Pattern.compile(reAQI).matcher(weatherResp);
        Matcher mDay = Pattern.compile(reDay).matcher(weatherResp);
        Matcher mNight = Pattern.compile(reNight).matcher(weatherResp);
        Matcher mTomorrow = Pattern.compile(reTomorrow).matcher(weatherResp);

        ArrayList<String> weatherList = new ArrayList<String>();
        String strAQI = "";

        if (mAQI.find()) {
            strAQI = "AQI: " + mAQI.group(1);
        }

        if (mNight.find()) {
            weatherList.add(String.format("%s    %s    %s    %s\n\n", mNight.group(1), mNight.group(2), mNight.group(3), strAQI));
        } else {
            mDay.find();
            weatherList.add(String.format("%s    %s/%s    %s    %s\n\n", mDay.group(1), mDay.group(2), mDay.group(3), mDay.group(4), strAQI));
        }

        if (mTomorrow.find()) {
            weatherList.add(String.format("%s    %s/%s    %s\n", mTomorrow.group(1), mTomorrow.group(2), mTomorrow.group(3), mTomorrow.group(4)));
        }

        return weatherList;
    }

    public ArrayList<String> getTranslation(String transResp) {
        if (transResp == "") {
            return null;
        }

        String reTranslation = "<content><!\\[CDATA\\[(.*?)\\]\\]>";
        Matcher mTrans = Pattern.compile(reTranslation).matcher(transResp);

        ArrayList<String> transList = new ArrayList<>();
        if (mTrans.find()) {
            transList.add(mTrans.group(1));
        }
        return transList;
    }

}
