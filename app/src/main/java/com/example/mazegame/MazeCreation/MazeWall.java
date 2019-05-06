package com.example.mazegame.MazeCreation;



public class MazeWall {
    private int row, column;
    private Orientation direction;

    public MazeWall(int row, int column, Orientation direction) {
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

    public boolean isSame(MazeWall wall){
        if (wall.getColumn() == this.column && wall.getRow() == this.row
                && wall.getDirection().equals(this.direction)){
            return true;
        }
        return false;
    }


}



