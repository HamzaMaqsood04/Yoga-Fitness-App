package com.example.yogafitnessapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.yogafitnessapp.Aadpter.RecyclerViewAdapter;
import com.example.yogafitnessapp.Model.Exercise;

import java.util.ArrayList;
import java.util.List;

public class ListExercises extends AppCompatActivity {

    List<Exercise> exerciseList = new ArrayList<>();
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exercises);

        initData();

        recyclerView = (RecyclerView) findViewById(R.id.list_ex);
        adapter = new RecyclerViewAdapter(exerciseList, getBaseContext());
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void initData() {

        exerciseList.add(new Exercise(R.drawable.easy_pose, "Easy pose"));
        exerciseList.add(new Exercise(R.drawable.cobra_pose, "Cobra pose"));
        exerciseList.add(new Exercise(R.drawable.downward_facing_dog, "Downward facing dog"));
        exerciseList.add(new Exercise(R.drawable.boat_pose, "Boat pose"));
        exerciseList.add(new Exercise(R.drawable.half_pigeon, "Half pigeon"));
        exerciseList.add(new Exercise(R.drawable.low_lunge, "Low lunge"));
        exerciseList.add(new Exercise(R.drawable.upward_bow, "Upward bow"));
        exerciseList.add(new Exercise(R.drawable.crescent_lunge, "Crescent lunge"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose, "Warrior pose"));
        exerciseList.add(new Exercise(R.drawable.bow_pose, "Bow pose"));
        exerciseList.add(new Exercise(R.drawable.warrior_pose_2, "Warrior pose 2"));
    }
}
