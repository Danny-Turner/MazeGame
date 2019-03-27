package com.example.mazegame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.ContextMenu;
import android.view.View;

public class MazeCanvas extends View {

    int canvasWidth, canvasHeight, width, height;
    int verticalWallLength, horizontalWallLength, wallThickness;
    Paint wallPaint;
    Maze maze;


    public MazeCanvas(Context context, Maze maze) {
        super(context);
        width = maze.getWidth();
        height = maze.getHeight();
        wallThickness = 3;  //3 is testing value. Will need to be calculated later
        wallPaint = new Paint();
        this.maze = maze;
    }

    private class BallView extends View {

        public BallView(Context context) {
            super(context);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            invalidate();
        }
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setUpCanvasDimensions();
        horizontalWallLength = (int) canvasWidth/width;
        verticalWallLength = (int) canvasHeight/height;
        wallPaint.setColor(Color.WHITE);
        wallPaint.setStrokeWidth(wallThickness);
        Log.e("mazeCanvas","canvaswidht: "+canvasWidth+" canvasheight: "+canvasHeight);
        Log.e("MazeCanvas", "onDraw: about to loop; h: "+horizontalWallLength+" v: "+verticalWallLength);
        for (int i = 0; i < maze.getWalls().size(); i++) {
            Log.e("MazeCanvas", "looping "+i);
            if (maze.getWalls().get(i).getDirection() == Orientation.horizontal) {
                drawHorizontalWall(maze.getWalls().get(i),canvas);
                Log.e("MazeCanvas", "draw horizontal");
            } else if(maze.getWalls().get(i).getDirection() == Orientation.vertical) {
                drawVerticalWall(maze.getWalls().get(i),canvas);
                Log.e("MazeCanvas", "draw vertical");
            }
        }
        invalidate();
    }

    private void setUpCanvasDimensions(){
        canvasWidth = getWidth();
        canvasHeight = getHeight();
    }

    private void drawHorizontalWall(MazeWall w, Canvas c){
        c.drawRect(w.getColumn()*horizontalWallLength,w.getRow()*verticalWallLength,
                (w.getColumn()+1)*horizontalWallLength, w.getRow()*verticalWallLength+wallThickness,wallPaint);
    }

    private void drawVerticalWall(MazeWall w, Canvas c){
        c.drawRect(w.getColumn()*horizontalWallLength,w.getRow()*verticalWallLength,
                w.getColumn()*horizontalWallLength+wallThickness, (w.getRow()+1)*verticalWallLength,wallPaint);
    }
}
