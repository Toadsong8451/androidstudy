 package org.techtown.http;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

 public class MainActivity extends AppCompatActivity {
    Handler handler = new Handler();
    private RecyclerView recycleView;
    private MyRecyclerViewAdapter adapter;
    ArrayList<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = (RecyclerView) findViewById(R.id.recycler_view);
        movieList = new ArrayList<Movie>();

        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();

        recycleView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));

    }


     public class MyAsyncTask extends AsyncTask<String, Void, Movie[]> {
         //로딩중 표시
         ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

         @Override
         protected void onPreExecute() {
             super.onPreExecute();
             progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
             progressDialog.setMessage("\t로딩중...");
             //show dialog
             progressDialog.show();
         }

         @Override
         protected Movie[] doInBackground(String... strings) {
             OkHttpClient client = new OkHttpClient();
             Request request = new Request.Builder()
                     .url("https://api.themoviedb.org/3/movie/upcoming?api_key=756ebe5fb864396cc03907a8de7f4af2&language=ko-KR&page=1")
                     .build();
             try {
                 Response response = client.newCall(request).execute();
                 Gson gson = new GsonBuilder().create();
                 JsonParser parser = new JsonParser();
                 JsonElement rootObject = parser.parse(response.body().charStream())
                         .getAsJsonObject().get("results");
                 Movie[] posts = gson.fromJson(rootObject, Movie[].class);
                 return posts;
             } catch (Exception e) {
                 e.printStackTrace();
             }
             return null;
         }

         @Override
         protected void onPostExecute(Movie[] result) {
             super.onPostExecute(result);
             progressDialog.dismiss();
             if(result.length > 0){
                 for(Movie p : result){
                     movieList.add(p);
                 }
             }

             //어답터 설정
             adapter = new MyRecyclerViewAdapter(MainActivity.this, movieList);
             recycleView.setAdapter(adapter);
         }
     }
}


