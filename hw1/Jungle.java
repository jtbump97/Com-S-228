package edu.iastate.cs228.hw1;

/**
 *  
 * @author joshuabump
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * 
 * The jungle is represented as a square grid of size width X width.
 *
 */

public class Jungle {
	private int width; // grid size: width X width
	public Living[][] grid;

	/**
	 * Default constructor reads from a file
	 */
	public Jungle(String inputFileName) throws FileNotFoundException {
		// reads in the file and puts it into an ArrayList
		File file = new File(inputFileName);
		Scanner scan = new Scanner(file);
		ArrayList<String> s = new ArrayList<String>();

		while (scan.hasNext()) {
			s.add(scan.next());
		}

		/*
		 * Calculates the width, creates a new jungle of that size, and creates
		 * a temporary Living 2d array to be used
		 */
		width = (int) Math.sqrt(s.size());

		grid = new Living[width][width];

		// keeps track of the indices needed from the ArrayList
		int t = 0;

		// turning the string into a jungle 2d array
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {

				if (s.get(t).contains("J")) {
					int tempAge = Integer.parseInt(s.get(t).replaceAll("\\D", ""));

					grid[i][j] = new Jaguar(this, i, j, tempAge);
				} else if (s.get(t).contains("P")) {
					int tempAge = Integer.parseInt(s.get(t).replaceAll("\\D", ""));

					grid[i][j] = new Puma(this, i, j, tempAge);
				} else if (s.get(t).contains("D")) {

					int tempAge = Integer.parseInt(s.get(t).replaceAll("\\D", ""));

					grid[i][j] = new Deer(this, i, j, tempAge);
				} else if (s.get(t).contains("G")) {
					grid[i][j] = new Grass(this, i, j);
				} else if (s.get(t).contains("E")) {
					grid[i][j] = new Empty(this, i, j);
				}
				t++;
			}

		}

		scan.close();

		//
		// Assumption: The input file is in correct format.
		//
		// You may create the grid jungle in the following steps:
		//
		// 1) Reads the first line to determine the width of the grid.
		//
		// 2) Creates a grid object.
		//
		// 3) Fills in the grid according to the input file.
		//
		// Be sure to close the input file when you are done.
	}

	/**
	 * Constructor that builds a w X w grid without initializing it.
	 * 
	 * @param width
	 *            the grid
	 */
	public Jungle(int w) {

		Living[][] grid = new Living[w][w];

	}

	public int getWidth() {

		return width;
	}

	/**
	 * Initialize the jungle by randomly assigning to every square of the grid
	 * one of Deer, Empty, Grass, Jaguar, or Puma.
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit() {

		// TODO
	}

	/**
	 * Output the jungle grid. For each square, output the first letter of the
	 * living form occupying the square. If the living form is an animal, then
	 * output the age of the animal followed by a blank space; otherwise, output
	 * two blanks.
	 */
	public String toString() {

		// Creates a temporary 2d array to store the living objects as a string
		String[][] tempStringArray = new String[width][width];

		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {
				if (grid[i][j].who() == State.JAGUAR) {
					Jaguar jag = (Jaguar) grid[i][j];
					tempStringArray[i][j] = "J" + jag.myAge();

				} else if (grid[i][j].who() == State.PUMA) {
					Puma p = (Puma) grid[i][j];
					tempStringArray[i][j] = "P" + p.myAge();
				} else if (grid[i][j].who() == State.DEER) {
					Deer d = (Deer) grid[i][j];
					tempStringArray[i][j] = "D" + d.myAge();
				} else if (grid[i][j].who() == State.GRASS) {
					tempStringArray[i][j] = "G ";
				} else if (grid[i][j].who() == State.EMPTY) {
					tempStringArray[i][j] = "E ";
				}
			}
		}

		// puts the 2d array into a string in the correct format and returns it
		// as temp

		String temp = "";
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < width; j++) {

				if (tempStringArray[i][j].contains("G") && tempStringArray[i][j].contains("E")) {
					temp = temp + tempStringArray[i][j] + "  ";

				} else {
					temp = temp + tempStringArray[i][j] + " ";

				}

			}
			temp = temp + '\n';
		}
		return temp;
	}

	/**
	 * Write the jungle grid to an output file. Also useful for saving a
	 * randomly generated jungle for debugging purpose.
	 * @throws IOException 
	 */
	public void write(String outputFileName)   {

		PrintWriter out;
		try {
			out = new PrintWriter(new FileWriter(outputFileName));
			out.print("x"); 
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		
		//
		// 1. Open the file.
		//
		// 2. Write to the file. The five life forms are represented by
		// characters
		// D, E, G, J, P. Leave one blank space in between. Examples are given
		// in
		// the project description.
		//
		// 3. Close the file.
	}
}
