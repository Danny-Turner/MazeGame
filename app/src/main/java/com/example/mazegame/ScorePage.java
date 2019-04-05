package com.example.mazegame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class ScorePage extends Activity {
    private ArrayList<HighScore> HighScores;
    private Button addScoreTestButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        findIDs();
        addScoreTestButton();
    }

    private void findIDs(){
        setContentView(R.layout.activity_scores);
        addScoreTestButton = findViewById(R.id.addScoreTestButton);
    }


    private void addScoreTestButton(){
        addScoreTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addScoreTest();
            }
        });
    }

    private void addScoreTest(){}

    private void addNewScore(String name, Long millitime){
        HighScores.add(new HighScore(name, millitime));
    }

}
