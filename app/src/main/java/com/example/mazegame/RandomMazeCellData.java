package com.example.mazegame;

public class RandomMazeCellData {
    boolean visited;
    int value;


    public RandomMazeCellData() {
        visited = false;
        value =3;
    }

    public int getValue() {return value;}

    public void setValue(int value) {this.value = value;}

    public boolean isVisited() {return visited;}

    public void setVisited(boolean visited) {this.visited = visited;}

}
