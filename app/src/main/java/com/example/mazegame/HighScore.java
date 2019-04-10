package com.example.mazegame;
import java.util.Random;

public class HighScore implements Comparable{
    private long time_in_seconds;
    private String username;

    public HighScore(String name, long time) {
        this.time_in_seconds = time/1000;
        this.username = name;
    }

    public long getTime_in_seconds(){return (time_in_seconds);}

    public String getUsername(){return username;}

    public void createTestScore(){
        Random r = new Random();
        String testname = "test" + r.nextInt(101);
        ScorePage.addNewScore( new HighScore(testname,r.nextInt(101)));
    }


    // This needs to be completed to properly sort the list of scores.
    // Potentially divide by difficulty then sort within each bracket?
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
