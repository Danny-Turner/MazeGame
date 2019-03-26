package com.example.mazegame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button toMaze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findIDs();
        toMazeButton();

    }



    public void findIDs(){
        setContentView(R.layout.activity_main);
        toMaze = findViewById(R.id.toMaze);
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
}
