package com.example.mazegame;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import java.io.IOException;

public class MazeActivity extends Activity{

    private MazeCanvas mazeCanvas;
    private Maze hardMaze;
    private SensorManager sensorManager;
    private BallHandler ballhandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Point point = setUpBoundries();
        ballhandler = new BallHandler(point.x - 50, point.y - 50);
        MazeReader test = new MazeReader();
        try {
            test.loadMaze(getAssets().open("10x15maze"));
        } catch (IOException e) {
            Log.e("MainActivity", "Could not read MazeData");
        }
        hardMaze = test.getMaze();
        mazeCanvas = new MazeCanvas(this, point.x, point.y, hardMaze, ballhandler);
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
        sensorManager.registerListener(ballhandler, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
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

}
