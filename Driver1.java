// COMP 282
// CSUN - Fall 2019
// Nicolae Bucsa
// Project 1 - Convex Hull

import java.util.*;
import java.io.*;

class Driver1 {
	
	public static void main (String[] args) {
		System.out.println("Welcome to Project 1: Boundaries");
		System.out.println("Loading points from input.txt");
		try {
			ConvexHull rf = new ConvexHull();
			rf.read();
			if (rf.points.size() < 3) {
				System.out.println("Not enough points in the file!");
				System.exit(0);
			}
			rf.upperEnvelope();
			System.out.println("Points in upper envelope:" + rf.upperPoints);
			rf.lowerEnvelope();
			System.out.println("Points in lower envelope:" + rf.lowerPoints);
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
					if (rf.outside(xcoord, ycoord))
						System.out.println("Outside");
					else
						System.out.println("Inside");
				}	
			}
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}