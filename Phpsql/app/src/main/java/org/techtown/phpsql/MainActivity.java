package org.techtown.phpsql;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.client.HttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.simple.parser.JSONParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button btnLogin;
    Button btnRegister;
    EditText loginId;
    EditText loginPwd;

    public String loginresult;
    private View rView;

    private static String TAG = "phptest";
    private static String url_login = "https://gghs20.cafe24.com/Login20059.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = (Button) findViewById(R.id.button);
        btnRegister = (Button) findViewById(R.id.button2);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),RegistActivity.class);
                startActivity(intent);

//                setContentView(R.layout.register);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                loginId = (EditText) findViewById(R.id.loginid);
                loginPwd = (EditText) findViewById(R.id.loginpwd);

                new LoginTry(MainActivity.this).execute();
            }
        });



    }
    class LoginTry extends AsyncTask<String, String, String> {
        private MainActivity mainActivity;
        /**
         * Before starting background thread Show Progress Dialog
         * */

        LoginTry(MainActivity ma) {
            mainActivity = ma;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            String id = loginId.getText().toString();
            String pwd = loginPwd.getText().toString();

            String postParameters = "userID="+id+"&userPassword="+pwd;

            try {
                URL url = new URL(url_login);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();

                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();

                Log.d(TAG, "POST parameter - " + postParameters);

                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }

                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }

                Log.d(TAG, "POST response data - " + sb.toString());

                bufferedReader.close();
                return sb.toString().trim();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/

        protected void onPostExecute(String result) {

            loginresult = result;
            Log.d(TAG, "JSON - " + loginresult);
            try {
                JSONObject jsonObject = new JSONObject(loginresult);

                String name = jsonObject.getString("userName");

                Log.d(TAG, "JSON name33- " + name);

                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle("결과").setMessage(name + "님, 로그인성공");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            } catch ( JSONException e) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
                builder.setTitle("결과").setMessage("로그인 실패");
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        }
    }

}