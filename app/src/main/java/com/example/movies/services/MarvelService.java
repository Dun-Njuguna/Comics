package com.example.movies.services;

import android.util.Log;

import com.example.movies.Constants;
import com.example.movies.models.Comics;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MarvelService {
    public static final String marvelapikey = Constants.marvelKey;
    public static final String marvelhash = Constants.MarvelHash;
    public static final String ts = Constants.TimeStamp;
    public static final String TAG = MarvelService.class.getSimpleName();

    public static void findcomics(String title, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.MARVEL_BASE_URL).newBuilder();
        urlBuilder
                .addQueryParameter(Constants.MARVEL_COMIC_TITLE_QUERY_PARAMETER , title)
                .addQueryParameter("apikey" , marvelapikey)
                .addQueryParameter("hash" ,marvelhash)
                .addQueryParameter("ts" ,ts);

        String url = urlBuilder.build().toString();

        Request request= new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public ArrayList<Comics> processResults(Response response) {
        ArrayList<Comics> newComics = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject comicsJSON = new JSONObject(jsonData);
            JSONArray resultsJSON = comicsJSON
                    .getJSONObject("data")
                    .getJSONArray("results");

            Log.v(TAG, "Response " + resultsJSON.toString());
            if (response.isSuccessful()) {
                for (int i = 0; i < resultsJSON.length(); i++) {
                    JSONObject resultJSON = resultsJSON.getJSONObject(i);
                    String title = resultJSON.getString("title");
                    String description = resultJSON.getString("description");
                    String issueNumber = resultJSON.getString("issueNumber");
                    String mThumbnailUrl = resultJSON.getString("thumbnail");

                    Comics comics =  new Comics(title,issueNumber,description,mThumbnailUrl);
                    newComics.add(comics);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newComics;
    }

}
