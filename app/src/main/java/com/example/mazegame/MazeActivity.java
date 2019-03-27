package com.example.mazegame;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class MazeActivity extends Activity implements SensorEventListener {

    private MazeCanvas mazeCanvas;
    private Maze easyMaze;
    private float xvel, yvel = 0.0f;
    private float xpos, ypos = 0.0f;
    private float xMax,yMax ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_maze);


        Log.e("mazeActivity", "about make new canvas");
        easyMaze = new Maze(10,20);
        testMakeMaze(easyMaze);
        mazeCanvas = new MazeCanvas(this, easyMaze);
        mazeCanvas.setBackgroundColor(Color.BLUE);
        setContentView(mazeCanvas);

        setUpBoundries();

        //need to create a canvas, the object should be the canvas
        //try to create the maze in the canvas at first
        //or may need to create it in the xml






    }

    private void setUpBoundries(){
        Display display = getWindowManager().getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        xMax = point.x - 100;
        yMax = point.y - 200;
    }

    private void testMakeMaze(Maze m ){
        for (int i=0; i< m.getHeight(); i++){
            for (int j= 0; j < m.getWidth(); j++) {
                m.addWall(new MazeWall(i, j, Orientation.horizontal));
                m.addWall(new MazeWall(i, j, Orientation.vertical));
            }
        }
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float xaccel = event.values[0];
            float yaccel = -event.values[1];
            updatedBall(xaccel, yaccel);

        }
    }

    private void updatedBall(float xaccel, float yaccel) {
        float frameLength = 0.666f;
        xvel += (xaccel * frameLength)/2;
        yvel += (yaccel * frameLength)/2;

        float xdisplace = xvel * frameLength;
        float ydisplace = yvel * frameLength;

        xpos -= xdisplace;
        ypos -= ydisplace;

        if (xpos > xMax) {
            xpos = xMax;
        } else if (xpos < 0) {
            xpos = 0;
        }

        if (ypos > yMax) {
            ypos = yMax;
        } else if (ypos < 0) {
            ypos = 0;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
