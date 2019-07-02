package com.example.yogafitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yogafitnessapp.Database.YogaDB;
import com.example.yogafitnessapp.Utils.Common;

public class ViewExcersie extends AppCompatActivity {

    int image_id;
    String name;

    TextView timer,title;

    ImageView detail_image;

    Button btnStart;

    boolean isRunning = false;

    YogaDB yogaDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_excersie);

        yogaDB = new YogaDB(this);

        timer = (TextView)findViewById(R.id.timer);
        title = (TextView)findViewById(R.id.title);
        detail_image = (ImageView)findViewById(R.id.detail_image);

        btnStart = (Button)findViewById(R.id.btnStart);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!isRunning) {

                    btnStart.setText("DONE");

                    int timeLimit = 0;

                    if (yogaDB.getSettingMode() == 0)   {
                        timeLimit = Common.TIME_LIMIT_EASY;
                    }

                    else if (yogaDB.getSettingMode() == 1)   {
                        timeLimit = Common.TIME_LIMIT_MEDIUM;
                    }

                    else if (yogaDB.getSettingMode() == 2)   {
                        timeLimit = Common.TIME_LIMIT_HARD;
                    }

                    new CountDownTimer(timeLimit, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {
                            timer.setText(""+millisUntilFinished/1000);
                        }

                        @Override
                        public void onFinish() {
                            //put ad here
                            Toast.makeText(ViewExcersie.this, "Finished!!!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }.start();
                }
                else    {
                    Toast.makeText(ViewExcersie.this, "Finished!!!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                isRunning = !isRunning;
            }
        });

        timer.setText("");

        if (getIntent() != null)    {

            image_id = getIntent().getIntExtra("image_id",-1);
            name = getIntent().getStringExtra("name");

            detail_image.setImageResource(image_id);
            title.setText(name);
        }
    }
}
