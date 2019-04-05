package com.example.mazegame;
import java.util.Random;
public class HighScore {
    private long time_in_seconds;
    private String username;


    public HighScore(String name, long time){
        this.time_in_seconds = time/1000;
        this.username = name;
    }

    public long getTime_in_seconds(){return (time_in_seconds);}

    public String getUsername(){return username;}

    public HighScore createTestScore(){
        Random r = new Random();
        String testname = "test" + r.nextInt(101);
        return new HighScore(testname,r.nextInt(101));
    }

    public HighScore createNewScore(String scorename, long millitime){
        return new HighScore(scorename, millitime/1000);
    }




}
