package com.example.mazegame;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ScorePage extends Activity {
    private static ArrayList<HighScore> topScoresList;
    private boolean isHighScore;
    public TextView Score_Display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findIDs();
        topScoresList = MainActivity.topScoresList;
        isHighScore = false;
        Score_Display = new TextView(this);
        displayScores();

    }

    private void findIDs(){
        setContentView(R.layout.high_score_page_activity);
        Score_Display = findViewById(R.id.name_input_box);
    }


    private void addScoreTest(){
        addNewScore("test", (long) 1000000);
    }

    public void addNewScore(String name, Long millitime){
        topScoresList.add(new HighScore(name, millitime));
    }

    public static void addNewScore(HighScore hs){
        topScoresList.add(hs);}

    private void sortScores(){
        //topScoresList.sort();
    }

    // compares newTime against the top 10 scores in topScoresList
    // if newTime is a faster time than ANY of the top 10,
    // then isHighScore = true
    public boolean isHighScore(long newTime) {
        for (int i=0; i<10; i++) {
            if (newTime < topScoresList.get(i).getTime()) {
                isHighScore = true;
            }
        }
        return isHighScore;
    }

    public void displayScores(){
        LinearLayout LinearLayoutView = new LinearLayout(this);
        Score_Display.setTextSize(25);
        LinearLayoutView.addView(Score_Display);
        Log.e("scores",Integer.toString(topScoresList.size()));
        for (int i=0; i< Math.min(9,topScoresList.size());i++){
            // print out all variable assignments to hunt the error
            Score_Display.append((CharSequence) topScoresList.get(i).getUsername());
            Score_Display.append("\n");
        }
        setContentView(LinearLayoutView);
    }

}
