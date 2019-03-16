package com.example.mazegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

public class MazeCanvas extends View {

    Paint paint;
    Rect rect;


    public MazeCanvas(Context context) {
        super(context);
        paint = new Paint();
        rect = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(3);

        canvas.drawRect(getWidth()/2,20,10+getWidth()/2,getHeight()/2,paint);
    }

}
