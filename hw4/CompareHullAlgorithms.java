package edu.iastate.cs228.hw4;

/**
 *  
 * @author joshuabump
 *
 */

/**
 * 
 * This class executes two convex hull algorithms: Graham's scan and Jarvis' march, over randomly
 * generated integers as well integers from a file input. It compares the execution times of 
 * these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Random;

public class CompareHullAlgorithms {
	/**
	 * Repeatedly take points either randomly generated or read from files.
	 * Perform Graham's scan and Jarvis' march over the input set of points,
	 * comparing their performances.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 * @throws InputMismatchException
	 **/
	public static void main(String[] args) throws InputMismatchException, FileNotFoundException {

		//
		// Conducts multiple rounds of convex hull construction. Within each
		// round, performs the following:
		//
		// 1) If the input are random points, calls generateRandomPoints() to
		// initialize an array
		// pts[] of random points. Use pts[] to create two objects of GrahamScan
		// and JarvisMarch,
		// respectively.
		//
		// 2) If the input is from a file, construct two objects of the classes
		// GrahamScan and
		// JarvisMarch, respectively, using the file.
		//
		// 3) Have each object call constructHull() to build the convex hull of
		// the input points.
		//
		// 4) Meanwhile, prints out the table of runtime statistics.
		//
		// A sample scenario is given in Section 5 of the project description.
		//
	
		ConvexHull[] algorithms = new ConvexHull[2];

		// Within a hull construction round, have each algorithm call the
		// constructHull() and draw()
		// methods in the ConvexHull class. You can visually check the result.
		// (Windows
		// have to be closed manually before rerun.) Also, print out the
		// statistics table
		// (see Section 5).
		
		
		
		int trialNum = 1;

		System.out.println("\nComparison between Convex Hull Algorithms");
		Scanner scan = new Scanner(System.in);
		int input = 1;
		while (input == 1 || input == 2) {

			System.out.println("Trial " + trialNum + ":");

			input = scan.nextInt();

			if (input == 1) {

				System.out.println("Enter number of random points:");
				int num = scan.nextInt();
				System.out.println("algorithm           size        time(ns)\n----------------------------------------");
				Point[] pts = new Point[num];

				Random rand = new Random();

				pts = generateRandomPoints(num, rand);

				algorithms[0] = new GrahamScan(pts);
				algorithms[1] = new JarvisMarch(pts);

				for(int i = 0;i< 2; i++) {
				algorithms[i].constructHull();
				algorithms[i].draw();
				System.out.println(algorithms[i].toString());
				System.out.println(algorithms[i].stats());
				
				}

				System.out.println("----------------------------------------\n");
				trialNum++;
			}

			else if (input == 2) {

				System.out.println("Points from a file");
				System.out.println("File name:");
				String file = scan.next();

				System.out.println("algorithm           size        time(ns)\n----------------------------------------");

				algorithms[0] = new GrahamScan(file);
				algorithms[1] = new JarvisMarch(file);
				
				for(int i = 0; i<2; i++) {
				algorithms[i].constructHull();
				
				algorithms[i].draw();
				
				System.out.println(algorithms[i].stats());
				}
				System.out.println("----------------------------------------\n");
				trialNum++;

			

		}

	}
		scan.close();
		System.exit(0);
	}

	/**
	 * This method generates a given number of random points. The coordinates of
	 * these points are pseudo-random numbers within the range [-50,50] �
	 * [-50,50].
	 * 
	 * @param numPts
	 *            number of points
	 * @param rand
	 *            Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException
	 *             if numPts < 1
	 */
	private static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {

		if (numPts < 1) {
			throw new IllegalArgumentException();
		}

		Point[] randoms = new Point[numPts];

		for (int i = 0; i < numPts; i++) {

			randoms[i] = new Point(rand.nextInt(100) - 50, rand.nextInt(100) - 50);
		}

		return randoms;

	}
}
