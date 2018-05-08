package edu.iastate.cs228.hw1;

/**
 * @author joshuabump
 */
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class JaguarTest {


	@Test
	public void setup() throws FileNotFoundException {

		Jungle j = new Jungle("public1-1cycle.txt");

		assertEquals("JAGUAR",j.grid[2][0].who().toString());
		assertEquals(0,j.grid[2][0].column);
		assertEquals(2,j.grid[2][0].row);
		

	}

}
