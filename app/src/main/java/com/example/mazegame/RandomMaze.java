package com.example.mazegame;

public class RandomMaze {
    private int width, height;
    private int [][] mazeArray;


    public RandomMaze(int w) {
        width = w;
        height = (int) ((float) w * 1.5);
        mazeArray = new int[width][height];
    }

 
}
