package com.example.mazegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class MazeCanvas extends View {
    int canvasWidth, canvasHeight, width, height;
    int verticalWallLength, verticalWallThickness;
    int horizontalWallLength, horizontalWallThickness;
    Paint verticalLinePaint, horizontalLinePaint;
    Rect verticalWall, horizontalWall;
    ArrayList<Rect> verticalWalls, horizontalWalls;

    public MazeCanvas(Context context, int w, int h) {
        super(context);
        width = w;
        height = h;
        verticalWallThickness = 3;
        horizontalWallThickness = 3;
        verticalLinePaint = new Paint();
        horizontalLinePaint = new Paint();
        verticalWall = new Rect();
        horizontalWall = new Rect();
        verticalWalls = new ArrayList<Rect>();
        horizontalWalls = new ArrayList<Rect>();
    }

    public void addVertWall(Rect r) {
        verticalWalls.add(r);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvasWidth = getWidth();
        canvasHeight = getHeight();
        horizontalWallLength = (int) canvasWidth/width;
        verticalWallLength = (int) canvasHeight/height;
        addVertWall(verticalWall);
        addVertWall(horizontalWall);
        verticalLinePaint.setColor(Color.WHITE);
        verticalLinePaint.setStrokeWidth(3);
        horizontalLinePaint.setColor(Color.WHITE);
        horizontalLinePaint.setStrokeWidth(3);
        Log.e("mazeCanvas","canvaswidht: "+canvasWidth+" canvasheight: "+canvasHeight);
        Log.e("MazeCanvas", "onDraw: about to loop; h: "+horizontalWallLength+" v: "+verticalWallLength);
        for (int i = 0; i < verticalWalls.size(); i++) {
            Log.e("MazeCanvas", "looping "+i);
            canvas.drawRect(i*horizontalWallLength,0,i*horizontalWallLength+4,
                    (1)*verticalWallLength, verticalLinePaint);
        }

    }

}
