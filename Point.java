// COMP 282
// CSUN - Fall 2019
// Nicolae Bucsa
// Project 1 - Convex Hull

public class Point implements Comparable<Point> {
    private int x, y;
    
    // constructor
    public Point (int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    // getters
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    // we don't need setters
    /*public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }*/

    @Override
    public String toString() {
    	return this.getX() + "  " + this.getY();
    }
    
    // to use the sort function in collections, in relation to X coordinates
    @Override
    public int compareTo(Point compare) {
        int compareX = ((Point)compare).getX();
        return this.x - compareX; // for ascending order
    }
}