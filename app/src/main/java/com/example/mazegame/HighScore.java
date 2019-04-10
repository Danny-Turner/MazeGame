package com.example.mazegame;
import java.util.Random;

enum Maze_Difficulty {Easy, Medium, Hard, Random}

public class HighScore{
    private long time_in_seconds;
    private String username;


    public HighScore(String name, long time) {
        this.time_in_seconds = time/1000;
        this.username = name;
    }

    public long getTime(){return (time_in_seconds);}

    public String getUsername(){return username;}

    public void createTestScore(){
        Random r = new Random();
        String testname = "test" + r.nextInt(101);
        ScorePage.addNewScore( new HighScore(testname,r.nextInt(101)));
    }

}
