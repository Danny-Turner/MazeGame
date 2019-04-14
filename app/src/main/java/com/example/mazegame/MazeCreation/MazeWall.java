package com.example.mazegame.MazeCreation;



public class MazeWall {
    private int row, column;
    private Orientation direction;

    MazeWall(int row, int column, Orientation direction) {
       this.row = row;
       this.column = column;
       this.direction = direction;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public Orientation getDirection(){
        return direction;
    }
}



