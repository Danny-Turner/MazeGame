package com.example.mazegame;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ScorePage extends Activity {
    private static ArrayList<HighScore> HighScores;
    private Button addScoreTestButton;
    private TextView Score_Display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score_page_activity);
        findIDs();
        HighScores = new ArrayList<>();
        addScoreTestButton();
    }

    private void findIDs(){
        setContentView(R.layout.high_score_page_activity);
        addScoreTestButton = findViewById(R.id.addScoreTestButton);
        Score_Display = findViewById(R.id.name_input_box);
    }


    private void addScoreTestButton(){
        addScoreTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addScoreTest();
            }
        });
    }

    private void addScoreTest(){
        addNewScore("test", (long) 1000000);
    }

    public void addNewScore(String name, Long millitime){
        HighScores.add(new HighScore(name, millitime));
    }

    public static void addNewScore(HighScore hs){HighScores.add(hs);}

    private void sortScores(){
        //HighScores.sort();
    }
}
