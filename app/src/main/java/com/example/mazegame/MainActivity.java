package com.example.mazegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button toScores;
    private Button toDifficulty;
    private Button toNewScore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findIDs();
        toScoresButton();
        toDifficultyButton();
        toNewScoreButton();
    }

    public void findIDs(){
        setContentView(R.layout.activity_main);
        toScores = findViewById(R.id.toScores);
        toDifficulty = findViewById(R.id.toDifficulties);
        toNewScore = findViewById(R.id.toNewScore);
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

    private void toNewScoreButton(){
        toNewScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toNewScoreActivity();
            }
        });
    }

    private void toNewScoreActivity(){
        Intent forwardIntent = new Intent(MainActivity.this, ScoreCreator.class);
        startActivity(forwardIntent);
    }
}
