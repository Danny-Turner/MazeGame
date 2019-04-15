package com.example.mazegame;

public class HighScore{
    private long time_in_milliseconds;
    private String username;


    public HighScore(String name, long time) {
        this.time_in_milliseconds = time;
        this.username = name;
    }

    public long getTime(){return (time_in_milliseconds);}

    public String getUsername(){return username;}

}
