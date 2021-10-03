package org.techtown.phpsql;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegistActivity extends AppCompatActivity {
    Button btnRegister2;
    EditText inputid;
    EditText inputpwd;
    EditText inputname;
    EditText inputage;
    int result;

    private static String TAG = "phptest";
    private static String url_register = "https://gghs20.cafe24.com/Register20059.php";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        result = 0;
        btnRegister2 = (Button) findViewById(R.id.button3);

        btnRegister2.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                inputid = (EditText) findViewById(R.id.registid);
                inputpwd = (EditText) findViewById(R.id.registpwd);
                inputname = (EditText) findViewById(R.id.registname);
                inputage = (EditText) findViewById(R.id.registage);

                new RegisterNew(RegistActivity.this).execute();
            }
        });

    }

    class RegisterNew extends AsyncTask<String, String, String> {
        private RegistActivity mainActivity;


        RegisterNew(RegistActivity ma) {
            mainActivity = ma;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        protected String doInBackground(String... args) {
            String id = inputid.getText().toString();
            String pwd = inputpwd.getText().toString();
            String name = inputname.getText().toString();
            String age = inputage.getText().toString();

            String postParameters = "userID=\""+id+"\"&userPassword=\""+pwd+"\"&userName=\""+name+"\"&userAge="+age;

            try {
                URL url = new URL(url_register);
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

                if( sb.toString().equals("true")) result = 1;
                else result = 2;

//                mainActivity.result = sb.toString();
                bufferedReader.close();

            } catch (Exception ex) {
                ex.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(String file_url) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            if( result == 1) {
                builder.setTitle("결과").setMessage("가입성공");
                builder.setPositiveButton("로그인하러가기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        mainActivity.finish();
                    }
                });
            } else {
                builder.setTitle("결과").setMessage("가입실패-ID중복또는 필드 누락");
                builder.setPositiveButton("다시시도", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });

            }

            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        }
    }
}
