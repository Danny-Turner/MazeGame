package com.example.mazegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.function.ToLongFunction;

public class ScorePage extends Activity {
    public TextView Score_Display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        findIDs();
        Score_Display = new TextView(this);
        try {
            HighScoreTable.get().sortScores();
            displayScores();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void findIDs(){
        setContentView(R.layout.high_score_page_activity);
        Score_Display = findViewById(R.id.name_input_box);
    }

    public void displayScores() throws IOException {
        LinearLayout LinearLayoutView = new LinearLayout(this);
        Score_Display.setTextSize(25);
        LinearLayoutView.addView(Score_Display);
        for (int i=0; i< Math.min(9,HighScoreTable.get().getTopScores().size());i++){
            Score_Display.append(displayTime(HighScoreTable.get().getTopScores().get(i).getTime()));
            Score_Display.append("   -    ");
            Score_Display.append(HighScoreTable.get().getTopScores().get(i).getUsername());
            Score_Display.append("\n");
        }
        setContentView(LinearLayoutView);
    }

    public String displayTime(Long time){
        int m = (int) (time /60000);
        int s= (int)(time - m*60000)/1000 ;
        return (m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
    }


}
