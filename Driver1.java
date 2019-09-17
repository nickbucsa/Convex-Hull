// COMP 282
// CSUN - Fall 2019
// Nicolae Bucsa
// Project 1 - Convex Hull

import java.util.*;
import java.io.*;

class Driver1 {
	
	ArrayList<Point> points= new ArrayList<>();
	HashSet<Integer> setX=new HashSet<>();
	int min;
	int max;
	public void read() throws IOException {
		FileReader fileRead;
		Point point=null;
		fileRead = new FileReader("C:\\Users\\bucci\\eclipse-workspace\\ConvexHull\\src\\input.txt");
		BufferedReader reader= new BufferedReader(fileRead);
        String line;
        String []string;
        int coordinateX, coordinateY;
        while ((line= reader.readLine()) != null ) {
        	string = line.split(" ");
        	coordinateX= Integer.parseInt(string[0]);
            coordinateY= Integer.parseInt(string[1]);
            point = new Point(coordinateX, coordinateY);
            points.add(point);
        }
        System.out.println("Points in the file: " + points);
	}
	
	public static void main (String args[]) {
		System.out.println("Welcome to Project 1: Boundaries");
		System.out.println("Loading points from input.txt");
		try {
			ReadFile rf= new ReadFile();
			rf.read();
			System.out.println("Min: "+rf.minX());
			System.out.println("Max: "+rf.maxX());
			System.out.println("Starting point: "+ rf.startingPoint(rf.minX()));
			System.out.println("Ending point: "+ rf.endingPoint(rf.maxX()));
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.print("Test point: \n>");
				String input = sc.nextLine();
				if (input.equalsIgnoreCase("quit"))
					break;
				else {
					String[] s = input.split(" ");
					int xcoord = Integer.parseInt(s[0]);
					int ycoord = Integer.parseInt(s[1]);
				}
				
			}
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int minX() {
		List<Integer> listX= sort();
		min = listX.get(0);
		return min;	
	}
	
	public int maxX() {
		List<Integer> listX= sort();
		max = listX.get(listX.size()-1);
		return max;	
	}
	
	public List<Integer> sort() {
		for (Point point: points) {
			setX.add(point.getX());		
		}
		List<Integer> listX= new ArrayList<Integer>(setX);
		Collections.sort(listX);
		return listX;
	}
	
	public Point startingPoint(int min) {
		for (Point point: points) {
			if (point.getX()== min) 
				return new Point(min, point.getY() );
		}
	return null;
	}
	
	public Point endingPoint(int max) {
		for (Point point: points) {
			if (point.getX()== max) 
				return new Point(max, point.getY() );
		}
	return null;
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
	
}