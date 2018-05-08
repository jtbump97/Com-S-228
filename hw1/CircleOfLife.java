package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  
 * @author joshuabump
 *
 */

/**
 * 
 * The CircleOfLife class performs simulation over a grid jungle with squares
 * occupied by deers, jaguars, pumas, grass, or none.
 *
 */
public class CircleOfLife {
	/**
	 * Update the new jungle from the old jungle in one cycle.
	 * 
	 * @param jOld
	 *            old jungle
	 * @param jNew
	 *            new jungle
	 */
	public static void updateJungle(Jungle jOld, Jungle jNew) {

		for (int i = 0; i < jOld.getWidth(); i++) {
			for (int j = 0; j < jOld.getWidth(); j++) {

				jNew.grid[i][j] = jOld.grid[i][j].next(jNew);
				
			}
		}

		//
		// For every life form (i.e., a Living object) in the grid jOld,
		// generate
		// a Living object in the grid jNew at the corresponding location such
		// that
		// the former life form changes into the latter life form.
		//
		// Employ the method next() of the Living class.
	}

	/**
	 * Repeatedly generates jungles either randomly or from reading files. Over
	 * each jungle, carries out an input number of cycles of evolution.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {

		int quit = 0;
		int trialNum = 1;

		while (quit != 3) {
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);

			System.out.println(
					"\nCircle of Life in the Amazon Jungle" + "\n" + "keys: 1 (random jungle) 2 (file input) 3 (exit)");

			System.out.println("Trial " + trialNum + ":");

			int input = scan.nextInt();
			if (input == 3) {

				quit = 3;

			}

			if (input == 1) {

				System.out.println("This feature unavailable. Try with a file" + "\n");
			}

			else if (input == 2) {

				System.out.println("Jungle input from a file" + "\n" + "Please enter the file name:");

				String saved = scan.next();

				System.out.println("What is the width of the Jungle?");

				System.out.print("Enter the number of cycles:");

				int numCycle = scan.nextInt();

				Jungle scanned = new Jungle(saved);

				Jungle temp = new Jungle(saved);

				Jungle initial = new Jungle(saved);

				//update jungle a chosen number of times
				for (int i = 0; i < numCycle; i++) {

					for(int f = 0;f<scanned.getWidth();f++) {
						for(int g=0;g<scanned.getWidth();g++) {
					
					temp.grid[f][g] = scanned.grid[f][g];
						}}
					
					updateJungle(temp, scanned);
					
				
				}

				System.out.print(
						"\nInitial Jungle:\n" + initial.toString() + "\n" + "Final Jungle:\n" + scanned.toString());

				trialNum++;

				//
				// Generate CircleOfLife simulations repeatedly like shown in
				// the
				// sample run in the project description.
				//
				// 1. Enter 1 to generate a random jungle, 2 to read a jungle
				// from an
				// input
				// file, and 3 to end the simulation. (An input file always ends
				// with
				// the suffix .txt.)
				//
				// 2. Print out standard messages as given in the project
				// description.
				//
				// 3. For convenience, you may define two jungles even and odd
				// as below.
				// In an even numbered cycle (starting at zero), generate the
				// jungle
				// odd from the jungle even; in an odd numbered cycle, generate
				// even
				// from odd.


				// 4. Print out initial and final jungles only. No intermediate
				// jungles
				// should
				// appear in the standard output. (When debugging your program,
				// you can
				// print intermediate jungles.)
				//
				// 5. You may save some randomly generated jungles as your own
				// test
				// cases.
				//
				// 6. It is not necessary to handle file input & output
				// exceptions for
				// this
				// project. Assume data in an input file to be correctly
				// formated.
			}
		}
	}

}
