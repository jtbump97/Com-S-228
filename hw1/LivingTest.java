package edu.iastate.cs228.hw1;

/**
 * @author joshuabump
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class LivingTest {

	private Jungle j;
	@Test
	public void setup() throws FileNotFoundException {

		j = new Jungle("public1-3x3.txt");

		
		Living x = j.grid[0][0];
		assertEquals("GRASS", x.who().toString());
		x = j.grid[0][1];
		assertEquals("JAGUAR", x.who().toString());
		x = j.grid[0][2];
		assertEquals("GRASS", x.who().toString());
		x = j.grid[1][0];
		assertEquals("JAGUAR", x.who().toString());
		x = j.grid[1][1];
		assertEquals("DEER", x.who().toString());
		x = j.grid[1][2];
		assertEquals("PUMA", x.who().toString());
		x = j.grid[2][0];
		assertEquals("JAGUAR", x.who().toString());
		x = j.grid[2][1];
		assertEquals("DEER", x.who().toString());
		x = j.grid[2][2];
		assertEquals("EMPTY", x.who().toString());

		
		//G  J0 G  
		//J0 D0 P0 
		//J0 D0 E 
	}

}
