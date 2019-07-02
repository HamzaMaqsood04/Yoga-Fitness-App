package com.example.yogafitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button btnExercises,btnSetting,btnCalendar;
    ImageView btnTraining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExercises = (Button)findViewById(R.id.btnExercises);
        btnSetting = (Button)findViewById(R.id.btnSetting);
        btnCalendar = (Button)findViewById(R.id.btnCalendar);
        btnTraining = (ImageView)findViewById(R.id.btnTraining);

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Calendar.class);
                startActivity(intent);
            }
        });

        btnTraining.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DailyTraining.class);
                startActivity(intent);
            }
        });


        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SettingPage.class);
                startActivity(intent);
            }
        });

        btnExercises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListExercises.class);
                startActivity(intent);
            }
        });
    }
}
