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

    private MazeCanvas mazeCanvas;
    private Maze easyMaze;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maze);
        Log.e("mazeActivity", "about make new canvas");
        easyMaze = new Maze(10,20);
        testMakeMaze(easyMaze);
        mazeCanvas = new MazeCanvas(this,easyMaze);
        mazeCanvas.setBackgroundColor(Color.BLUE);
        setContentView(mazeCanvas);

    }

    private void testMakeMaze(Maze m ){
        m.addWall(new MazeWall(0,0,Orientation.horizontal));
        m.addWall(new MazeWall(0,0,Orientation.vertical));
        m.addWall(new MazeWall(1,0,Orientation.horizontal));

    }


}
