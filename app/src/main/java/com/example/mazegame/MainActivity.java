package com.example.mazegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button toMaze;
    private Button toScores;
    private Button toDifficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIDs();
        toMazeButton();
        toScoresButton();
        toDifficultyButton();
    }

    public void findIDs(){
        setContentView(R.layout.activity_main);
        toMaze = findViewById(R.id.toMaze);
        toScores = findViewById(R.id.toScores);
        toDifficulty = findViewById(R.id.toDifficulties);
    }

    private void toMazeButton() {
        toMaze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMazeActivity();
            }
        });
    }

    private void toMazeActivity(){
        Intent forwardIntent = new Intent(MainActivity.this, MazeActivity.class);
        startActivity(forwardIntent);
    }

    private void toScoresButton() {
        toScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toScoresActivity();
            }
        });
    }

    private void toScoresActivity() {
        Intent forwardIntent = new Intent(MainActivity.this, ScorePage.class);
        startActivity(forwardIntent);
    }

    private void toDifficultyButton() {
        toDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toDifficultyActivity();
            }
        });
    }

    private void toDifficultyActivity() {
        Intent forwardIntent = new Intent(MainActivity.this, Difficulties.class);
        startActivity(forwardIntent);
    }
}
