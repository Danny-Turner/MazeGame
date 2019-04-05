package com.example.mazegame;

import android.os.SystemClock;

public class Timer {
    private long startTime, endTime;
    boolean started;

    public Timer(){
        startTime = 0;
        endTime = 0;
        started = false;
    }

    public void start(){
        startTime = SystemClock.elapsedRealtime();
        started = true;
    }

    public void stop(){
        endTime = getEllapsedTime();
        started = false;
    }

    public long getEllapsedTime() {
        if (started) {
            return SystemClock.elapsedRealtime() - startTime;
        }
        return endTime - startTime;
    }

    public String displayTime(){
        int m = (int) (getEllapsedTime() /60000);
        int s= (int)(getEllapsedTime() - m*60000)/1000 ;
        return (m < 10 ? "0"+m: m)+":"+ (s < 10 ? "0"+s: s);
    }
}


