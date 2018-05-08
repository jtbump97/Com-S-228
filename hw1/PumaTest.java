package edu.iastate.cs228.hw1;

/**
 * @author joshuabump
 */
import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class PumaTest {


	@Test
	public void setup() throws FileNotFoundException {

		Jungle j = new Jungle("public1-1cycle.txt");

		assertEquals("PUMA",j.grid[1][2].who().toString());
		assertEquals(2,j.grid[1][2].column);
		assertEquals(1,j.grid[1][2].row);
		

	}

}
