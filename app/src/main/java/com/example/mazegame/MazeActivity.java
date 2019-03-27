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
        for (int i=0; i< m.getHeight(); i++){
            for (int j= 0; j < m.getWidth(); j++) {
                m.addWall(new MazeWall(i, j, Orientation.horizontal));
                m.addWall(new MazeWall(i, j, Orientation.vertical));
            }
        }
    }


}
