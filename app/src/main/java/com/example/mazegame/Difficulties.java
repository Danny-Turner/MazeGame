package com.example.mazegame;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Difficulties extends Activity {
    private Button Easy, Medium, Hard, Random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_difficulties);
        findIDs();
        toEasyButton();
        toMediumButton();
        toHardButton();
        toRandomButton();
    }

    public void findIDs(){
        setContentView(R.layout.activity_difficulties);
        Easy = findViewById(R.id.toEasy);
        Medium = findViewById(R.id.toMedium);
        Hard = findViewById(R.id.toHard);
        Random = findViewById(R.id.toRandom);
    }

    private void toEasyButton() {
        Easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toEasyActivity();
            }
        });
    }

    private void toEasyActivity() {
        Intent forwardIntent = new Intent(Difficulties.this, MazeActivity.class);
        forwardIntent.putExtra("Maze","6x9maze");
;       startActivity(forwardIntent);
    }

    private void toMediumButton() {
        Medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMediumActivity();
            }
        });
    }

    private void toMediumActivity() {
        Intent forwardIntent = new Intent(Difficulties.this, MazeActivity.class);
        forwardIntent.putExtra("Maze","8x12maze");
        startActivity(forwardIntent);
    }

    private void toHardButton() {
        Hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHardActivity();
            }
        });
    }

    private void toHardActivity() {
        Intent forwardIntent = new Intent(Difficulties.this, MazeActivity.class);
        forwardIntent.putExtra("Maze","10x15maze");
        startActivity(forwardIntent);
    }

    private void toRandomButton() {
        Random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toRandomActivity();
            }
        });
    }

    private void toRandomActivity() {
        Intent forwardIntent = new Intent(Difficulties.this, MazeActivity.class);
        forwardIntent.putExtra("Maze", "random");
        ;
        startActivity(forwardIntent);
    }
}
