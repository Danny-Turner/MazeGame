package com.example.mazegame;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MazeActivity extends AppCompatActivity {

    MazeCanvas mazeCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maze);
        Log.e("mazeActivity", "about make new canvas");
        mazeCanvas = new MazeCanvas(this,10,20);
        mazeCanvas.setBackgroundColor(Color.BLUE);
        setContentView(mazeCanvas);

    }


}
