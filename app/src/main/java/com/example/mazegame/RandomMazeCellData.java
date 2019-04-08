package com.example.mazegame;

public class RandomMazeCellData {
    private boolean visited;
    private int row, column, value;


    public RandomMazeCellData(int row, int column) {
        visited = false;
        value =3;
        this.row=row;
        this.column = column;
    }

    public int getColumn() {return column;}

    public void setColumn(int column) {this.column = column;}

    public int getRow() {return row;}

    public void setRow(int row) { this.row = row; }

    public int getValue() {return value;}

    public void setValue(int value) {this.value = value;}

    public boolean isVisited() {return visited;}

    public void setVisited(boolean visited) {this.visited = visited;}

}
