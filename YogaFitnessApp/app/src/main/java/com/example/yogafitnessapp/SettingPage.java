package com.example.yogafitnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import java.util.Calendar;

import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.yogafitnessapp.Database.YogaDB;
import com.example.yogafitnessapp.Utils.AlarmNotificationReceiver;

import java.util.Date;

public class SettingPage extends AppCompatActivity {

    Button btnSave;
    RadioButton rdiEasy,rdiMedium,rdiHard;

    RadioGroup rdiGroup;
    YogaDB yogaDB;

    ToggleButton switchAlarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_page);

        btnSave = (Button)findViewById(R.id.btnSave);
        rdiGroup = (RadioGroup)findViewById(R.id.rdiGroup);
        rdiEasy = (RadioButton)findViewById(R.id.rdiEasy);
        rdiMedium = (RadioButton)findViewById(R.id.rdiMedium);
        rdiHard = (RadioButton)findViewById(R.id.rdiHard);

        switchAlarm = (ToggleButton)findViewById(R.id.switchAlarm);

        timePicker = (TimePicker)findViewById(R.id.timePicker);

        yogaDB = new YogaDB(this);

        int mode = yogaDB.getSettingMode();
        setRadioButton(mode);

        //event
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveWorkoutMode();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(SettingPage.this, "SAVED!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void saveAlarm(boolean checked) {
        Intent intent;
        PendingIntent pendingIntent ;

        intent = new Intent(SettingPage.this, AlarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

        AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int Hour =  timePicker.getCurrentHour();
        int Minute = timePicker.getCurrentMinute();

        if (Build.VERSION.SDK_INT >= 23 ) {



        }  else{

            Minute = timePicker.getCurrentMinute();
            Hour = timePicker.getCurrentHour();
        }


        Date dat = new Date();
        Calendar cal_alarm = Calendar.getInstance();
        Calendar cal_now = Calendar.getInstance();
        cal_now.setTime(dat);
        cal_alarm.setTime(dat);
        cal_alarm.set(Calendar.HOUR_OF_DAY,Hour);
        cal_alarm.set(Calendar.MINUTE,Minute);
        cal_alarm.set(Calendar.SECOND,10);
        if(cal_alarm.before(cal_now)){
            cal_alarm.add(Calendar.DATE,1);
        }



        manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);

    }

    private void saveWorkoutMode() {
        int selectedID = rdiGroup.getCheckedRadioButtonId();

        if (selectedID == rdiEasy.getId())  {
            yogaDB.saveSettingMode(0);
        }
        else if (selectedID == rdiMedium.getId())  {
            yogaDB.saveSettingMode(1);
        }
        else if (selectedID == rdiHard.getId())  {
            yogaDB.saveSettingMode(2);
        }
    }

    private void setRadioButton(int mode) {
        if (mode == 0)  {

            rdiGroup.check(R.id.rdiEasy);
        }
        else if (mode == 1)  {

            rdiGroup.check(R.id.rdiMedium);
        }
        else if (mode == 2)  {

            rdiGroup.check(R.id.rdiHard);
        }
    }
}
