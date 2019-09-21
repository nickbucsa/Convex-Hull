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
	
	public void read() throws IOException {
		FileReader fileRead;
		Point point = null;
		fileRead = new FileReader("input.txt");
		BufferedReader reader = new BufferedReader(fileRead);
        String line;
        String [] string;
        int coordinateX, coordinateY;
        while ((line = reader.readLine()) != null) {
        	string = line.split(" ");
        	coordinateX = Integer.parseInt(string[0]);
            coordinateY = Integer.parseInt(string[1]);
            point = new Point(coordinateX, coordinateY);
            points.add(point);
            upperPoints.add(point);
            lowerPoints.add(point);
        }
        System.out.println("Points in the file: " + points);
        Collections.sort(upperPoints);
        System.out.println("Sorted points in the upper file: " + upperPoints);
        Collections.sort(lowerPoints);
	}
	
	public boolean above(int x1, int x2, int x3, int y1, int y2, int y3) {
		int a, b, c;
		a = ((y3-y1)/(x3-x1)) * x2;
		b = ((y3-y1)/(x3-x1)) * x1;
		c = a + y1 - b;
		return y2 > c;
	}
	
	public boolean below(int x1, int x2, int x3, int y1, int y2, int y3) {
		int a, b, c;
		a = ((y3-y1)/(x3-x1)) * x2;
		b = ((y3-y1)/(x3-x1)) * x1;
		c = a + y1 - b;
		return y2 < c;
	}
	
	public void upperEnvelope() {
		int x1, x2, x3, y1, y2, y3;
		for (int i = 1; i < upperPoints.size() - 1; i++) {
			x1 = upperPoints.get(i-1).getX();
			y1 = upperPoints.get(i-1).getY();
			x2 = upperPoints.get(i).getX();
			y2 = upperPoints.get(i).getY();
			x3 = upperPoints.get(i+1).getX();
			y3 = upperPoints.get(i+1).getY();
			if (x1 == x3) {
				i++;
			}
			else if (below(x1, x2, x3, y1, y2, y3)) {
				upperPoints.remove(i);
				i = 0;
			}
		}
	}
	
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
	
	public boolean outside(int x, int y) {
		int top = upperPoints.size() - 1;
		int x1, x2, y1, y2;
		if (x < upperPoints.get(0).getX() || x > upperPoints.get(top).getX())
			return true;
		for (int i = 1; i < top; i++) {
			x1 = upperPoints.get(i - 1).getX();
			y1 = upperPoints.get(i - 1).getY();
			x2 = upperPoints.get(i).getX();
			y2 = upperPoints.get(i).getY();
			if (above(x1, x, x2, y1, y, y2))
				return true;
		}
		for (int i = 1; i < lowerPoints.size() - 1; i++) {
			x1 = lowerPoints.get(i - 1).getX();
			y1 = lowerPoints.get(i - 1).getY();
			x2 = lowerPoints.get(i).getX();
			y2 = lowerPoints.get(i).getY();
			if (below(x1, x, x2, y1, y, y2))
				return true;
		}
		return false;
	}
}
