// COMP 282
// CSUN - Fall 2019
// Nicolae Bucsa
// Project 1 - Convex Hull

import java.util.*;
import java.io.*;

public class ConvexHull {
	ArrayList<Point> points = new ArrayList<>();
	ArrayList<Point> upperPoints = new ArrayList<>();
	ArrayList<Point> lowerPoints = new ArrayList<>();
	
	// method read() to read coordinates from file "input.txt" using BufferedReader
	public void read() throws IOException {
		FileReader fileRead;
		Point point = null;
		fileRead = new FileReader("input.txt");
		BufferedReader reader = new BufferedReader(fileRead);
        String line;
        String [] string;
        int coordinateX, coordinateY;
        while ((line = reader.readLine()) != null) { // reading line as string
        	string = line.split(" "); // split string by character space (" ")
        	coordinateX = Integer.parseInt(string[0]); // convert to integer
            coordinateY = Integer.parseInt(string[1]); // convert to integer
            point = new Point(coordinateX, coordinateY); // create point
			points.add(point); // add point to array list of points
			// need 2 extra array lists to manipulate without losing originals
            upperPoints.add(point);
            lowerPoints.add(point);
        }
        //System.out.println("Points in the file: " + points); //testing
        Collections.sort(upperPoints); // sort ascending by x coordinates
        //System.out.println("Sorted points in upper file: " + upperPoints);
        Collections.sort(lowerPoints); // sort ascending by x coordinate
	}
	
	// method to check if point (x2,y2) is above line [(x1,y1), (x3,y3)]
	public boolean above(int x1, int x2, int x3, int y1, int y2, int y3) {
		int a, b, c;
		a = ((y3-y1)/(x3-x1)) * x2;
		b = ((y3-y1)/(x3-x1)) * x1;
		c = a + y1 - b;
		return y2 > c;
	}
	
	// method to check if point (x2,y2) is below line [(x1,y1), (x3,y3)]
	public boolean below(int x1, int x2, int x3, int y1, int y2, int y3) {
		int a, b, c;
		a = ((y3-y1)/(x3-x1)) * x2;
		b = ((y3-y1)/(x3-x1)) * x1;
		c = a + y1 - b;
		return y2 < c;
	}
	
	// creating upper envelope (upper boundary of convex hull)
	public void upperEnvelope() {
		int x1, x2, x3, y1, y2, y3;
		for (int i = 1; i < upperPoints.size() - 1; i++) {
			x1 = upperPoints.get(i-1).getX();
			y1 = upperPoints.get(i-1).getY();
			x2 = upperPoints.get(i).getX();
			y2 = upperPoints.get(i).getY();
			x3 = upperPoints.get(i+1).getX();
			y3 = upperPoints.get(i+1).getY();
			// the following prevents division by zero in "below" method
			if (x1 == x3) { // if 3 point have the same x value
				i++; // just increment "i" once, then in for loop once more
			}
			else if (below(x1, x2, x3, y1, y2, y3)) {
				upperPoints.remove(i); // this means point at index "i" is
				i = 0;				   // lower than the upper boundary so we
			}						   // remove it from upper boundary list
		}
	}
	
	// creating lower envelope (lower boundary of convex hull)
	public void lowerEnvelope() {
		int x1, x2, x3, y1, y2, y3;
		for (int i = 1; i < lowerPoints.size() - 1; i++) {
			x1 = lowerPoints.get(i-1).getX();
			y1 = lowerPoints.get(i-1).getY();
			x2 = lowerPoints.get(i).getX();
			y2 = lowerPoints.get(i).getY();
			x3 = lowerPoints.get(i+1).getX();
			y3 = lowerPoints.get(i+1).getY();
			if (x1 == x3) {
				i++;
			}
			else if (above(x1, x2, x3, y1, y2, y3)) {
				lowerPoints.remove(i);
				i = 0;
			}
		}
	}
	
	// method to test if a point is inside the boundary
	// created by the upper and lower envelopes
	public boolean outside(int x, int y) {
		int top = upperPoints.size() - 1;
		int x1, x2, y1, y2;
		// if point is outside min and max on the x axis => point is outside
		if (x < upperPoints.get(0).getX() || x > upperPoints.get(top).getX())
			return true;
		// if point is above upper boundary => point is outside
		for (int i = 1; i <= top; i++) {
			x1 = upperPoints.get(i - 1).getX();
			y1 = upperPoints.get(i - 1).getY();
			x2 = upperPoints.get(i).getX();
			y2 = upperPoints.get(i).getY();
			if (above(x1, x, x2, y1, y, y2))
				return true;
		}
		// if point is below lower boundary => point is outside
		for (int i = 1; i <= lowerPoints.size() - 1; i++) {
			x1 = lowerPoints.get(i - 1).getX();
			y1 = lowerPoints.get(i - 1).getY();
			x2 = lowerPoints.get(i).getX();
			y2 = lowerPoints.get(i).getY();
			if (below(x1, x, x2, y1, y, y2))
				return true;
		}
		// otherwise point is not outside => point is inside
		// considering points right on the fence as inside
		return false;
	}
}
