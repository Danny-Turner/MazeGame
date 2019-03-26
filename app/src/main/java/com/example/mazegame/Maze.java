package com.example.mazegame;

import java.util.ArrayList;

public class Maze {
    private int width, height;
    private ArrayList<MazeWall> walls;

    public Maze(int width, int height){
        this.width = width;
        this.height = height;
        walls = new ArrayList<>();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ArrayList<MazeWall> getWalls(){
        return walls;
    }

    public void addWall(MazeWall w){
        walls.add(w);
    }


}
