package edu.iastate.cs228.hw1;

/**
 *@author joshuabump
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class CircleOfLifeTest {

	@Test
	public void setup() throws FileNotFoundException {

		//Old
		Jungle j = new Jungle("public3-10x10.txt");
		//New
		Jungle k = new Jungle("public1-1cycle.txt");
		//System.out.println(j);
		
		CircleOfLife.updateJungle(j, k);
	
		//Old
		assertEquals("G  E  G  \n" + "E  J0 P0\n" +"J0 E  D0", j.toString());

		//New
		assertEquals("G  E  G  \n" + "E  J0 P0\n" +"J0 E  D0", k.toString());
	}		

}
