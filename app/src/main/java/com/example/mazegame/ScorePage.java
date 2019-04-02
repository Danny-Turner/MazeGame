package com.example.mazegame;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Dictionary;

public class ScorePage extends Activity {
    private Dictionary high_scores;
    private Spinner score_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        findIDs();

    }

    public void findIDs(){
        setContentView(R.layout.activity_scores);
    }

}
