package edu.iastate.cs228.hw1;

/**
 * @author joshuabump
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class AnimalTest {
	
	@Test
	public void setup() throws FileNotFoundException {
		

		Jungle j = new Jungle("public1-3x3.txt");

		assertEquals("DEER",j.grid[1][1].who().toString());
		assertEquals(1,j.grid[1][1].column);
		assertEquals(1,j.grid[1][1].row);
		

	}
}
	




