package org.techtown.shakeshj;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    ImageView imgView;
    TextView view;
    private long lastUpdate;
    private boolean isColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view = findViewById(R.id.textView);
        imgView = findViewById(R.id.imageView);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        lastUpdate = System.currentTimeMillis();
        isColor = true;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            getAccelerometer(event);
        }
    }

    private void getAccelerometer(SensorEvent event) {
        float[] values = event.values;
        float x = values[0];
        float y = values[1];
        float z = values[2];
        double accelationSquareRoot = Math.sqrt((x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH));

        long actualTime = System.currentTimeMillis();
        Toast.makeText(getApplicationContext(), String.valueOf(accelationSquareRoot) + "  " + SensorManager.GRAVITY_EARTH,
                Toast.LENGTH_SHORT).show();
        if (accelationSquareRoot >= 1.4) {
            if (actualTime - lastUpdate < 200) {
                return;
            }
            lastUpdate = actualTime;
            if (isColor) {
                view.setText("Bar Code");
                imgView.setImageResource(R.drawable.barcode);
            } else {
                view.setText("QR Code");
                imgView.setImageResource(R.drawable.qrcode);
            }
        }
        isColor = !isColor;
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(this,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
