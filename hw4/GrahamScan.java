package edu.iastate.cs228.hw4;

/**
 * @author joshuabump

 */
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

import java.util.Comparator;

public class GrahamScan extends ConvexHull {
	/**
	 * Stack used by Graham's scan to store the vertices of the convex hull of
	 * the points scanned so far. At the end of the scan, it stores the hull
	 * vertices in the counterclockwise order.
	 */
	private PureStack<Point> vertexStack;

	/**
	 * Call corresponding constructor of the super class. Initialize two
	 * variables: algorithm (from the class ConvexHull) and vertexStack.
	 * 
	 * @throws IllegalArgumentException
	 *             if pts.length == 0
	 */
	public GrahamScan(Point[] pts) throws IllegalArgumentException {
		//super constructor call
		super(pts);
		
		//set algorithm equal to name of this scan
		super.algorithm = "Graham's Scan";
		
		//initialize the stack
		vertexStack = new ArrayBasedStack<Point>();;
	}

	/**
	 * Call corresponding constructor of the super class. Initialize algorithm
	 * and vertexStack.
	 * 
	 * @param inputFileName
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 *             when the input file contains an odd number of integers
	 */
	public GrahamScan(String inputFileName) throws FileNotFoundException, InputMismatchException {
		
		//super constructor call
		super(inputFileName);
		//assign algorithm variable to name of this scan
		super.algorithm = "Graham's Scan";
		vertexStack = new ArrayBasedStack<Point>();;
	}

	// -------------
	// Graham's scan
	// -------------

	/**
	 * This method carries out Graham's scan in several steps below: 
	 * 
	 *     1) Call the private method setUpScan() to sort all the points in the array 
	 *        pointsNoDuplicate[] by polar angle with respect to lowestPoint.    
	 *        
	 *     2) Perform Graham's scan. To initialize the scan, push pointsNoDuplicate[0] and 
	 *        pointsNoDuplicate[1] onto vertexStack.  
	 * 
     *     3) As the scan terminates, vertexStack holds the vertices of the convex hull.  Pop the 
     *        vertices out of the stack and add them to the array hullVertices[], starting at index
     *        vertexStack.size() - 1, and decreasing the index toward 0.    
     *        
     * Two degenerate cases below must be handled: 
     * 
     *     1) The array pointsNoDuplicates[] contains just one point, in which case the convex
     *        hull is the point itself. 
     *     
     *     2) The array contains only collinear points, in which case the hull is the line segment 
     *        connecting the two extreme points.   
	 */
	public void constructHull()
	{
		setUpScan();
		time = System.nanoTime();
		
		//if given only one point
		if(pointsNoDuplicate.length==1) {
			vertexStack.push(pointsNoDuplicate[0]);
		}
		
		//if given only two points
		else if(pointsNoDuplicate.length==2) {
			time = System.nanoTime();
			vertexStack.push(pointsNoDuplicate[0]);
			vertexStack.push(pointsNoDuplicate[1]);
			
		}
		
		else {
		
		
		//push first three points onto stack
		vertexStack.push(pointsNoDuplicate[0]);
	vertexStack.push(pointsNoDuplicate[1]);
		
		
		for(int i = 2; i< pointsNoDuplicate.length;i++) {
			
			
			Point top = vertexStack.pop();
			
			Comparator<Point> temp = new PolarAngleComparator(vertexStack.peek(), true); 
			
			
			while (temp.compare(pointsNoDuplicate[i],top) < 0) {
				
				top = vertexStack.pop();
				temp = new PolarAngleComparator(vertexStack.peek(), false);
			}
	
				//push top back on to list
				vertexStack.push(top);
				
				//push current on to list to be able to put back into comparator later
				vertexStack.push(pointsNoDuplicate[i]);
				
				}
				
		}
		
		int numOnStack = vertexStack.size()-1;
		
		//set up hull array with size needed
		hullVertices = new Point[vertexStack.size()];
		
		while(vertexStack.size()!=0) {
			//add individual points onto array from stack
			hullVertices[numOnStack] = vertexStack.pop();
			numOnStack--;
			
		}
		//calculate total time taken
		 time = System.nanoTime() - time;
		
	}

	/**
	 * Set the variable quicksorter from the class ConvexHull to sort by polar
	 * angle with respect to lowestPoint, and call quickSort() from the
	 * QuickSortPoints class on pointsNoDupliate[]. The argument supplied to
	 * quickSort() is an object created by the constructor call
	 * PolarAngleComparator(lowestPoint, true).
	 * 
	 * Ought to be private, but is made public for testing convenience.
	 *
	 */
	public void setUpScan() {

		quicksorter = new QuickSortPoints(pointsNoDuplicate);
		PolarAngleComparator temp = new PolarAngleComparator(lowestPoint, true);
		quicksorter.quickSort(temp);
	}
}
