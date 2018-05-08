package edu.iastate.cs228.hw4;

/**
 *  
 *@author joshuabump
 *
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException; 
import java.io.PrintWriter;
import java.util.Scanner;



/**
 * 
 * This class implements construction of the convex hull of a finite number of points. 
 *
 */

public abstract class ConvexHull 
{
	// ---------------
	// Data Structures 
	// ---------------
	protected String algorithm;  // Its value is either "Graham's scan" or "Jarvis' march". 
	                             // Initialized by a subclass.
	
	protected long time;         // execution time in nanoseconds
	
	/**
	 * The array points[] holds an input set of Points, which may be randomly generated or 
	 * input from a file.  Duplicates are possible. 
	 */
	private Point[] points;    
	

	/**
	 * Lowest point from points[]; and in case of a tie, the leftmost one of all such points. 
	 * To be set by a constructor. 
	 */
	protected Point lowestPoint; 

	
	/**
	 * This array stores the same set of points from points[] with all duplicates removed. 
	 * These are the points on which Graham's scan and Jarvis' march will be performed. 
	 */
	protected Point[] pointsNoDuplicate; 
	
	
	/**
	 * Vertices of the convex hull in counterclockwise order are stored in the array 
	 * hullVertices[], with hullVertices[0] storing lowestPoint. 
	 */
	protected Point[] hullVertices;
	
	
	protected QuickSortPoints quicksorter;  // used (and reset) by this class and its subclass GrahamScan

	
	
	// ------------
	// Constructors
	// ------------
	
	
	/**
	 * Constructor over an array of points.  
	 * 
	 *    1) Store the points in the private array points[].
	 *    
	 *    2) Initialize quicksorter. 
	 *    
	 *    3) Call removeDuplicates() to store distinct points from the input in pointsNoDuplicate[].
	 *    
	 *    4) Set lowestPoint to pointsNoDuplicate[0]. 
	 * 
	 * @param pts
	 * @throws IllegalArgumentException  if pts.length == 0
	 */
	public ConvexHull(Point[] pts) throws IllegalArgumentException 
	{
		
		points = new Point[pts.length];
		
		//put points into array
		for(int i=0;i<pts.length;i++) {
		points[i] = pts[i];
		}
		
		quicksorter = new QuickSortPoints(points);
		//remove the duplicates from the list
		removeDuplicates();
		
		lowestPoint = pointsNoDuplicate[0];
	}
	
	
	/**
	 * Read integers from an input file.  Every pair of integers represent the x- and y-coordinates 
	 * of a point.  Generate the points and store them in the private array points[]. The total 
	 * number of integers in the file must be even.
	 * 
	 * You may declare a Scanner object and call its methods such as hasNext(), hasNextInt() 
	 * and nextInt(). An ArrayList may be used to store the input integers as they are read in 
	 * from the file.  
	 * 
	 * Perform the operations 1)-4) described for the previous constructor. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException   when the input file contains an odd number of integers
	 */
	public ConvexHull(String inputFileName) throws FileNotFoundException, InputMismatchException
	{
		//create a file and scanner
		
				File file = new File(inputFileName);
				Scanner scan = new Scanner(file);
			
				ArrayList<Integer> temp = new ArrayList<Integer>();
				
				while(scan.hasNextInt()) {
					temp.add(scan.nextInt());	
				}
				scan.close();
				int x = 0;
				points = new Point[temp.size()/2];
				for(int i = 0; i<points.length; i++) {
				
					
					points[i] = new Point(temp.get(x),temp.get(x+1));
	
					x+=2;
				}
				
				quicksorter = new QuickSortPoints(points);
				
				removeDuplicates();
				
				lowestPoint = pointsNoDuplicate[0];
	}

	
	/**
	 * Construct the convex hull of the points in the array pointsNoDuplicate[]. 
	 */
	public abstract void constructHull(); 

	
		
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <convex hull algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * Graham's scan   1000	  9200867
	 *  
	 * Use the spacing in the sample run in Section 5 of the project description. 
	 */
	public String stats()
	{
		
		String s = algorithm + "       " + this.pointsNoDuplicate.length  + "         " + time;

		return s; 
		
	}
	
	
	/**
	 * The string displays the convex hull with vertices in counterclockwise order starting at  
	 * lowestPoint.  When printed out, it will list five points per line with three blanks in 
	 * between. Every point appears in the format "(x, y)".  
	 * 
	 * For illustration, the convex hull example in the project description will have its 
	 * toString() generate the output below: 
	 * 
	 * (-7, -10)   (0, -10)   (10, 5)   (0, 8)   (-10, 0)   
	 * 
	 * lowestPoint is listed only ONCE. 
	 *  
	 * Called only after constructHull().  
	 */
	public String toString()
	{
		
		String s = "";
		int x = 0;
		for(int i = 0; i<hullVertices.length; i++) {
			
			if(x == 4) {
			
			s = s +  "(" + hullVertices[i].getX() + ", " + hullVertices[i].getY() + ")\n";
			x=0;
		}
			else {
				s = s +  "(" + hullVertices[i].getX() + ", " + hullVertices[i].getY() + ")  ";
				x++;
			}
				
			}
		 
		return s; 
	}
	
	
	/** 
	 * 
	 * Writes to the file "hull.txt" the vertices of the constructed convex hull in counterclockwise 
	 * order.  These vertices are in the array hullVertices[], starting with lowestPoint.  Every line
	 * in the file displays the x and y coordinates of only one point.  
	 * 
	 * For instance, the file "hull.txt" generated for the convex hull example in the project 
	 * description will have the following content: 
	 * 
     *  -7 -10 
     *  0 -10
     *  10 5
     *  0  8
     *  -10 0
	 * 
	 * The generated file is useful for debugging as well as grading. 
	 * 
	 * Called only after constructHull().  
	 * 
	 * 
	 * @throws IllegalStateException  if hullVertices[] has not been populated (i.e., the convex 
	 *                                   hull has not been constructed)
	 */
	public void writeHullToFile() throws IllegalStateException 
	{
		PrintWriter out;
		try {
			
			out = new PrintWriter(new FileWriter("hull.txt"));
			
			for(int i = 0 ; i<hullVertices.length;i++) {
			out.print(hullVertices[i].getX() + " " + hullVertices[i].getY() + "\n"); 
			}
			
			out.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		} 
	}
	

	/**
	 * Draw the points and their convex hull.  This method is called after construction of the 
	 * convex hull.  You just need to make use of hullVertices[] to generate a list of segments 
	 * as the edges. Then create a Plot object to call the method myFrame().  
	 */
	public void draw()
	{		
		int numSegs = 0;  // number of segments to draw 

		// Based on Section 4, generate the line segments to draw for display of the convex hull.
		// Assign their number to numSegs, and store them in segments[] in the order. 
		
		//calculate number of segments
		numSegs = hullVertices.length;
		
		Segment[] segments = new Segment[numSegs]; 
		
		//add segments to segment array
		
		for(int i = 0; i<hullVertices.length-1; i++){
			
		segments[i] = new Segment(hullVertices[i], hullVertices[i+1]); 
		
		}
		
		segments[numSegs-1] = new Segment(hullVertices[hullVertices.length-1],hullVertices[0]);
		
		// The following statement creates a window to display the convex hull.
		Plot.myFrame(pointsNoDuplicate, segments, getClass().getName());
		
	}

		
	/**
	 * Sort the array points[] by y-coordinate in the non-decreasing order.  Have quicksorter 
	 * invoke quicksort() with a comparator object which uses the compareTo() method of the Point 
	 * class. Copy the sorted sequence onto the array pointsNoDuplicate[] with duplicates removed.
	 *     
	 * Ought to be private, but is made public for testing convenience. 
	 */
	public void removeDuplicates()
	{
		
	
		Comparator<Point> comp = new Comparator<Point>()
		  {
		    @Override
		    public int compare(Point p0, Point p1)
		    {
		      return p0.compareTo(p1);
		    }
		  };

		// use comp as a Comparator<Point>
		  
		quicksorter.quickSort(comp);
		
		//remove all of the duplicates
		ArrayList<Point> temp = new ArrayList<Point>();
		
		for(int i = 0; i<points.length; i++) {
			
			if(!temp.contains(points[i])) {
				temp.add(points[i]);
			}
		}
		
		pointsNoDuplicate = new Point[temp.size()];
		
		for(int j = 0; j<temp.size(); j++) {
			
			pointsNoDuplicate[j] = temp.get(j);
		}
		
		
	}
}
