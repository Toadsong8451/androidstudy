package org.techtown.samplepaintcsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BestPaintBoard view = new BestPaintBoard(this);

        setContentView(view);
    }
}