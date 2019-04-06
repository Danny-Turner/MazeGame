package com.example.mazegame;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class MazeActivity extends Activity implements SendEndGame{

    private MazeCanvas mazeCanvas;
    private Maze hardMaze;
    private SensorManager sensorManager;
    private BallHandler ballhandler;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //TODO: test bounds
        Point point = setUpBoundries();
        ballhandler = new BallHandler(point.x, point.y);
        MazeReader test = new MazeReader();
        Bundle extras = getIntent().getExtras();
        try {

            test.loadMaze(getAssets().open(extras.getString("Maze")));
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
        mazeCanvas.addAsEndGameListener(this);
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

    @Override
    public void sendTimer(final Timer timer, Context context) {
        Intent forwardIntent = new Intent(MazeActivity.this, MainActivity.class);
        startActivity(forwardIntent);

    }


}
