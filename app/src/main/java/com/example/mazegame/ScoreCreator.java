package com.example.mazegame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;

public class ScoreCreator extends Activity {
    long playerScore;
    private Button submit_button;
    private EditText name_input_box;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_score_activity);
        Intent i = getIntent();
        playerScore = i.getLongExtra("Score",0L);
        findIDs();
        submit_score_button();
    }


    public void findIDs(){
        setContentView(R.layout.new_score_activity);
        submit_button = findViewById(R.id.submit_button);
        name_input_box = findViewById(R.id.name_input_box);
    }


    private void submit_score_button() {
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HighScoreTable highScore = HighScoreTable.get();
                    highScore.addNewScore(new HighScore(name_input_box.getText().toString(),playerScore));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                toMainActivity();
            }
        });
    }


    private void toMainActivity() {
        Intent forwardIntent = new Intent(ScoreCreator.this, MainActivity.class);
        startActivity(forwardIntent);
    }


}
