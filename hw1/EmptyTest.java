package edu.iastate.cs228.hw1;

/**
 * @author joshuabump
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class EmptyTest {


	@Test
	public void setup() throws FileNotFoundException {

		Jungle j = new Jungle("public1-3x3.txt");

		assertEquals("EMPTY",j.grid[2][2].who().toString());
		assertEquals(2,j.grid[2][2].column);
		assertEquals(2,j.grid[2][2].row);
		

	}

}
