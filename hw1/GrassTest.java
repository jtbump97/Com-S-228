package edu.iastate.cs228.hw1;

/**
 * @author joshuabump
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class GrassTest {


	@Test
	public void setup() throws FileNotFoundException {

		Jungle j = new Jungle("public1-1cycle.txt");

		assertEquals("GRASS",j.grid[0][0].who().toString());
		assertEquals(0,j.grid[0][0].column);
		assertEquals(0,j.grid[0][0].row);
		

	}

}
