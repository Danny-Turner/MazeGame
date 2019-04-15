package com.example.mazegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {
    public static ArrayList<HighScore> topScoresList;
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
        topScoresList = new ArrayList<>();
        try {
            loadHighScores(getAssets().open("highscoresfile"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public static void loadHighScores(InputStream scoreInput) throws IOException {
        Scanner input = new Scanner(scoreInput);
        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] scoreData = line.split(",");
            Log.e("inithighscores",scoreData[0]+" "+scoreData[1]);
            topScoresList.add(new HighScore(scoreData[0],Long.parseLong(scoreData[1])));
        }
        input.close();
    }


}
