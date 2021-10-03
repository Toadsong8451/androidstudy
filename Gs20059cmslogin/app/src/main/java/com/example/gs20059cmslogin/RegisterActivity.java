package com.example.gs20059cmslogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText idText = (EditText)findViewById(R.id.idText);
        EditText password = (EditText)findViewById(R.id.passwordText);
        EditText nameText = (EditText)findViewById(R.id.nameText);
        EditText ageText = (EditText)findViewById(R.id.ageText);

        Button RegisterButton = (Button) findViewById(R.id.registerButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(RegisterActivity.this, menuActivity.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });
    }
}