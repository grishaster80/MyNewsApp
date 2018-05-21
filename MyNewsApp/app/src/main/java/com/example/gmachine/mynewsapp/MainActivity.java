package com.example.gmachine.mynewsapp;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.gmachine.mynewsapp.Adapter.MyCustomAdapter;
import com.example.gmachine.mynewsapp.MessagePack.Message;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private List<Message> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = new ArrayList<>();
        DownloadTask task = new DownloadTask();
        String result = null;
        try {

            result = task.execute("https://newsapi.org/v2/top-headlines?country=us&apiKey=ce2cd1d879254e76a92848e15456e6dc").get();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        AtomicReference<MyCustomAdapter> adapter = new AtomicReference<>();
        adapter.set(new MyCustomAdapter(initData(result)));
        Log.i("result","Heh "+result);
        recyclerView.setAdapter(adapter.get());
    }

    private List<Message> initData(String myDataString) {
        representData(myDataString);
        return list;
    }
    private void representData(String myJSONDataString){
        JsonParser parser = new JsonParser();
        JsonObject rootObj = parser.parse(myJSONDataString).getAsJsonObject();
        JsonArray articlesArray = rootObj.getAsJsonArray("articles");
        for (JsonElement aa : articlesArray) {
            JsonObject articleObj = aa.getAsJsonObject();
            String title = articleObj.get("title").getAsString();
            String description = articleObj.get("description").getAsString();
            list.add(new Message(title,description));
        }
    }
    public class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {

                url = new URL(urls[0]);

                urlConnection = (HttpURLConnection)url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int data = reader.read();

                while (data != -1) {

                    char current = (char) data;

                    result += current;

                    data = reader.read();

                }

                return result;

            }
            catch(Exception e) {

                e.printStackTrace();

                return "Failed";

            }


        }

    }
}
