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
    private Maze maze;
    private SensorManager sensorManager;
    private BallHandler ballhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Point point = setUpBoundries();
        ballhandler = new BallHandler(point.x, point.y);
        Bundle extras = getIntent().getExtras();
        maze = getMaze(extras.getString("Maze").toString());
        mazeCanvas = new MazeCanvas(this, point.x, point.y, maze, ballhandler);
        mazeCanvas.setBackgroundColor(Color.BLUE);
        setContentView(mazeCanvas);
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mazeCanvas.addAsEndGameListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(ballhandler, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

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
    public void sendTimer(final Timer timer) {
       /* Intent forwardIntent = new Intent(MazeActivity.this, MainActivity.class);
        if (isHighScore(timer.getEllapsedTime() ) {
            forwardIntent = new Intent(MazeActivity.this, ScoreCreator.class);
        }
            startActivity(forwardIntent);

        */

        Intent forwardIntent = new Intent(MazeActivity.this, MainActivity.class);
        startActivity(forwardIntent);

    }

    private Maze getMaze(String selected) {
        if (selected.equals("random")) {
            RandomMaze random = new RandomMaze(10,15);
            random.generateRandomPaths();
            random.createWalls();
            return random;
        } else {
            Maze prebuiltMaze;
            MazeReader test = new MazeReader();

            try {
                test.loadMaze(getAssets().open(selected));
            } catch (IOException e) {
                Log.e("MainActivity", "Could not read MazeData");
            }
            prebuiltMaze = test.getMaze();
            return prebuiltMaze;
        }
    }

}
