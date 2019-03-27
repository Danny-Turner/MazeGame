package com.example.mazegame;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MazeActivity extends Activity{

    private MazeCanvas mazeCanvas;
    private Maze easyMaze;
    private SensorManager sensorManager;
    private BallHandler ballhandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //setContentView(R.layout.activity_maze);
        Point point = setUpBoundries();
        ballhandler = new BallHandler(point.x - 50, point.y - 50);


        Log.e("mazeActivity", "about make new canvas");
        easyMaze = new Maze(10,20);
        testMakeMaze(easyMaze);
        mazeCanvas = new MazeCanvas(this, easyMaze, ballhandler);
        mazeCanvas.setBackgroundColor(Color.BLUE);
        setContentView(mazeCanvas);



        //need to create a canvas, the object should be the canvas
        //try to create the maze in the canvas at first
        //or may need to create it in the xml

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);




    }



    @Override
    protected void onStart() {
        super.onStart();
        //registers the listener on start
        sensorManager.registerListener(ballhandler, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    //removes the listener on close
    @Override
    protected void onStop() {
        sensorManager.unregisterListener(ballhandler);
        super.onStop();
    }


    private Point setUpBoundries(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        return point;
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
