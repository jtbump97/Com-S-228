package edu.iastate.cs228.hw1;

/**
 * @author joshuabump
 */

import static org.junit.Assert.*;

import java.io.FileNotFoundException;

import org.junit.Test;

public class JungleTest {

	@Test
	public void test() throws FileNotFoundException {
		Jungle j = new Jungle("public1-3x3.txt");
		
		assertEquals("G  J0 G \nJ0 D0 P0\nJ0 D0 E ",j.toString());
		
		
	}

}
